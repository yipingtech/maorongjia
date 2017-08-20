package cc.messcat.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.wechat.weixin.popular.support.TicketManager;
import cc.messcat.wechat.weixin.popular.support.TokenManager;
import cc.modules.constants.Constants;

@SuppressWarnings("unchecked")
public class BaseListener implements ServletContextListener {
	private static final long serialVersionUID = -5184793850414042013L;
	private static Logger log = LoggerFactory.getLogger(BaseListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			TokenManager.init(Constants.APPID, Constants.APPSECRET);
			TicketManager.init(Constants.APPID);
			log.info("listener:token:" + TokenManager.getToken(Constants.APPID));
			log.info("listener:ticket:" + TicketManager.getTicket(Constants.APPID));
		} catch (Exception e1) {
			log.info("MemberInitToken::");
			e1.printStackTrace();
		}
	}
}