package cc.messcat.entity;

import java.util.Date;

/**
 * Evaluate entity. @author MyEclipse Persistence Tools
 */

public class Evaluate implements java.io.Serializable {

	// Fields

	private Long id;
	private Member member;
	private McProductInfo product;
	private Double level;
	private String keypoint;
	private String evaluate;
	private String evaluateReply;
	private Long parendId;
	private Date addTime;
	private Date replyTime;
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public McProductInfo getProduct() {
		return product;
	}

	public void setProduct(McProductInfo product) {
		this.product = product;
	}

	public Double getLevel() {
		return this.level;
	}

	public void setLevel(Double level) {
		this.level = level;
	}

	public String getKeypoint() {
		return this.keypoint;
	}

	public void setKeypoint(String keypoint) {
		this.keypoint = keypoint;
	}

	public String getEvaluate() {
		return this.evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	public String getEvaluateReply() {
		return this.evaluateReply;
	}

	public void setEvaluateReply(String evaluateReply) {
		this.evaluateReply = evaluateReply;
	}

	public Long getParendId() {
		return this.parendId;
	}

	public void setParendId(Long parendId) {
		this.parendId = parendId;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}