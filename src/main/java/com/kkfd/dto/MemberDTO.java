package com.kkfd.dto;

public class MemberDTO {
	private String memId;
	private String memPwd;
	private String memName;
	private String memPhone;
	private String memEmail;
	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memPwd=" + memPwd + ", memName=" + memName + ", memPhone=" + memPhone
				+ ", memEmail=" + memEmail + "]";
	}
	public MemberDTO(String memId, String memPwd, String memName, String memPhone, String memEmail) {
		super();
		this.memId = memId;
		this.memPwd = memPwd;
		this.memName = memName;
		this.memPhone = memPhone;
		this.memEmail = memEmail;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPwd() {
		return memPwd;
	}
	public void setMemPwd(String memPwd) {
		this.memPwd = memPwd;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
}
