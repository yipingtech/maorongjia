package cc.messcat.vo;

import java.util.Date;

public class PayOrderVo2 {
	private Date payTimeStr;
	private String receiver;
	private String receiverPhone;
	private String receiverAddress;
	private String sender;
	private String senderPhone;
	private String senderAddress;
	

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderPhone() {
		return senderPhone;
	}

	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public Date getPayTimeStr() {
		return payTimeStr;
	}

	public void setPayTimeStr(Date payTimeStr) {
		this.payTimeStr = payTimeStr;
	}

}
