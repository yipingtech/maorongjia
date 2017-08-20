package cc.messcat.vo;

import cc.messcat.entity.Member;

public class MemberVo {
	private Member member;
	private double money;

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
