package cc.messcat.wechat.weixin.popular.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cc.messcat.wechat.weixin.popular.bean.BaseResult;
import cc.messcat.wechat.weixin.popular.bean.Menu;
import cc.messcat.wechat.weixin.popular.bean.MenuButtons;
import cc.messcat.wechat.weixin.popular.client.LocalHttpClient;
import cc.modules.constants.Constants;

public class MenuAPI extends BaseAPI {
	private static Logger log = LoggerFactory.getLogger(MenuAPI.class); 
	/**
	 * 创建菜单
	 * 
	 * @param access_token
	 * @param menuJson
	 *            菜单json 数据 例如{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}
	 *            ] }
	 * @return
	 */
	public static BaseResult menuCreate(String access_token, String menuJson) {
		HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader).setUri(BASE_URI + "/cgi-bin/menu/create")
			.addParameter("access_token", access_token).setEntity(new StringEntity(menuJson, Charset.forName("utf-8"))).build();
		return LocalHttpClient.executeJsonResult(httpUriRequest, BaseResult.class);
	}

	/**
	 * 创建菜单
	 * 
	 * @param access_token
	 * @param menuButtons
	 * @return
	 */
	public static BaseResult menuCreate(String access_token, MenuButtons menuButtons) {
		String str = JsonUtil.toJSONString(menuButtons);
		return menuCreate(access_token, str);
	}

	/**
	 * 获取菜单
	 * 
	 * @param access_token
	 * @return
	 */
	public static Menu menuGet(String access_token) {
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(BASE_URI + "/cgi-bin/menu/get").addParameter("access_token",
			access_token).build(); 
		return LocalHttpClient.executeJsonResult(httpUriRequest, Menu.class);
	}

	/**
	 * 删除菜单
	 * 
	 * @param access_token
	 * @return
	 */
	public static BaseResult menuDelete(String access_token) {
		HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(BASE_URI + "/cgi-bin/menu/delete").addParameter(
			"access_token", access_token).build();
		return LocalHttpClient.executeJsonResult(httpUriRequest, BaseResult.class);
	}

	public static void main(String[] args) throws Exception {
		String token = TokenAPI.token(Constants.APPID, Constants.APPSECRET).getAccess_token();
		BaseResult baseResult = MenuAPI.menuCreate(token, getMenu());
		log.error(baseResult.getErrcode() + "--" + baseResult.getErrmsg());
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static MenuButtons getMenu() throws UnsupportedEncodingException {

		MenuButtons menuButtons = new MenuButtons();

		MenuButtons.Button mainBtn1 = new MenuButtons.Button();
		mainBtn1.setName("进入商城");
		mainBtn1.setType("view");
		System.out.println(Constants.WEBSITE_URL);
		String url1 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!toIndex.action", "UTF-8");
		mainBtn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url1
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		/*MenuButtons.Button childBtn3 = new MenuButtons.Button();
		childBtn3.setName("模式解说");
		childBtn3.setType("view");
		String url5 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!moShi.action", "UTF-8");
		childBtn3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url5
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/

		/*MenuButtons.Button childBtn4 = new MenuButtons.Button();
		childBtn4.setName("如何组建团队");
		childBtn4.setType("view");
		String url2 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!createTeam.action", "UTF-8");
		childBtn4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url2
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/
	

		/*MenuButtons.Button childBtn5 = new MenuButtons.Button();
		childBtn5.setName("我的分享");
		childBtn5.setType("view");
		String url11 = URLEncoder.encode(Constants.WEBSITE_URL + "/epNewsListMoreAction!redirectNewsList.action", "UTF-8");
		childBtn5.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url11
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/

		MenuButtons.Button childBtn6 = new MenuButtons.Button();
		childBtn6.setName("专属二维码");
		childBtn6.setType("view");
		String url3 = URLEncoder.encode(Constants.WEBSITE_URL + "/epMemberCenterAction!generateQrcode.action", "UTF-8");
		childBtn6.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url3
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		/*MenuButtons.Button childBtn12 = new MenuButtons.Button();
		childBtn12.setName("个人中心");
		childBtn12.setType("view");
		String url4 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!myCompany.action", "UTF-8");
		childBtn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url4
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/




		MenuButtons.Button mainBtn2 = new MenuButtons.Button();
		mainBtn2.setName("会员中心");
		mainBtn2.setType("view");
		String ur20 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!myCompany.action", "UTF-8");
		mainBtn2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + ur20
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
		
		//List<MenuButtons.Button> button2List = new ArrayList<MenuButtons.Button>();
		//button2List.add(childBtn3);
		//button2List.add(childBtn4);
		//button2List.add(childBtn5);
		//button2List.add(childBtn6);
		//button2List.add(childBtn12);
		//mainBtn2.setSub_button(button2List);

		/*MenuButtons.Button childBtn7 = new MenuButtons.Button();
		childBtn7.setName("集团简介");
		childBtn7.setType("view");
		String url6 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!intro.action", "UTF-8");
		childBtn7.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url6
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/

/*		MenuButtons.Button childBtn8 = new MenuButtons.Button();
		childBtn8.setName("律师声明");
		childBtn8.setType("view");
		String url7 = URLEncoder.encode(Constants.WEBSITE_URL + "/navigation.htm?columnId=32&columnType=content", "UTF-8");
		childBtn8.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url7
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");
*/
		/*MenuButtons.Button childBtn8 = new MenuButtons.Button();
		childBtn8.setName("集团架构");
		childBtn8.setType("view");
		String url8 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!jiaGou.action", "UTF-8");
		childBtn8.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url8
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/

		/*MenuButtons.Button childBtn9 = new MenuButtons.Button();
		childBtn9.setName("发展历程");
		//childBtn9.setName("快递查询");
		childBtn9.setType("view");
		String url12 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!develop.action", "UTF-8");
		childBtn9.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url12
				+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/
		
		/*MenuButtons.Button childBtn10 = new MenuButtons.Button();
		childBtn10.setName("企业文化");
		//childBtn10.setName("购买协议");
		childBtn10.setType("view");
		String url9 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!culture.action", "UTF-8");
		childBtn10.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url9
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/
		

		MenuButtons.Button childBtn11 = new MenuButtons.Button();
		childBtn11.setName("微官网链接");
		childBtn11.setType("view");
		String url10 = URLEncoder.encode(Constants.WEBSITE_URL + "/epIndexAction!contactUs.action", "UTF-8");
		/*childBtn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + Constants.APPID + "&redirect_uri=" + url10
			+ "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");*/
		childBtn11.setUrl("http://www.yipingtech.com");

		MenuButtons.Button mainBtn3 = new MenuButtons.Button();
		mainBtn3.setName("关于我们");

		List<MenuButtons.Button> button3List = new ArrayList<MenuButtons.Button>();
		//button3List.add(childBtn7);
		//button3List.add(childBtn8);
		//button3List.add(childBtn9);
		button3List.add(childBtn11);
		button3List.add(childBtn6);
		mainBtn3.setSub_button(button3List);

		menuButtons.setButton(new MenuButtons.Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menuButtons;
	}

}