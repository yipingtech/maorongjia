package cc.messcat.web.collection;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.modules.commons.PageAction;
import cc.modules.util.PropertiesFileReader;
import cc.modules.constants.ActionConstants;
import cc.modules.constants.Constants;
import cc.modules.util.DateHelper;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Messcat
 * @version 1.1
 */
public class EpNewsAction extends PageAction {

	private static final long serialVersionUID = 7227694518413664151L;
	private List<EnterpriseNews> enterpriseNewsList;
	private EnterpriseNews enterpriseNews;
	private Long id;
	private String title;
	private String contents;
	private Date initTime;
	private Date endTime;
	private File upload;
	private String uploadFileName;
	private String allowTypes;
	private String uploadContentType;
	private String father;
	private String state;
	private String photo;

	private String autoCutPhoto;

	private EnterpriseColumn column;
	
	//是否显示新增按钮
	private String isShowInsert;
	
	private File uploadImage;
	private String uploadImageFileName;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 根据栏目分页查询新闻内容信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception {
		String event = "";
		EnterpriseColumn ec = null;

		Long tempFather = Long.valueOf(father);

		if (0 != tempFather) {
			ec = this.epColumnManagerDao.getEnterpriseColumn(tempFather);
			this.setColumn(ec);
		} else {
			this.setColumn(null);
		}

		/**
		 * isShowInsert 是否在新闻列表页面显示插入按钮
		 * @author:Andy Lin
		 * @date:2013/7/15
		 * @time:11:49 AM
		 */
		/*------ isShowInsert begin ------*/
		boolean isLeafNode = this.epColumnManagerDao.isLeafNode(Long.valueOf(father));
		if (isLeafNode && tempFather != 0) {
			isShowInsert = "true";
		}
		/*------ isShowInsert end ------*/
		
		EnterpriseNews temp = null;

		if (tempFather != 0 && "content".equals(column.getTypeColumn().getTemplateType())) {
			temp = this.epNewsManagerDao.getEpNewsByColumnId(tempFather);

			if (temp == null) {
				temp = new EnterpriseNews();
				temp.setEnterpriseColumn(ec);
				temp.setState("1");
				temp.setTitle(ec.getNames());
				temp.setEditeTime(new Date());
				temp.setIsPrimPhoto("0");

				this.epNewsManagerDao.addEpNews(temp);
			}

			this.setEnterpriseNews(temp);

			event = ActionConstants.EDIT_KEY;

		} else {
			temp = new EnterpriseNews();

			if (title != null)
				temp.setTitle(title);

			if (state != null)
				temp.setState(state);

			EnterpriseColumn enterpriseColumn = new EnterpriseColumn();
			enterpriseColumn.setId(Long.valueOf(father));
			enterpriseColumn.setState("-1");
			temp.setEnterpriseColumn(enterpriseColumn);

			/**
			 * This line code will throw the java.lang.OutOfMemoryError: Java
			 * heap space
			 */
			super.pager = epNewsManagerDao.findEpNews(pageSize, pageNo, temp);
			List resultList = super.pager.getResultList();
			setEnterpriseNewsList(resultList);

			event = ActionConstants.SUCCESS_KEY;
		}
		pageSize = 10;
		pageNo = 1;
		return event;
	}

	/**
	 * 根据新闻标题和状态进行模糊查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public String queryByTitleAndState() throws Exception {
		String event = "";
		EnterpriseColumn ec = null;
		Long tempFather = Long.valueOf(father);

		if (0 != tempFather) {
			ec = this.epColumnManagerDao.getEnterpriseColumn(tempFather);
			this.setColumn(ec);
		} else {
			this.setColumn(null);
		}

		if (title != null)
			title = title.trim();

		/**
		 * isShowInsert 是否在新闻列表页面显示插入按钮
		 * @author:Andy Lin
		 * @date:2013/7/15
		 * @time:11:49 AM
		 */
		/*------ isShowInsert begin ------*/
		boolean isLeafNode = this.epColumnManagerDao.isLeafNode(Long.valueOf(father));
		if (isLeafNode) {
			isShowInsert = "true";
		}
		/*------ isShowInsert end ------*/
		
		EnterpriseNews temp = null;

