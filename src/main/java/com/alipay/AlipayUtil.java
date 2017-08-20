package com.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AlipayUtil {
	private static Logger log = LoggerFactory.getLogger(AlipayUtil.class); 
	/**
	 * @param params
	 * @return
	 * 通过params参数初始化订单信息
	 */
	public static Map<String, String> forwardToCp(Map<String, String> params) {
		log.info("forwardToCp begin: " + new Date()+" --" + System.currentTimeMillis());
		Map<String, String> map = null;
		StringBuffer forwardParam = new StringBuffer();
		if (params != null && !params.isEmpty()) {
			/*result 支付结果,1代表成功0代表失败
			money 支付金额 单位分
			order 订单号
			mid 支付用户帐号
			time 时间戳 long 系统当前时间毫秒级
			sign md5验证串（加密字段）格式为order=_&money=_&mid=_&time=_&result=_&ext=_&key=_ 等号后的下划线位置为具体参数值 key由你们来定义
			ext 客户端传的参数*/
			
			String order = params.get("out_trade_no");      //订单号
			String money = params.get("total_fee");
			if (null != money && !money.equals("")) {
				Float f = Float.valueOf(money)*100;
				money = String.valueOf(f.intValue());
			}
			//String time = String.valueOf(GameConstants.dateStringToMillis(params.get("gmt_payment")));
			String bodyStr = params.get("body");
			String ext = "";  //客户端传的参数
			String channel_id = "";   		    //渠道id
			String game_id = "";               //游戏id     --暂时没用
			String mid = "";                   // 支付用户帐号
			String sign  = "";                 // 支付用户帐号
			String key = "";                   //先使用渠道编号作为加密的key
			String body = "";                   
			String divice_id = "";                   
			String pass_id = "";                   
			String result;                     //支付结果,1代表成功0代表失败
			
			if (null != order && !order.equals("")) {
				result = "1";
			}else {
				result = "0";
			}
			
			// 把bodyStr解析出来，把值传给对应的参数里面
			if (bodyStr != null && !bodyStr.trim().equals("")) {
				body = bodyStr.substring(0, bodyStr.indexOf(";"));
				bodyStr = bodyStr.substring(bodyStr.indexOf(";")+1, bodyStr.length());
				
				channel_id = bodyStr.substring(0, bodyStr.indexOf(";"));
				bodyStr = bodyStr.substring(bodyStr.indexOf(";")+1, bodyStr.length());
				
				divice_id = bodyStr.substring(0, bodyStr.indexOf(";"));
				bodyStr = bodyStr.substring(bodyStr.indexOf(";")+1, bodyStr.length());
				
				game_id = bodyStr.substring(0, bodyStr.indexOf(";"));
				bodyStr = bodyStr.substring(bodyStr.indexOf(";")+1, bodyStr.length());
				
				pass_id = bodyStr.substring(0, bodyStr.indexOf(";"));
			
				//key = doSignature("200001");
				key = doSignature(game_id);
				//game_id = "100009";
				bodyStr = bodyStr.substring(bodyStr.indexOf(";") + 1, bodyStr.length());
				ext = bodyStr.substring(0, bodyStr.length());
				//ext = "102,190,3";
				//mid = bodyStr.substring(0, bodyStr.indexOf(";"));
				mid = "234234234";
			}
			
			if (channel_id.equals("") || game_id.equals("")) {
				log.info("数据传输错误");
				return null;
			}
			
			//key = GameSign.SIGNMAP.get("100009");
			
//			sign = "order="+ order +"&money="+ money +"&mid="+ mid +"&time="+ time +"&result="+ result +"&ext="+ ext +"&key="+ key;
//			sign = order + money + mid + time + result + ext + key;
			//log.info("加密前："+sign);
			sign = doSignature(sign);
			//log.info("加密后："+sign);
			
			forwardParam.append("result="+result);
			forwardParam.append("&order="+order);
			forwardParam.append("&money="+money);
//			forwardParam.append("&time="+time);
			forwardParam.append("&ext="+ext);
			forwardParam.append("&mid="+mid);
			forwardParam.append("&sign="+sign);
			
			
			map = new HashMap<String, String>();
//			map.put("url", GameSign.GAMEURL.get(game_id));
			map.put("param", forwardParam.toString());
			map.put("gameId", game_id);
		}
		
		log.info("forwardToCp end: " + new Date()+" -- " + System.currentTimeMillis());
		return map;
	}
	
	 /**
     * @param params
     * @return String
     * 通过params进行加密
     */
    private static String doSignature(String param){
    	return DigestUtils.md5Hex(param);
    }
    
    
    
    public static void main(String[] args) {
    	PrintWriter out = null;
    	BufferedReader in = null;
    	String urlString = "http://192.168.1.116:8080/fengmi/jsps/ws_notify_url.jsp";
		String paramString = "body=山寨国产红苹果手机Hiphone I9 JAVA QQ 后台 飞信 炒股UC;200001;;100009;;102,190,3&buyer_email=dlwdgl@gmail.com&buyer_id=2088602315385429&discount=0.00&gmt_create=2013-08-22 14:45:23&gmt_payment=2013-08-22 14:45:23&is_total_fee_adjust=N&notify_time=2013-08-22 14:45:24&notify_type=trade_status_sync&out_trade_no=03453452345256&payment_type=8&price=1.00&quantity=1&seller_email=alipayrisk18@alipay.com&seller_id=2088501624816263&subject=苹果手机 山寨国产红苹果手机Hiphone I9 JAVA QQ后台  飞信  炒股UC&total_fee=1.00&trade_no=2013082244524842&trade_status=TRADE_SUCCESS&use_coupon=N ";
		String result="";
		/*if (counter==1) {
			paramString = "result=1&order=11130502000006310278&money=3000&time=1398676091511&ext=102,190,3&mid=234234234&sign=c7e27a1e153ed75b01f521403285d2d0";
		}
		log.info("counter : "+counter);*/
		log.info(urlString + " - " + paramString);
		// 建立并打开连接
		URL url;
		try {
			url = new URL(urlString);
			URLConnection urlConn = url.openConnection();

			// 像该url发送请求参数
			urlConn.setDoOutput(true);
			out = new PrintWriter(urlConn.getOutputStream());
			out.print(paramString);
			out.flush();

			// 获取返回的信息
			in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			log.info("return data："+result);
			//如果发送成功或者发送错误，则停止定时器
			//counter++;
		} catch (Exception e) {
			log.info("timer connect fail");
			e.printStackTrace();
		}
	}
}
