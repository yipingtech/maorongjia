/*
 * StockAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2015-04-16
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.product;

import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.Stock;

public class StockAction extends PageAction {
	
	private Long id;
	
	private Stock stock;
	
	private List<Stock> stocks;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllStocks() throws Exception {
		try {
			super.pager = this.stockManagerDao.retrieveStocksPager(pageSize, pageNo);
			this.stocks = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveStockById() throws Exception {
		try {
			this.stock = this.stockManagerDao.retrieveStock(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.stock = this.stockManagerDao.retrieveStock(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newStock() throws Exception {
		try {
			this.stockManagerDao.addStock(this.stock);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "edit_success";
	}
	
	public String editStock() throws Exception {
		try {
			Stock stock0 = this.stockManagerDao.retrieveStock(this.id);
			stock0.setProductAttrIds(this.stock.getProductAttrIds());
			stock0.setAmount(this.stock.getAmount());
			stock0.setPrice(this.stock.getPrice());
			stock0.setAddTime(this.stock.getAddTime());
			stock0.setEditTime(this.stock.getEditTime());
			stock0.setStatus(this.stock.getStatus());

			this.stockManagerDao.modifyStock(stock0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return "edit_success";
	}
	
	public String delStock() throws Exception {
		try {
			this.stockManagerDao.removeStock(this.id);
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

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(
			List<Stock> stocks) {
		this.stocks = stocks;
	}

}