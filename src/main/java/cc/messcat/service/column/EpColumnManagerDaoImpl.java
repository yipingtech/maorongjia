package cc.messcat.service.column;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Authorities;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.entity.PageType;
import cc.messcat.entity.Roles;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.entity.RolesAuthoritiesId;
import cc.messcat.front.IndexInfoBean;

/**
 * @author Messcat
 * @version 1.1
 */
public class EpColumnManagerDaoImpl extends BaseManagerDaoImpl implements EpColumnManagerDao {

	public EpColumnManagerDaoImpl() {
	}

	public List findAllEnterpriseColumn() {
		return this.epColumnDao.findAll();
	}

	public List findEnterpriseColumn() {
		List EpColumnList = epColumnDao.findByFatherAndState(Long.valueOf(0L), "-1", null);
		return EpColumnList;
	}

	public EnterpriseColumn getEnterpriseColumn(Long id) {
		return epColumnDao.get(id);
	}

	/**
	 * 添加栏目时,需要添加相应的权限控制
	 * */
	public void saveEnterpriseColumn(EnterpriseColumn enterpriseColumn) {

		epColumnDao.save(enterpriseColumn);

		/**
		 * 根据新增的栏目信息动态生成URL，并再次更新栏目
		 */
		if(enterpriseColumn.getTypeColumn().getTemplateType() == null || "".equals(enterpriseColumn.getTypeColumn().getTemplateType())){
			enterpriseColumn.setTypeColumn(this.pageTypeDao.get(enterpriseColumn.getTypeColumn().getId()));
		}
		String linkUrl = buildLinkUrl(enterpriseColumn);
		if(linkUrl != null){
			enterpriseColumn.setLinkUrl(linkUrl);
		}
		epColumnDao.update(enterpriseColumn);
		
		/**
		 * 权限添加其父级栏目
		 * @author Andy Lin
		 * 2013/06/25
		 */
		Long fatherId = enterpriseColumn.getFather();
		Long fatherAuthId;
		Authorities fatherAuth = null;
		
		if(fatherId == 0L){
			fatherAuthId = 0L;
		}else{
			EnterpriseColumn ec = this.epColumnDao.get(fatherId);
			String fatherName = ec.getNames();
			fatherAuth = this.authoritiesDao.getByNameAndType(fatherName, "1");
			fatherAuthId = fatherAuth.getId();
		}
		/* ---end--- */
		
		Authorities auth = new Authorities();
		auth.setDisplayName(enterpriseColumn.getNames());
		auth.setName(enterpriseColumn.getNames());
		auth.setAuthoritiesType("1");
		auth.setAuthoritiesId(fatherAuthId);

		this.authoritiesDao.save(auth);
		
		/**
		 * 迭代所有角色，如果此角色含有“自动添加栏目权限”这个权限，则为此角色添加此新增的栏目权限
		 * @author Andy Lin
		 * 2013/07/22
		 */
		Authorities autoAddPermit = this.authoritiesDao.getByName("AUTO_ADDPERMIT");
		List<Roles> roleList = this.rolesDao.findAllBYState("1");
		
		//判断数据库中是否存在“自动添加栏目权限”
		if(autoAddPermit != null){
			for (Roles roles : roleList) {
				//init object
				Set<RolesAuthorities> authSet = roles.getRolesAuthoritieses();
				
				//判断角色中是否包含“自动添加栏目权限”
				RolesAuthorities rolesAuthorities = new RolesAuthorities(new RolesAuthoritiesId(roles, autoAddPermit));
				if(authSet.contains(rolesAuthorities)){
					//如果父级权限不为0L，则判断该角色是否拥有此父级权限，拥有才给他添加栏目权限
					if(fatherAuthId != 0L){
						RolesAuthorities fatherAuthorities = new RolesAuthorities(new RolesAuthoritiesId(roles, fatherAuth));
						if(!authSet.contains(fatherAuthorities))
							continue;
					}
					RolesAuthoritiesId currRoleAuthId = new RolesAuthoritiesId(roles, auth);
					RolesAuthorities rolesauthorities = new RolesAuthorities(currRoleAuthId, roles, auth);
					this.rolesAuthoritiesDao.save(rolesauthorities);
				}
			}
		}
	}

	public void updateEnterpriseColumn(EnterpriseColumn enterpriseColumn, Authorities auth) {

		//动态生成URL
		if(enterpriseColumn.getTypeColumn().getTemplateType() == null || "".equals(enterpriseColumn.getTypeColumn().getTemplateType())){
			enterpriseColumn.setTypeColumn(this.pageTypeDao.get(enterpriseColumn.getTypeColumn().getId()));
		}
		String linkUrl = buildLinkUrl(enterpriseColumn);
		if(linkUrl != null){
			enterpriseColumn.setLinkUrl(linkUrl);
		}
		//更新栏目信息
		this.epColumnDao.update(enterpriseColumn);
		//更新权限信息
		this.authoritiesDao.update(auth);

	}

