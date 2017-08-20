package cc.messcat.dao.member;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.AgentOrder;
import cc.messcat.entity.CashInfo;
import cc.messcat.entity.Member;
import cc.modules.commons.Pager;
import cc.modules.util.ObjValid;

public class MemberDaoImpl extends BaseDaoImpl implements MemberDao {
	private static Logger log = LoggerFactory.getLogger(MemberDaoImpl.class); 
	
	/**
	 * 查询所有用户除了代理商
	 * */
	public Pager queryMember(int pageSize,int pageNo){
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from Member m where m.status =1");
		Query query = session.createQuery(sql.toString());
		query.setFirstResult(pageSize * (pageNo - 1));
		query.setMaxResults(pageSize);
		List result = query.list();
		int rowCount = this.queryRowCount();
		return new Pager(pageSize, pageNo, rowCount, result);
	}
	public int queryRowCount(){
		int rowCount = 0;
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from Member m where m.status =1");
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		rowCount = result.size();
 		return rowCount;
	}

	//注册用户
	public void save(Member member) {
		getHibernateTemplate().save(member);
	}
	
	//修改用户根据用户对象
	public void update(Member member) {
		getHibernateTemplate().update(member);
	}
	
	//查询用户根据id
	public Member get(Long id) {
		return (Member) getHibernateTemplate().get(Member.class, id);
	}
	
