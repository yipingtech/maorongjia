package cc.modules.constants;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import cc.modules.util.PropertiesFileReader;

public class Constants {

	public static final String ENCODING = "UTF-8";
	public static final String MESSCATCMS = "messcatcms";

	/**
	 * ϵͳ����
	 */
	public static final Map<String, String> SYSTEM_TYPES = new LinkedHashMap<String, String>();
	static {
		SYSTEM_TYPES.put(MESSCATCMS, "messcatcmsͳ");
	}

	public static String getSysName(String sysType) {
		return SYSTEM_TYPES.get(sysType);
	}

	/**
	 * �ϴ���·����
	 */
	public static final String UPLOAD_PATH = "upload";
	public static final String UPLOAD_FILE = Constants.FILE_SEPARATOR+"file";
	public static final String UPLOAD_FLASH = Constants.FILE_SEPARATOR+"flash";
	public static final String UPLOAD_IMAGE = Constants.FILE_SEPARATOR+"image";
	public static final String UPLOAD_MEDIA = Constants.FILE_SEPARATOR+"media";
	/**
	 * 
	 */
	public static final String TPL_DEF_SOLUTION = "default";
	/**
	 * 
	 */
	public static final String TPL_SUFFIX = ".html";

	/**
	 * 
	 */
	public static final char SPT = '/';
	/**
	 * 
	 */
	public static final String FILE_SEPARATOR = File.separator;
	/**
	 * 
	 */
	public static final String REDIRECT_DOMAIN = "redirectDomain";
	/**
	 * 
	 */
	public static final String WEBSITE_NOT_FOUND = "websiteNotFound";

	/**
	 * 操作成功完成
	 */
	public static final String SUCCESS = "success";

	/**
	 * 输入错误
	 */
	public static final String INPUT = "input";

	public static final String DO_SUCCESS = "do_success";
	
	/**
	 * 项目名称
	 */
	public static final String PROJECTNAME = PropertiesFileReader.get("web.name", "/app.properties").trim(); //项目名称
	
	/**
	 * 微信appId
	 */
	public static final String APPID = PropertiesFileReader.get("wechat.appId", "/app.properties").trim();   //床品超市
	
	
	/**
	 * 微信密钥
	 */
	public static final String APPSECRET = PropertiesFileReader.get("wechat.appsecret", "/app.properties").trim();//床品超市
	
	
	/**
	 * 商户号，微信支付合作者id
	 */
	public static final String PARTNER_ID = PropertiesFileReader.get("wechat.partnerId", "/app.properties").trim();//床品超市
	
	/**
	 * 微信合作者密钥
	 */
	public static final String PARTNER_KEY = PropertiesFileReader.get("wechat.partnerKey", "/app.properties").trim();//床品超市
	
	/**
	 * 验证码
	 */
	public static String CHECK_CODE = "";
	
	/**
	 * 微信链接服务器域名
	 */
	public static String WEBSITE_URL = PropertiesFileReader.get("wechat.websiteUrl", "/app.properties").trim();    //公司测试服务器地址
	
	/**
	 * 微信支付回调地址
	 */
	public static String PAY_NOTIFY_URL = PropertiesFileReader.get("wechat.payNotifyUrl", "/app.properties").trim();    //公司测试服务器地址

	/**
	 * 游客
	 */
	public static final String VISITOR =  "0";
	
	/**
	 * 分销商
	 */
	public static final String DISTRIBUTOR =  "1";
	
	 /**
	 * 背景图X轴坐标
	 */
	public static int BACKGROUND_IMG_X = 0;
	
	 /**
	 * 背景图Y轴坐标
	 */
	public static int BACKGROUND_IMG_Y = 0;
	
	/**
	 * 微信二维码图片X轴坐标
	 */
	public static int WX_QR_IMG_X = 270;
	//public static int WX_QR_IMG_X = 218;
	
	/**
	 * 微信二维码图片Y轴坐标
	 */
	public static int WX_QR_IMG_Y = 530;
	//public static int WX_QR_IMG_Y = 446;
	
	/**
	 * 微信二维码图片宽
	 */
	public static int WX_QR_IMG_WIDTH = 200;
	
	/**
	 * 微信二维码图片高
	 */
	public static int WX_QR_IMG_HEIGHT = 200;
	
	
	/**
	 * 微信头像图片X轴坐标
	 */
	public static int WX_IMG_X = 72;
	//public static int WX_IMG_X = 55;
	
	/**
	 * 微信头像图片Y轴坐标
	 */
	public static int WX_IMG_Y = 1085;
	//public static int WX_IMG_Y = 944;
	
	/**
	 * 微信头像图片宽
	 */
	public static int WX_IMG_WIDTH = 82;
	
	/**
	 * 微信头像图片高
	 */
	public static int WX_IMG_HEIGHT = 82;
	
	/**
	 * 微信昵称X轴坐标
	 */
	public static int WX_NICK_NAME_X = 260;
	//public static int WX_NICK_NAME_X = 220;
	
	/**
	 * 微信昵称Y轴坐标
	 */
	public static int WX_NICK_NAME_Y = 1105;
	//public static int WX_NICK_NAME_Y = 965;
	
	/**
	 * 生成二维码图片路径
	 */
	public static String QR_TEMP_PATH= "D://wwwroot//bailichun//fx//upload//enterprice//member_qrcode/";  //服务器路径
	//public static String QR_TEMP_PATH="D://tomcat8.0//webapps//bailichun//upload//enterprice//member_qrcode/"; //本地测试路径
	//public static String QR_TEMP_PATH= "D:/test/";  //测试路径
	
	/**
	 * 二维码背景图片名称
	 */
	public static String QR_BACKGROUND_IMG_NAME= "background.jpg";
	
	/**
	 * 新会员加入通知
	 */
	public static String TEMPLATE_MEMBER_ADD = PropertiesFileReader.get("wechat.templateMemberAdd", "/app.properties").trim();    //公司测试服务器地址;
	
	/**
	 * 新增订单通知
	 */
	public static String TEMPLATE_ORDER_SEND= PropertiesFileReader.get("wechat.templateOrderSend", "/app.properties").trim();    //公司测试服务器地址;
	/**
	 * 订单发货通知
	 */
	public static String TEMPLATE_ORDER_ADD= PropertiesFileReader.get("wechat.templateOrderAdd", "/app.properties").trim();    //公司测试服务器地址
	/**
	 * 关注平台方式(二维码)
	 */
	public static String FOCUS_WAY_QRCODE= "1";
	
	/**
	 * 关注平台方式(链接)
	 */
	public static String FOCUS_WAY_URL= "2";
	
	/**
	 * 顺序
	 */
	public static String ASC= "asc";
	
	/**
	 * 逆序
	 */
	public static String DESC= "desc";
	
	/**
	 * 启用
	 */
	public static String ENABLE= "1";
	
	/**
	 * 禁用
	 */
	public static String DISABLE= "0";
	
}