	public void deleteEnterpriseColumn(Long id) {
		List newsList = epNewsDao.findFrontLimitNewsByColumn(1000L, id);
		if (newsList != null) {
			EnterpriseNews en;
			Iterator iterator = newsList.iterator();
			while (iterator.hasNext()) {
				en = (EnterpriseNews) iterator.next();
				epNewsDao.delete(en);
			}
		}
		EnterpriseColumn ec = epColumnDao.get(id);
		Authorities auth = authoritiesDao.getByName(ec.getNames());

		if (auth != null) {
			List<RolesAuthorities> list = rolesAuthoritiesDao.findByAuthId(auth.getId());
	
			if (list != null)
				for (RolesAuthorities ra : list) {
					this.rolesAuthoritiesDao.delete(ra);
				}
			this.authoritiesDao.delete(auth);
		}

		epColumnDao.delete(ec);
	}

	public List findSubColumn(Long father) {
		if (father == -1L)
			return epColumnDao.findAllColumn();
		else
			return epColumnDao.findSubColumn(father);
	}

	public boolean isNameUnique(String names, String orgName, Long father) {
		if (!names.equals(orgName))
			return epColumnDao.isNameUnique(names, father);
		else
			return true;
	}

	public EnterpriseColumn getEnterpriseColumn(String frontNum) {
		return epColumnDao.getColumnByFrontNum(frontNum);
	}

	/**
	 * 核心方法，查找所有标识的栏目的栏目信息和新闻内容信息
	 * */
	public List<IndexInfoBean> findFrontInfoFrontNumNotNull() {
		List indexBean = new ArrayList();
		List tempList = epColumnDao.getColumnByFrontNumNotNull();
		int i = 0;
		if (tempList != null) {
			Iterator iterator = tempList.iterator();
			IndexInfoBean temp = null;
			EnterpriseColumn ec = null;
			StringBuffer SQL = null;
			while (iterator.hasNext()) {
				SQL = null;
				SQL = new StringBuffer(" where 1 = 1 ");
				ec = (EnterpriseColumn) iterator.next();
				temp = new IndexInfoBean();

				/**
				 * 1.link链接栏目则直接设置
				 * 2.content单页栏目则需要查找单条新闻
				 * 3.list或者download列表栏目则需要查找栏目新闻
				 * */
				if ("link".equals(ec.getTypeColumn().getTemplateType())) {
					temp.setEnterpriseColumn(ec);
				} else if ("content".equals(ec.getTypeColumn().getTemplateType())) {
					temp.setEnterpriseColumn(ec);

					SQL.append(" and enterpriseColumn.id = ").append(ec.getId());
					List tempNews = epNewsDao.findNewsByWhere(1L, SQL.toString());

					if (tempNews != null && tempNews.size() > 0)
						temp.setEnterpriseNews((EnterpriseNews) tempNews.get(0));

					//temp.setEnterpriseColumnList(epColumnDao.findByFatherAndState(ec.getId(), "1", "orderColumn"));

				} else if ("list".equals(ec.getTypeColumn().getTemplateType()) || "download".equals(ec.getTypeColumn().getTemplateType())
					|| "mostlist".equals(ec.getTypeColumn().getTemplateType())) {
					StringBuffer SQL0 = null;
					
					temp.setEnterpriseColumn(ec);

					temp.setEnterpriseColumnList(epColumnDao.findByFatherAndState(ec.getId(), "1", "orderColumn"));

					SQL.append(" and (enterpriseColumn.id = ").append(ec.getId());

					Iterator iter = temp.getEnterpriseColumnList().iterator();

					while (iter.hasNext()) {
						EnterpriseColumn e = (EnterpriseColumn) iter.next();
						e.setProductColumns(epColumnDao.findProductColumn(e.getId()));
						//三级栏目
						e.setSubColumnList(epColumnDao.findSubColumn(e.getId()));
						Iterator it = e.getSubColumnList().iterator();
						while (it.hasNext()) {
							EnterpriseColumn th = (EnterpriseColumn) it.next();
							//三级栏目 商品-栏目
							th.setProductColumns(epColumnDao.findProductColumn(th.getId()));
						}
						SQL.append(" or enterpriseColumn.id = ").append(e.getId());
						
					}
					SQL.append(")");
					
					//是否在主页滚动显示，如果为1，才会在主页显示
					SQL.append(" AND IS_SHOWED_ON_INDEX = '1' ");

					temp.setEnterpriseNewsList(epNewsDao.findNewsByWhere(11L, SQL.toString() + " and state = 1 "));
					/**
					 * 查询5条新闻图片
					 * */
					SQL.append(" and isPrimPhoto = 1 ");
					temp.setEnterprisePhotoNewsList(epNewsDao.findNewsByWhere(5L, SQL.toString() + " and state = 1 "));
				} else if ("product".equals(ec.getTypeColumn().getTemplateType())) {
					temp.setEnterpriseColumn(ec);

					temp.setEnterpriseColumnList(epColumnDao.findByFatherAndState(ec.getId(), "1", "orderColumn"));

					/*SQL.append(" and (enterpriseColumn.id = ").append(ec.getId());

					Iterator iter = temp.getEnterpriseColumnList().iterator();

					while (iter.hasNext()) {
						EnterpriseColumn e = (EnterpriseColumn) iter.next();
						SQL.append(" or enterpriseColumn.id = ").append(e.getId());
					}
					SQL.append(")");
					
					temp.setMcProductInfoList(this.mcProductInfoDao.findMcProductInfoByWhere(SQL.toString()));*/
					temp.setMcProductInfoList(this.productColumnDao.findIsSaleProductByColumnPro(null, ec));
				}
				indexBean.add(i, temp);
				i++;
			}
		}
		return indexBean;
	}
	
