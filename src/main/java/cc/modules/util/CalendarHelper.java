package cc.modules.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日历计算，用于数据库的一周，一个月的数据查询
 * @author John
 *
 */
public class CalendarHelper {
	public List<String> calendDate(String flag){
		Date endDate = new Date();
		//创建基于当前时间的日历对象
		Calendar cl = Calendar.getInstance();
		cl.setTime(endDate);
		//距离今天，一个月内数据
		 if(flag.equals("month")){
		  cl.add(Calendar.MONTH, -1);
		}
		 //距离今天，一周内的数据,如果(flag！=month) &&  (flag！=week), 则查询的就是当天的数据
		if(flag.equals("week")){
		  cl.add(Calendar.DATE, -7);
		 }
		Date startDate = cl.getTime();
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//格式化开始日期和结束日期
		String start = dd.format(startDate);
		String end = dd.format(endDate);
		List<String> calendList = new ArrayList<String>();
		calendList.add(0,start);
		calendList.add(1,end);
		return calendList;
	}

}
