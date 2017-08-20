package cc.messcat.web.front;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcloud.msg.http.HttpSender;


public class HttpSenderTest {
	private static Logger log = LoggerFactory.getLogger(HttpSenderTest.class); 
	public static void main(String[] args) {
		String uri = "http://222.73.117.158/msg/index.jsp";//应用地址
		String account = "jiekou-clcs-005";//账号
		String pswd = "Clwh1681688888";//密码
		String mobiles = "18613123721,18826411783";//手机号码，多个号码使用","分割
		String content = "亲爱的用户，您的验证码是1234567，5分钟内有效。";//短信内容
		boolean needstatus = true;//是否需要状态报告，需要true，不需要false
		String product = null;//产品ID
		String extno = null;//扩展码
		 
		try {
			String returnString = HttpSender.batchSend(uri, account, pswd, mobiles, content, needstatus, product, extno);
			log.info(returnString);
			//TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			//TODO 处理异常
			e.printStackTrace();
		}
	}
}
