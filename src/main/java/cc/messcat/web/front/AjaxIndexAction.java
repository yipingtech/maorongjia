// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 16:36:14
// Home Page : http://members.fortunecity.com/neshkov/dj.html - Check often for
// new version!
// Decompiler options: packimports(3)
// Source File Name: AjaxIndexAction.java

package cc.messcat.web.front;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cc.modules.commons.PageAction;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.front.EIndexNews;
import cc.messcat.front.Ecolumn;

public class AjaxIndexAction extends PageAction {

	private static final long serialVersionUID = -70638331100381846L;
	private List columnlist;
	private List linklist;
	private String frontNum;
	private String frontType;
	private EnterpriseColumn frontNumColumn;
	private List newlist;
	private EnterpriseNews news;
	private String topResult;
	private String tempTitle;
	private String tempColumnId;
	private EIndexNews eIndexNews;
	private String epColumnId;
	private String columnName;
	private String subColumnId;
	private String subColumnName;
	private String newsId;

	public AjaxIndexAction() {
		columnlist = new ArrayList();
		linklist = new ArrayList();
		frontNum = "";
		frontType = "";
		frontNumColumn = new EnterpriseColumn();
		newlist = new ArrayList();
		news = new EnterpriseNews();
		topResult = "";
		eIndexNews = new EIndexNews();
	}

	public String update_column() throws Exception {
		columnlist = epColumnManagerDao.findSubColumn(0L);
		return "success";
	}

	public String update_links() throws Exception {
		linklist = epLinksManagerDao.getEpLinksByClassAndSize(Long.valueOf(13L));
		return "success";
	}

	public String update_new_by_frontnum() throws Exception {
		frontNumColumn = epColumnManagerDao.getEnterpriseColumn(frontNum);
		if ("1".equals(frontType)) {
			if (frontNumColumn != null) {
				newlist = epNewsManagerDao.findEpNewsByFrontNum(Long.valueOf(8L), frontNumColumn);
				if (newlist.size() == 0)
					newlist.add(new EnterpriseNews());
			}
		} else if ("2".equals(frontType) && frontNumColumn != null)
			news = epNewsManagerDao.getEpNewsByColumnId(frontNumColumn.getId());
		return "success";
	}

	public String execute() throws Exception {
		eIndexNews.setSubEpColumn(null);
		List epc = epColumnManagerDao.findSubColumn(0L);
		int i = 0;
		eIndexNews.getEcolumn().clear();
		for (Iterator iterator = epc.iterator(); iterator.hasNext();) {
			EnterpriseColumn epColumn = (EnterpriseColumn) iterator.next();
			Ecolumn ec = new Ecolumn();
			ec.setEnterpriseColumn(epColumn);
			ec.setEnterpriseColumnList(epColumnManagerDao.findSubColumn(epColumn.getId()));
			eIndexNews.getEcolumn().add(i, ec);
			i++;
		}

		eIndexNews.setEnterpriseLinks(epLinksManagerDao.getEpLinksByClassAndSize(Long.valueOf(13L)));
		eIndexNews.setPhotoNews(epNewsManagerDao.findFrontEpNews(Long.valueOf(8L), Long.valueOf(1L), "1", "-1", "-1", Long
			.valueOf(-1L), "1"));
		return "success";
	}