	//联合查询三级客户 ----只限分销商(不限)
	public Pager queryUnionMemberFather(Member member,int pageStart, int pageSize){
/*		String sql = "SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' and n2.DISTRIBUTOR='1' UNION " +
				     "SELECT n3.*  FROM member n3,(SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' and n2.DISTRIBUTOR='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' and n3.DISTRIBUTOR='1' UNION " +
				     "SELECT n4.*  FROM member n4,(SELECT n3.*  FROM member n3,(SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' and n2.DISTRIBUTOR='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' and n3.DISTRIBUTOR='1') t3 WHERE t3.id = n4.MEMBER_FATHER and n4.STATUS='1' and n4.DISTRIBUTOR='1'";*/
		/*String sql = "SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' UNION " +
				"SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' UNION " +
				"SELECT n4.id  FROM member n4,(SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1') t3 WHERE t3.id = n4.MEMBER_FATHER and n4.STATUS='1'";*/
		//联合查询二级客户 ----只限分销商(不限)
		String sql = "SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' UNION " +
				"SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' ";
		int pageIndex=(pageStart-1)*pageSize;
		return new Pager(pageSize, pageStart, this.getSession().createSQLQuery(sql).list().size(),
				this.getSession().createSQLQuery(sql).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	//联合查询三级客户  -----分销商加粉丝
	public Pager queryAllTeam(Member member,int pageStart, int pageSize){
		/*String sql = "SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1'  UNION " +
				"SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' ) t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1'  UNION " +
				"SELECT n4.id  FROM member n4,(SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' ) t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' ) t3 WHERE t3.id = n4.MEMBER_FATHER and n4.STATUS='1' ";*/
		/*String sql = "SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' UNION " +
				"SELECT n3.*  FROM member n3,(SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' UNION " +
				"SELECT n4.*  FROM member n4,(SELECT n3.*  FROM member n3,(SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1') t3 WHERE t3.id = n4.MEMBER_FATHER and n4.STATUS='1'";*/
		//int pageIndex=(pageStart-1)*pageSize;
		//联合查询二级客户  -----分销商加粉丝
		String sql = "SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1'  UNION " +
				"SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' ) t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' ";
		log.info("");
		int pageIndex =0;
		pageSize = 10;
		pageStart = 1;
		return new Pager(pageSize, pageStart, this.getSession().createSQLQuery(sql).list().size(),
				this.getSession().createSQLQuery(sql).setFirstResult(pageIndex).list());
	}
	
	//分开查询三级客户  -->二级
	public Pager queryThreeMember(Member member,int pageStart, int pageSize,int flag){
		String sql ="";
		if (flag==1) {
			sql+="SELECT n2.*  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1' ORDER BY n2.id desc";
		}else if (flag==2) {
			sql+="SELECT n3.*  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1' ORDER BY n3.id desc";
		}/*else if (flag==3) {
			sql+="SELECT n4.*  FROM member n4,(SELECT n3.id  FROM member n3,(SELECT n2.id  FROM member n2 WHERE n2.MEMBER_FATHER ="+member.getId()+" and n2.STATUS='1') t2 WHERE t2.id = n3.MEMBER_FATHER and n3.STATUS='1') t3 WHERE t3.id = n4.MEMBER_FATHER and n4.STATUS='1' ORDER BY n4.id desc";
		}*/
		int pageIndex=(pageStart-1)*pageSize;
	return new Pager(pageSize, pageStart, this.getSession().createSQLQuery(sql).list().size(),
			this.getSession().createSQLQuery(sql).addEntity(Member.class).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	
	//微信号、会员卡号和会员级别模糊查询（会员列表）
	@Override
	public Pager queryCardAndRankByLike(int pageStart, int pageSize,Member member) {
		int pageIndex=(pageStart-1)*pageSize;
		StringBuffer hql = new StringBuffer("from Member as p where p.status='1' ");
		if (ObjValid.isValid(member)) {
			//微信号不为空，会员卡号和会员等级为空
			if (ObjValid.isValid(member.getAgent())) {
				hql.append(" and p.agent is not null");
			}
			if (ObjValid.isValid(member.getDistributor())){
				hql.append(" and p.distributor = '1' and  p.firstFather is null");
			}
			
			if (ObjValid.isValid(member.getNickname())) {
				hql.append(" and p.nickname like '%"+member.getNickname()+"%'");
			}
			
			if (ObjValid.isValid(member.getCardNum())) {
				hql.append(" and p.cardNum like '%"+member.getCardNum()+"%'");
			}
			
//			if (ObjValid.isValid(member.getId())) {
//				hql.append(" and p.id like '%"+member.getId()+"%'");
//			}
			if (ObjValid.isValid(member.getId())) {
				hql.append(" and p.id like '%"+member.getId()+"%'");
			}
			if (ObjValid.isValid(member.getRank())) {
				hql=mySql(hql, member);		
			}
		}
		hql.append(" order by p.id ");
		log.info(hql.toString());
		Session session = this.getSession();
		return new Pager(pageSize, pageStart, queryTotalRecord("select count(*) "+hql.toString()),
				session.createQuery(hql.toString()).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
		
		//订单分类sql拼接
		public StringBuffer mySql(StringBuffer hql,Member member){
			hql.append(" and ");
		 if (member.getRank().equals("1")) {
				hql.append("p.rank='1'");
			}
			else if (member.getRank().equals("2")) {
				hql.append("p.rank='2'");
			}
			else if (member.getRank().equals("3")) {
				hql.append("p.rank='3'");
			}
			else if (member.getRank().equals("4")) {
				hql.append("p.rank='4'");
			}
			return hql;	
		}


	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(Member.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public Pager checkAllAgent(int pageSize, int pageNo) {
        Session session = this.getSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" from Member m where m.agent is not null");
        Query query = session.createQuery(sql.toString());
        if(pageSize!=-1&&pageNo!=-1){
        	query.setFirstResult(pageSize * (pageNo - 1));
    		query.setMaxResults(pageSize);
        }
        List result = query.list();
        int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public boolean findAgent(String address) {
		boolean flag = false;
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append(" from Member m where m.agent = "+address);
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		if(result!=null){
			return true;
		}
		return false;
	}
	@Override
	public Pager findMyFans(int pageSize, int pageNo, Member member) {
		String sql = "from Member m where m.memberFather.id = "+member.getId()+" and m.distributor ='0' ";
	    int pageIndex=(pageNo-1)*pageSize;
	    Session session = this.getSession();
	    Query query = session.createQuery(sql.toString());
        query.setFirstResult(pageSize * (pageNo - 1));
    	query.setMaxResults(pageSize);
        List result = query.list();
        int rowCount = result.size();
		return new Pager(pageSize, pageNo, rowCount, result);	
		//return new Pager(pageSize, pageNo, this.getSession().createSQLQuery(sql).addEntity(Member.class).list().size(),
		//this.getSession().createSQLQuery(sql).addEntity(Member.class).setMaxResults(pageSize).setFirstResult(pageIndex).list());
	}
	@Override
	public CashInfo findCashInfoId(Long id) {
			return (CashInfo) getHibernateTemplate().get(CashInfo.class, id);
	}
	/* (non-Javadoc)
	 * 计算N天之前的佣金和提成
	 * @see cc.messcat.dao.member.MemberDao#queryMoneyToSend(cc.messcat.entity.Member, java.lang.String)
	 */
	@Override
	public Double queryMoneyToSend(Member member, String beginTime) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(COMMISSION) FROM commission_info c WHERE c.status = '1' AND c.MEMBER_ID = '"+member.getId()+"' AND (c.COMMISSION_STATUS = '3' OR c.COMMISSION_STATUS = '0') "
			+ "AND c.PAY_ORDER_ID IN (select ID from pay_order p where p.BEST_TIME < '"+beginTime+"')");
		Query query = session.createSQLQuery(sql.toString());
		Double obj = query.uniqueResult()==null?0:(Double)query.uniqueResult();
		return obj.doubleValue();
	}
	/* 
	 *  查出下一级会员
	 */
	@Override
	public List<Member> findMember(Member member) {
		String sql = "from Member m where m.memberFather.id = "+member.getId();
	    Session session = this.getSession();
	    Query query = session.createQuery(sql.toString());
        List result = query.list();
		return (List<Member>)result;	
		
	}
	@Override
	public void saveAgentOrder(AgentOrder agentOrder) {
		//this.getHibernateTemplate().save("agent_order", agentOrder);
		this.getHibernateTemplate().save("AgentOrder", agentOrder);
	}
	@Override
	public AgentOrder receiveAgentOrder(Long id) {
		String sql = "from AgentOrder a where a.id = "+id;
		Session session = this.getSession();
		Query query = session.createQuery(sql.toString());
		List result = query.list();
		if(ObjValid.isValid(result)){
			return (AgentOrder) result.get(0);
		}
		return null;
	}
	@Override
	public double queryAgentMoneyToSend(Member member,String beginTime) {
		Session session = this.getSession();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(COMMISSION) FROM commission_info c WHERE c.status = '1' AND c.MEMBER_ID = '"+member.getId()+"' AND c.COMMISSION_STATUS = '4'  "
			+ " AND c.ADD_TIME  < '"+beginTime+"'");
		Query query = session.createSQLQuery(sql.toString());
		Double obj = query.uniqueResult()==null?0:(Double)query.uniqueResult();
		return obj.doubleValue();
	}
	

}