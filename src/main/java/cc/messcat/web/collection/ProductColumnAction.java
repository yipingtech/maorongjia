/*
 * ProductColumnAction.java
 * 
 * Create by MCGT
 * 
 * Create time 2014-02-13
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.web.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.McProductInfo;
import cc.messcat.entity.ProductColumn;
import cc.modules.commons.PageAction;
import cc.modules.commons.Pager;

public class ProductColumnAction extends PageAction {
	
	private Long id;
	
	private ProductColumn productColumn;
	
	private McProductInfo productInfo;
	
	private List<ProductColumn> productColumns;
	
	/**
	 * 栏目id
	 * */
	private String colId;
	
	/**
	 * 没有关联某栏目的产品
	 * */
	private List<McProductInfo> noProductColumns;
	
	/**
	 * 某栏目下的产品
	 * */
	private List<ProductColumn> readyProductColumns;
	
	private Pager pager2;
	protected int pageSize2;
	protected int pageNo2;
	
	
	
	public ProductColumnAction() {
		pageSize2 = 100;
		pageNo2 = 1;
	}

	@SuppressWarnings("unchecked")
	public String retrieveAllProductColumns() throws Exception {
		try {
			super.pager = this.productColumnManagerDao.retrieveProductColumnsPager(pageSize, pageNo);
			this.productColumns = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrieveProductColumnById() throws Exception {
		try {
			this.productColumn = this.productColumnManagerDao.retrieveProductColumn(id);
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
			this.productColumn = this.productColumnManagerDao.retrieveProductColumn(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 添加产品-栏目-关联 
	 */
	public String newProductColumn() throws Exception {
		try {
			this.productColumnManagerDao.addProductColumn(this.productColumn);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return "query";
	}
	
	/**
	 * @return
	 * 
	 * 查询产品-栏目关联
	 */
	public String queryProductColumn(){
		try {
			if (colId!=null) {
				EnterpriseColumn ec =  epColumnManagerDao.getEnterpriseColumn(Long.valueOf(colId));
				pager = productColumnManagerDao.findProductByColumnPro(productInfo, ec, pageSize, pageNo);
				pager2 = productColumnManagerDao.findProductByNoColumn(productInfo, ec, pageSize2, pageNo2);
				
				readyProductColumns =  pager.getResultList();
				noProductColumns =  pager2.getResultList();
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("查询失败, 请检查参数是否都正确！");
		}
		return "column_productinfo";
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 
	 */
	public String editProductColumn() throws Exception {
		try {
			ProductColumn productColumn0 = this.productColumnManagerDao.retrieveProductColumn(this.id);

			this.productColumnManagerDao.modifyProductColumn(productColumn0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllProductColumns();
	}
	
	/**
	 * @return
	 * @throws Exception
	 * 删除产品-栏目-关联
	 */
	public String delProductColumn() throws Exception {
		try {
			this.productColumnManagerDao.removeProductColumn(this.id);
			addActionMessage("Delete successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
			ex.printStackTrace();
		}
		return queryProductColumn();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductColumn getProductColumn() {
		return productColumn;
	}

	public void setProductColumn(ProductColumn productColumn) {
		this.productColumn = productColumn;
	}

	public List<ProductColumn> getProductColumns() {
		return productColumns;
	}

	public void setProductColumns(
			List<ProductColumn> productColumns) {
		this.productColumns = productColumns;
	}

	
	public String getColId() {
		return colId;
	}

	
	public void setColId(String colId) {
		this.colId = colId;
	}

	
	public List<McProductInfo> getNoProductColumns() {
		return noProductColumns;
	}

	
	public void setNoProductColumns(List<McProductInfo> noProductColumns) {
		this.noProductColumns = noProductColumns;
	}

	
	public List<ProductColumn> getReadyProductColumns() {
		return readyProductColumns;
	}

	
	public void setReadyProductColumns(List<ProductColumn> readyProductColumns) {
		this.readyProductColumns = readyProductColumns;
	}

	
	public Pager getPager2() {
		return pager2;
	}

	
	public void setPager2(Pager pager2) {
		this.pager2 = pager2;
	}

	
	public int getPageSize2() {
		return pageSize2;
	}

	
	public void setPageSize2(int pageSize2) {
		this.pageSize2 = pageSize2;
	}

	
	public int getPageNo2() {
		return pageNo2;
	}

	
	public void setPageNo2(int pageNo2) {
		this.pageNo2 = pageNo2;
	}

	public McProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(McProductInfo productInfo) {
		this.productInfo = productInfo;
	}

}