		temp = new EnterpriseNews();
		// 在这里进行设置条件,进行条件查询
		temp.setTitle(title);
		temp.setState(state);

		EnterpriseColumn enterpriseColumn = new EnterpriseColumn();
		enterpriseColumn.setId(tempFather);
		enterpriseColumn.setState("-1");
		temp.setEnterpriseColumn(enterpriseColumn);

		super.pager = epNewsManagerDao.findEpNews(pageSize, pageNo, temp);
		List resultList = super.pager.getResultList();
		setEnterpriseNewsList(resultList);

		event = ActionConstants.SUCCESS_KEY;

		pageSize = 10;
		pageNo = 1;
		return event;
	}

	/**
	 * 查询一条新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String view() throws Exception {

		enterpriseNews = epNewsManagerDao.getEpNews(id);
		return ActionConstants.VIEW_KEY;
	}

	/**
	 * 查询一条新闻信息，并转到编辑状态
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {

		enterpriseNews = epNewsManagerDao.getEpNews(id);
		return ActionConstants.EDIT_KEY;
	}

	/**
	 * 添加一条新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		String event = "";
		// initEpNews();

		if (isAdmin()) {
			enterpriseNews.setState("1");// 已通过审核
		} else {
			enterpriseNews.setState("2");// 待审核
		}

		enterpriseNews.setContents(contents);

		/**
		 * 如果为下载模板,则上传的不为图片，为文件
		 */
		if (enterpriseNews.getEnterpriseColumn() != null && "2".equals(enterpriseNews.getIsPrimPhoto())) {
			if (!uploadFile("add")) {
				event = ActionConstants.INPUT_KEY;
			} else {
				enterpriseNews.setId(null);
				enterpriseNews.setEditeTime(new Date());
				epNewsManagerDao.addEpNews(enterpriseNews);
				this.setFather(String.valueOf(enterpriseNews.getEnterpriseColumn().getId()));
				event = this.query();
			}
		} else {
			if ("1".equals(enterpriseNews.getIsPrimPhoto()) && !uploadPhoto("add")) {

				event = ActionConstants.INPUT_KEY;
			} else {
				enterpriseNews.setId(null);
				enterpriseNews.setEditeTime(new Date());
				epNewsManagerDao.addEpNews(enterpriseNews);

				this.setFather(String.valueOf(enterpriseNews.getEnterpriseColumn().getId()));
				event = this.query();
			}
		}
		title = null;
		return event;
	}

	/**
	 * 修改一条新闻的信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String update() throws Exception {

		String event = "";

		if (!isAdmin()) {
			enterpriseNews.setState("2");// 待审核
		}
		enterpriseNews.setContents(contents);

		if ("2".equals(enterpriseNews.getIsPrimPhoto())) {
			boolean temp = true;
			if(upload != null || uploadImage != null){
				temp = uploadFile("update");
				if(!temp){
					event = "input";
				}else{
					EnterpriseNews news = epNewsManagerDao.getEpNews(enterpriseNews.getId());
					enterpriseNews.setInitTime(news.getInitTime());
					enterpriseNews.setEndTime(news.getEndTime());
					enterpriseNews.setEditeTime(new Date());
					if(enterpriseNews.getPhoto() == null || "".equals(enterpriseNews.getPhoto())){
						enterpriseNews.setPhoto(news.getPhoto());
					}
					if(enterpriseNews.getFileInfo() == null || "".equals(enterpriseNews.getFileInfo())){
						enterpriseNews.setFileInfo(news.getFileInfo());
					}
					epNewsManagerDao.updateEpNews(enterpriseNews);
					event = this.query();
				}
			}else{
				EnterpriseNews news = epNewsManagerDao.getEpNews(enterpriseNews.getId());
				enterpriseNews.setInitTime(news.getInitTime());
				enterpriseNews.setEndTime(news.getEndTime());
				enterpriseNews.setPhoto(news.getPhoto());
				enterpriseNews.setFileInfo(news.getFileInfo());
				enterpriseNews.setEditeTime(new Date());
				epNewsManagerDao.updateEpNews(enterpriseNews);
				event = this.query();
			}
		} else if ("1".equals(enterpriseNews.getIsPrimPhoto()) && uploadImage != null && !uploadPhoto("update")) {
			event = "input";
		} else {
			if (enterpriseNews.getPhoto() == null) {
				enterpriseNews.setPhoto(photo);
			}

			EnterpriseNews news = epNewsManagerDao.getEpNews(enterpriseNews.getId());
			enterpriseNews.setInitTime(news.getInitTime());
			enterpriseNews.setEndTime(news.getEndTime());
			enterpriseNews.setEditeTime(new Date());
			epNewsManagerDao.updateEpNews(enterpriseNews);

			event = this.query();
		}

		title = null;
		return event;
	}

	/**
	 * 删除一条新闻信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		epNewsManagerDao.deleteEpNews(id);
		return this.query();
	}

	/**
	 * 跳转到添加新闻初始化页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add_page() throws Exception {
		if (this.enterpriseNews != null)
			this.setEnterpriseNews(null);
		this.setColumn(this.epColumnManagerDao.getEnterpriseColumn(Long.valueOf(father)));

		return "add_page";
	}

	private boolean checkUploadPhotoSize() throws Exception {
		long maxSize = 20971520L;
		if (getUpload() != null && getUpload().length() > maxSize) {
			addActionMessage("\u4E0A\u4F20\u7684\u6587\u4EF6\u7684\u5BB9\u91CF\u4E0D\u80FD\u5927\u4E8E1MB\uFF01");
			return false;
		} else {
			return true;
		}
	}

	private String filterType(String types[]) {
		if (getUploadContentType() == null)
			return null;
		String fileType = getUploadContentType();
		String as[];
		int j = (as = types).length;
		for (int i = 0; i < j; i++) {
			String type = as[i];
			if (type.equals(fileType))
				return null;
		}

		return "FilterError";
	}

	private String getAllowTypes() {

		allowTypes = "image/bmp,image/x-png,image/png,image/gif,image/pjpeg,image/jpeg";
		return allowTypes;
	}

	private boolean checkUploadPhoto() throws Exception {
		String filterResult = filterType(getAllowTypes().split(","));
		if (filterResult != null) {
			addActionMessage((new StringBuffer(
				"\u60A8\u8981\u4E0A\u4F20\u7684\u6587\u4EF6\u7C7B\u578B\u4E0D\u6B63\u786E\uFF01<br>\u5141\u8BB8\u4E0A\u4F20\u7684\u6587\u4EF6\u7C7B\u578B\u4E3A\uFF1A"))
				.append(getAllowTypes()).toString());
			return false;
		} else {
			return checkUploadPhotoSize();
		}
	}

	/**
	 * 上传图片检查
	 * 
	 * @param how_do
	 * @return
	 * @throws Exception
	 */
	public boolean uploadPhoto(String how_do) throws Exception {
		if (!checkUploadPhoto())
			return false;
		String oldPhoto = null;
		if ("update".equals(how_do))
			oldPhoto = epNewsManagerDao.getEpNews(enterpriseNews.getId()).getPhoto();

		FileOutputStream fos = null;
		FileInputStream fis = null;
		if (uploadImageFileName != null && !uploadImageFileName.equals(""))
			try {
				String inputFileExt = uploadImageFileName.substring(uploadImageFileName.lastIndexOf(".") + 1);
				String newFileName = (new StringBuffer(String.valueOf((new DateHelper()).getRandomNum()))).append(".").append(
					inputFileExt).toString();

				String outputFilePath = (new StringBuffer(String.valueOf(getSaveImgPath()))).append(Constants.FILE_SEPARATOR).append(newFileName)
					.toString();

				enterpriseNews.setPhoto(newFileName);

				/*
				 * 方法一： Image src = javax.imageio.ImageIO.read(upload);
				 * BufferedImage tag = new
				 * BufferedImage(185,152,BufferedImage.TYPE_INT_RGB);
				 * tag.getGraphics().drawImage(src, 0, 0, 185, 152,null); fos =
				 * new FileOutputStream(outputFilePath); JPEGImageEncoder
				 * encoder = JPEGCodec.createJPEGEncoder(fos);
				 * encoder.encode(tag);
				 */

				// 压缩
				if ("1".equals(autoCutPhoto)) {
					Image src = javax.imageio.ImageIO.read(uploadImage);
					src = src.getScaledInstance(185, 152, Image.SCALE_AREA_AVERAGING);
					BufferedImage tag = new BufferedImage(185, 152, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = tag.createGraphics();
					g2.drawImage(src, 0, 0, 185, 152, Color.white, null);
					g2.dispose();

					float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2, -0.125f, -0.125f, -0.125f, -0.125f };
					Kernel kernel = new Kernel(3, 3, kernelData2);
					ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
					tag = cOp.filter(tag, null);
					fos = new FileOutputStream(outputFilePath);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
					encoder.encode(tag);
				} else {
					// 不压缩
					fos = new FileOutputStream(outputFilePath);
					fis = new FileInputStream(getUploadImage());
					byte buffer[] = new byte[1024];
					for (int len = 0; (len = fis.read(buffer)) > 0;)
						fos.write(buffer, 0, len);

					File delFile = new File((new StringBuffer(String.valueOf(getSaveImgPath()))).append(Constants.FILE_SEPARATOR).append(oldPhoto)
						.toString());
					if (delFile.exists()) {
						try {
							delFile.delete();
						} catch (Exception e) {
							return false;
						}
					}
				}

			} catch (Exception e) {
				addActionMessage("\u4E0A\u4F20\u5931\u8D25!");
				return false;
			} finally {
				if (fos != null)
					fos.close();
				if (fis != null)
					fis.close();
			}
		// 清除缓存
		this.setUploadImageFileName(null);
		return true;
	}

	/**
	 * 上传文件检查
	 * 
	 * @param how_do
	 * @return
	 * @throws Exception
	 */
	public boolean uploadFile(String how_do) throws Exception {
		String oldFile = null;
		String oldPhoto = null;

		FileOutputStream fos = null;
		FileInputStream fis = null;

		if ("update".equals(how_do)){
			EnterpriseNews oldEpNews  = epNewsManagerDao.getEpNews(enterpriseNews.getId());
			oldFile = oldEpNews.getFileInfo();
			oldPhoto = oldEpNews.getPhoto();
		}

		if (uploadFileName != null && !uploadFileName.equals("")){
			try {
				String inputFileExt = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
				String newFileName = (new StringBuffer(String.valueOf((new DateHelper()).getRandomNum()))).append(".").append(
					inputFileExt).toString();
				String outputFilePath = (new StringBuffer(String.valueOf(getSaveFilePath()))).append(Constants.FILE_SEPARATOR).append(newFileName)
					.toString();
				// 也是上传文件
				enterpriseNews.setFileInfo(newFileName);
				Long fileSize = getUpload().length() / 1024;
				if(fileSize == 0L){
					fileSize = 1L;
				}
				enterpriseNews.setFileSize(fileSize);
				fos = new FileOutputStream(outputFilePath);
				fis = new FileInputStream(getUpload());
				byte buffer[] = new byte[4096];
				for (int len = 0; (len = fis.read(buffer)) > 0;)
					fos.write(buffer, 0, len);

				File delFile = new File((new StringBuffer(String.valueOf(getSaveFilePath()))).append(Constants.FILE_SEPARATOR).append(oldFile).toString());

				if (delFile.exists()) {
					try {
						delFile.delete();
					} catch (Exception e) {
						return false;
					}
				}
			} catch (Exception e) {
				addActionMessage("\u4E0A\u4F20\u5931\u8D25!");
				return false;
			}
		}
		if(uploadImageFileName != null && !uploadImageFileName.equals("")){
			try {
				String inputFileExt = uploadImageFileName.substring(uploadImageFileName.lastIndexOf(".") + 1);
				String newFileName = (new StringBuffer(String.valueOf((new DateHelper()).getRandomNum()))).append(".").append(
					inputFileExt).toString();

				String outputFilePath = (new StringBuffer(String.valueOf(getSaveImgPath()))).append(Constants.FILE_SEPARATOR).append(newFileName)
					.toString();

				enterpriseNews.setPhoto(newFileName);

				/*
				 * 方法一： Image src = javax.imageio.ImageIO.read(upload);
				 * BufferedImage tag = new
				 * BufferedImage(185,152,BufferedImage.TYPE_INT_RGB);
				 * tag.getGraphics().drawImage(src, 0, 0, 185, 152,null); fos =
				 * new FileOutputStream(outputFilePath); JPEGImageEncoder
				 * encoder = JPEGCodec.createJPEGEncoder(fos);
				 * encoder.encode(tag);
				 */

				// 压缩
				if ("1".equals(autoCutPhoto)) {
					Image src = javax.imageio.ImageIO.read(uploadImage);
					src = src.getScaledInstance(185, 152, Image.SCALE_AREA_AVERAGING);
					BufferedImage tag = new BufferedImage(185, 152, BufferedImage.TYPE_INT_RGB);
					Graphics2D g2 = tag.createGraphics();
					g2.drawImage(src, 0, 0, 185, 152, Color.white, null);
					g2.dispose();

					float[] kernelData2 = { -0.125f, -0.125f, -0.125f, -0.125f, 2, -0.125f, -0.125f, -0.125f, -0.125f };
					Kernel kernel = new Kernel(3, 3, kernelData2);
					ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
					tag = cOp.filter(tag, null);
					fos = new FileOutputStream(outputFilePath);
					JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
					encoder.encode(tag);
				} else {
					// 不压缩
					fos = new FileOutputStream(outputFilePath);
					fis = new FileInputStream(getUploadImage());
					byte buffer[] = new byte[1024];
					for (int len = 0; (len = fis.read(buffer)) > 0;)
						fos.write(buffer, 0, len);

					File delFile = new File((new StringBuffer(String.valueOf(getSaveImgPath()))).append(Constants.FILE_SEPARATOR).append(oldPhoto)
						.toString());
					if (delFile.exists()) {
						try {
							delFile.delete();
						} catch (Exception e) {
							return false;
						}
					}
				}

			} catch (Exception e) {
				addActionMessage("\u4E0A\u4F20\u5931\u8D25!");
				return false;
			} finally {
				if (fos != null)
					fos.close();
				if (fis != null)
					fis.close();
			}
		}
		// 清除缓存
		this.setUploadFileName(null);
		this.setUploadImageFileName(null);
		return true;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	/**
	 * 获取图片保存路径，放在配置文件app.properties
	 * @return
	 * @throws Exception
	 */
	public String getSaveImgPath() throws Exception {
		String imgPath = PropertiesFileReader.get("upload.image.path", "/app.properties");
		String realPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(Constants.SPT+imgPath);
		return realPath;
	}
	
	/**
	 *  获取文件保存路径，放在配置文件app.properties
	 * @return
	 * @throws Exception
	 */
	public String getSaveFilePath() throws Exception {
		String filePath = PropertiesFileReader.get("upload.file.path", "/app.properties");
		String realPath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(Constants.SPT+filePath);
		return realPath;
	}

	public List<EnterpriseNews> getEnterpriseNewsList() {
		return enterpriseNewsList;
	}

	public void setEnterpriseNewsList(List<EnterpriseNews> enterpriseNewsList) {
		this.enterpriseNewsList = enterpriseNewsList;
	}

	public EnterpriseNews getEnterpriseNews() {
		return enterpriseNews;
	}

	public void setEnterpriseNews(EnterpriseNews enterpriseNews) {
		this.enterpriseNews = enterpriseNews;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public EnterpriseColumn getColumn() {
		return column;
	}

	public void setColumn(EnterpriseColumn column) {
		this.column = column;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getInitTime() {
		return initTime;
	}

	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isAdmin() {

		boolean res = false;
		Integer isAdmin = (Integer) ServletActionContext.getContext().getSession().get("isAdmin");
		if (isAdmin != null && isAdmin == 1) {

			res = true;
		}
		return res;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAutoCutPhoto() {
		return autoCutPhoto;
	}

	public void setAutoCutPhoto(String autoCutPhoto) {
		this.autoCutPhoto = autoCutPhoto;
	}

	public String getIsShowInsert() {
		return isShowInsert;
	}

	public void setIsShowInsert(String isShowInsert) {
		this.isShowInsert = isShowInsert;
	}

	public File getUploadImage() {
		return uploadImage;
	}

	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}

	public String getUploadImageFileName() {
		return uploadImageFileName;
	}

	public void setUploadImageFileName(String uploadImageFileName) {
		this.uploadImageFileName = uploadImageFileName;
	}

}