	private void splitPage() throws Exception {
		if (subColumnName != null)
			subColumnName = null;
		// List epc =
		// epColumnManagerDao.findFrontEnterpriseColumn(Long.valueOf(0L));
		EnterpriseColumn ec = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(subColumnId));
		subColumnName = ec.getNames();
		EnterpriseColumn temp1 = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(ec.getFather().longValue()));
		columnName = temp1.getNames();
		EnterpriseNews temp = new EnterpriseNews();
		temp.setState("1");
		EnterpriseColumn tempColumn = new EnterpriseColumn();
		tempColumn.setId(Long.valueOf(subColumnId));
		temp.setEnterpriseColumn(tempColumn);
		super.pager = epNewsManagerDao.findEpNews(pageSize, pageNo, temp);
		List resultList = super.pager.getResultList();
		eIndexNews.setNewsList(resultList);
		if (subColumnId != null) {
			tempColumnId = subColumnId;
			subColumnId = null;
		}
	}

	public String news() throws Exception {
		eIndexNews.setNews(epNewsManagerDao.getEpNews(Long.valueOf(newsId)));
		return "news";
	}

	public String news_more() throws Exception {
		initColumn();
		EnterpriseColumn temps = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(epColumnId));
		eIndexNews.setSubEpColumn(epColumnManagerDao.findSubColumn(Long.valueOf(temps.getId().longValue())));
		columnName = temps.getNames();
		EnterpriseNews temp = new EnterpriseNews();
		temp.setState("1");
		EnterpriseColumn tempColumn = new EnterpriseColumn();
		if (subColumnId != null)
			tempColumn.setId(Long.valueOf(subColumnId));
		else
			tempColumn.setId(Long.valueOf(epColumnId));
		temp.setEnterpriseColumn(tempColumn);
		super.pager = epNewsManagerDao.findEpNews(pageSize, pageNo, temp);
		List resultList = super.pager.getResultList();
		eIndexNews.setNewsList(resultList);
		if (subColumnId != null) {
			tempColumnId = subColumnId;
			subColumnId = null;
		}
		return "news_more";
	}

	private void initColumn() throws Exception {
		if (subColumnName != null)
			subColumnName = null;
		List epc = epColumnManagerDao.findSubColumn(0L);
		int i = 0;
		eIndexNews.getEcolumn().clear();
		for (Iterator iterator = epc.iterator(); iterator.hasNext();) {
			EnterpriseColumn epColumn = (EnterpriseColumn) iterator.next();
			Ecolumn ecTemp = new Ecolumn();
			ecTemp.setEnterpriseColumn(epColumn);
			ecTemp.setEnterpriseColumnList(epColumnManagerDao.findSubColumn(epColumn.getId()));
			eIndexNews.getEcolumn().add(i, ecTemp);
			i++;
		}

		if (subColumnId != null) {
			EnterpriseColumn ec = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(subColumnId));
			subColumnName = ec.getNames();
			epColumnId = String.valueOf(ec.getFather());
			EnterpriseColumn temp = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(ec.getFather().longValue()));
			columnName = temp.getNames();
		} else {
			EnterpriseColumn temp = epColumnManagerDao.getEnterpriseColumn(Long.valueOf(epColumnId));
			columnName = temp.getNames();
		}
	}

	public String newsView() throws Exception {
		eIndexNews.setNews(epNewsManagerDao.getEpNews(Long.valueOf(newsId)));
		return "newsView";
	}

	public EIndexNews getEIndexNews() {
		return eIndexNews;
	}

	public void setEIndexNews(EIndexNews indexNews) {
		eIndexNews = indexNews;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getSubColumnId() {
		return subColumnId;
	}

	public void setSubColumnId(String subColumnId) {
		this.subColumnId = subColumnId;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getSubColumnName() {
		return subColumnName;
	}

	public void setSubColumnName(String subColumnName) {
		this.subColumnName = subColumnName;
	}

	public String getEpColumnId() {
		return epColumnId;
	}

	public void setEpColumnId(String epColumnId) {
		this.epColumnId = epColumnId;
	}

	public String getTempTitle() {
		return tempTitle;
	}

	public void setTempTitle(String tempTitle) {
		this.tempTitle = tempTitle;
	}

	public String getTempColumnId() {
		return tempColumnId;
	}

	public void setTempColumnId(String tempColumnId) {
		this.tempColumnId = tempColumnId;
	}

	public String getTopResult() {
		return topResult;
	}

	public void setTopResult(String topResult) {
		this.topResult = topResult;
	}

	public List getColumnlist() {
		return columnlist;
	}

	public void setColumnlist(List columnlist) {
		this.columnlist = columnlist;
	}

	public List getLinklist() {
		return linklist;
	}

	public void setLinklist(List linklist) {
		this.linklist = linklist;
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public List getNewlist() {
		return newlist;
	}

	public void setNewlist(List newlist) {
		this.newlist = newlist;
	}

	public EnterpriseColumn getFrontNumColumn() {
		return frontNumColumn;
	}

	public void setFrontNumColumn(EnterpriseColumn frontNumColumn) {
		this.frontNumColumn = frontNumColumn;
	}

	public EnterpriseNews getNews() {
		return news;
	}

	public void setNews(EnterpriseNews news) {
		this.news = news;
	}

	public String getFrontType() {
		return frontType;
	}

	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}

}