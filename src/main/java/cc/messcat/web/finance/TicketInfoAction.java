/*
 * TicketInfoAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-06-01
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.finance;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.TicketInfo;

public class TicketInfoAction extends PageAction {
	
	private Long id;	
	private TicketInfo ticketInfo;	
	private List<TicketInfo> ticketInfos;
	
	
	/**
	 * 查询所有的卡券配置信息
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String retrieveAllTicketInfos() throws Exception {
		try {
			super.pager = this.ticketInfoManagerDao.retrieveAllTicketInfos(pageNo,pageSize);
			this.ticketInfos = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	
	/**
	 * 根据id查询卡券配置信息
	 * @return
	 * @throws Exception
	 */
	public String retrieveTicketInfoById() throws Exception {
		try {
			this.ticketInfo = this.ticketInfoManagerDao.retrieveTicketInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	/**
	 * 跳转到新的页面
	 * @return
	 * @throws Exception
	 */
	public String newPage() throws Exception {
		return "newPage";
	}
	
	/**
	 * 查看配置详细信息
	 * @return
	 * @throws Exception
	 */
	public String viewPage() throws Exception {
		try {
			this.ticketInfo = this.ticketInfoManagerDao.retrieveTicketInfo(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	/**
	 * 添加卡券配置
	 * @return
	 * @throws Exception
	 */
	public String newTicketInfo() throws Exception {
		try {
			this.ticketInfoManagerDao.addTicketInfo(this.ticketInfo);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllTicketInfos();
	}
	
	
	/**
	 * 编辑卡券信息
	 * @return
	 * @throws Exception
	 */
	public String editTicketInfo() throws Exception {
		try {
			TicketInfo ticketInfo0 = this.ticketInfoManagerDao.retrieveTicketInfo(this.id);
			ticketInfo0.setTicketAmount(this.ticketInfo.getTicketAmount());
			ticketInfo0.setRestrictAmount(this.ticketInfo.getRestrictAmount());
			ticketInfo0.setRestrictDay(this.ticketInfo.getRestrictDay());

			this.ticketInfoManagerDao.modifyTicketInfo(ticketInfo0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	
	/**
	 * 删除卡券配置信息
	 * @return
	 * @throws Exception
	 */
	public String delTicketInfo() throws Exception {
		try {
			this.ticketInfoManagerDao.removeTicketInfo(this.id);
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

	public TicketInfo getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(TicketInfo ticketInfo) {
		this.ticketInfo = ticketInfo;
	}

	public List<TicketInfo> getTicketInfos() {
		return ticketInfos;
	}

	public void setTicketInfos(
			List<TicketInfo> ticketInfos) {
		this.ticketInfos = ticketInfos;
	}

}