	public IndexInfoBean findProductClassify(){
		IndexInfoBean result = new IndexInfoBean();
		EnterpriseColumn ec = epColumnDao.getColumnByFrontNum("indexProductTypes");
		result.setEnterpriseColumn(ec);
		//二级栏目 --> 产品分类
		result.setEnterpriseColumnList(epColumnDao.findByFatherAndState(ec.getId(), "1", "orderColumn"));
		Iterator iter = result.getEnterpriseColumnList().iterator();
		while (iter.hasNext()) {
			EnterpriseColumn e = (EnterpriseColumn) iter.next();
			e.setProductColumns(epColumnDao.findProductColumn(e.getId()));
			//三级栏目 -- 产品系列
			e.setSubColumnList(epColumnDao.findSubColumn(e.getId()));
			Iterator it = e.getSubColumnList().iterator();
			while (it.hasNext()) {
				EnterpriseColumn th = (EnterpriseColumn) it.next();
				//三级栏目 -->  商品-栏目
				th.setProductColumns(epColumnDao.findProductColumn(th.getId()));
			}
		}
		return result;
	}
	

	/**
	 * user to the ajax struts the nav tree in behind
	 * 
	 * @param father
	 * @return
	 */
	public List findTreeByFather(Long father) {
		return this.epColumnDao.findAjaxByFatherAndState(father, "-1", "orderColumn");
	}

	/**
	 * 判断是否叶节点
	 */
	public boolean isLeafNode(Long columnId) {
		List<EnterpriseColumn> tempEpColumnList = this.epColumnDao.findSubColumn(columnId);
		if(tempEpColumnList != null && tempEpColumnList.size() > 0){
			return false;
		}
		return true;
	}

	/**
	 * 根据父级栏目查找出本次新增的栏目排序
	 */
	public Long getOrderColumnByFather(Long father) {
		Long currentOrderColumn = this.epColumnDao.getMaxOrderByFather(father);
		return currentOrderColumn + 1L;
	}

	/**
	 * 根据栏目ID动态生成URL
	 * @author Andy Lin
	 * @param enterpriseColumn
	 * @return
	 */
	public static String buildLinkUrl(EnterpriseColumn enterpriseColumn){
		//1、根据选择的模板类型查出对象
		PageType currentType = enterpriseColumn.getTypeColumn();
		if(!"link".equals(currentType.getTemplateType())){
			//2、根据栏目对象查出URL模板
			String url = currentType.getTemplateUrl();
			//3、动态生成URL
			url = url.replace("@", enterpriseColumn.getId().toString());
			//4、获取当前项目路径，并组成完整URL
			StringBuilder urlBuilder = new StringBuilder();
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			String http = request.getScheme();				//http
			String urlName = request.getServerName();		//域名
			if("localhost".equals(urlName))
				urlName = "127.0.0.1";
			int port = request.getServerPort();				//端口号
			String root = request.getContextPath();			//项目跟目录
			urlBuilder.append(http).append("://").append(urlName).append(":").append(port).append(root).append("/").append(url);
			//5、生成linkUrl并返回
			return urlBuilder.toString();
		}else{
			return null;
		}
	}
	
	public List<EnterpriseColumn> findColumnsByPageType(Long fatherId, Long pageTypeId){
		return this.epColumnDao.findColumnsByPageType(fatherId, pageTypeId);
	}
	
}