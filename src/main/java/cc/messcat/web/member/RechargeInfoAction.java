package cc.messcat.web.member;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.Member;
import cc.messcat.entity.RechargeInfo;

public class RechargeInfoAction extends PageAction {

	private static final long serialVersionUID = 1L;
	private Long id;	
	private RechargeInfo rechargeInfo;	
	private List<RechargeInfo> rechargeInfos;
	private Member member;
	
	
	@SuppressWarnings("unchecked")
	public String retrieveAllRechargeInfos() throws Exception {
		try {
			super.pager = this.rechargeInfoManagerDao.retrieveRechargeInfosPager(pageSize, pageNo);
			this.rechargeInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//根据id查询
	public String retrieveRechargeInfoById() throws Exception {
		try {
			this.rechargeInfo = this.rechargeInfoManagerDao.retrieveRechargeInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	//跳转页面
	public String newPage() throws Exception {
		return "newPage";
	}
	
	//显示单个记录详细信息
	public String viewPage() throws Exception {
		try {
			this.rechargeInfo = this.rechargeInfoManagerDao.retrieveRechargeInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	//添加记录
	public String newRechargeInfo() throws Exception {
		try {
			rechargeInfo.setMemberId(member);         //关联用户
			rechargeInfoManagerDao.addRechargeInfo(rechargeInfo);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	//商家查询单个用户的所有余额记录(分页)
	public String queryRechargeInfoByMember() throws Exception {
		try {
			member=memberManagerDao.retrieveMemberByLoginName(member);
			pager = rechargeInfoManagerDao.retrieveAllRechargeInfos(pageNo, pageSize, member);
			rechargeInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
		
	//商家查询所有用户的余额记录（分页）
	public String queryRechargeByProducter() throws Exception {
		try {
			//不要获取member
			pager = rechargeInfoManagerDao.retrieveAllRechargeInfos(pageNo, pageSize, member);
			rechargeInfos=pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
		
	//用户查询自己的余额记录
	public String queryAllRechargeByUser() throws Exception {
		try {
			//要获取member
			rechargeInfos = rechargeInfoManagerDao.retrieveAllRechargeInfos(pageNo, pageSize, member).getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	//编辑充值记录信息
	public String editRechargeInfo() throws Exception {
		try {
			RechargeInfo rechargeInfo0 = this.rechargeInfoManagerDao.retrieveRechargeInfo(this.id);
			rechargeInfo0.setRechargeAmount(this.rechargeInfo.getRechargeAmount());
			rechargeInfo0.setRechargeTime(this.rechargeInfo.getRechargeTime());
			rechargeInfo0.setRechargeType(this.rechargeInfo.getRechargeType());
			rechargeInfo0.setRechargeCard(this.rechargeInfo.getRechargeCard());
			rechargeInfo0.setRechargeComments(this.rechargeInfo.getRechargeComments());
			rechargeInfo0.setStatus(this.rechargeInfo.getStatus());

			this.rechargeInfoManagerDao.modifyRechargeInfo(rechargeInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	//删除记录信息
	public String delRechargeInfo() throws Exception {
		try {
			this.rechargeInfoManagerDao.removeRechargeInfo(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return "edit_success";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RechargeInfo getRechargeInfo() {
		return rechargeInfo;
	}

	public void setRechargeInfo(RechargeInfo rechargeInfo) {
		this.rechargeInfo = rechargeInfo;
	}

	public List<RechargeInfo> getRechargeInfos() {
		return rechargeInfos;
	}

	public void setRechargeInfos(
			List<RechargeInfo> rechargeInfos) {
		this.rechargeInfos = rechargeInfos;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}