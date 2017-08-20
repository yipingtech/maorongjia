package cc.messcat.entity;

import java.util.Date;

public class AccessToken {

	
private Long id;
	
	private String appId;
	
	private String appsecret;
	
    private Date expirationTime;
	
	private String type;
	
	private String token;
	
	private String openId;
	
	public AccessToken() {
		
	}
    public AccessToken(String appId,String appsecret,String type) {
		this.appId=appId;
		this.appsecret=appsecret;
		this.type=type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
