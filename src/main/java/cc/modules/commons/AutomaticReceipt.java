package cc.modules.commons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.entity.ParameterSet;
import cc.messcat.entity.PayOrder;
import cc.messcat.service.pay.PayOrderManagerDao;
import cc.modules.security.MemberInit;
import cc.modules.util.DateHelper;
import cc.modules.util.ObjValid;

public class AutomaticReceipt{
	private static Logger log = LoggerFactory.getLogger(AutomaticReceipt.class);
	private PayOrderManagerDao payOrderManagerDao;
 	
	//定时监控订单的方法
	public void schedule(){
		List<PayOrder> pl = new ArrayList<PayOrder>();
		pl = payOrderManagerDao.findPayOrder();
		ParameterSet p = payOrderManagerDao.findParameterSet();
		Date today = new Date();// 今天
		String timeLimit = "0";
		ParameterSet parameterSet = this.payOrderManagerDao.findParameterSet();
		if(ObjValid.isValid(parameterSet.getConsigneeTime())){
			timeLimit = "-"+parameterSet.getConsigneeTime();
		}
		long limitDate = DateHelper.getDateByCalculateDays(today, Integer.valueOf(timeLimit)).getTime(); // 后台设置的N天前
		log.error("待收货订单有多少笔："+pl.size());
		for(int i=0;i<pl.size();i++){
			if(ObjValid.isValid(pl.get(i).getShippingTime())){
				//如果订单在超过预设的N天前，那么自动确认收货
				if(pl.get(i).getShippingTime().getTime()<limitDate){
					pl.get(i).setBestTime(new Date());
					pl.get(i).setShippingStatus("2");
					this.payOrderManagerDao.updatePayOrder(pl.get(i));
					log.error("自动确认收货的订单ID："+pl.get(i).getId());
				}
			}
		}
		
	}

	public PayOrderManagerDao getPayOrderManagerDao() {
		return payOrderManagerDao;
	}

	public void setPayOrderManagerDao(PayOrderManagerDao payOrderManagerDao) {
		this.payOrderManagerDao = payOrderManagerDao;
	}
}
