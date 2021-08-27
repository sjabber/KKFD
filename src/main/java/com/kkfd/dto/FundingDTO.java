package com.kkfd.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FundingDTO {
	private int funNo;
	private MemberDTO member;	//창작자가 후원자이름,이메일,연락처조회 시
	private ProjectDTO project; //펀딩한 프로젝트 가격,달성률 배송예정일 등 조회 시
	private int funQuantity;
	private int funFm;
	private String funName;
	private String funAddress;
	private String funDetail;
	private String funBank;
	private String funAcno;
	private int funTrack;	//0:운송장 미입력 1:운송장입력
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "Asia/Seoul")
	private Date funDate;
	public FundingDTO() {}
	@Override
	public String toString() {
		return "FundingDTO [funNo=" + funNo + ", member=" + member + ", project=" + project + ", funQuantity="
				+ funQuantity + ", funFm=" + funFm + ", funName=" + funName + ", funAddress=" + funAddress
				+ ", funDetail=" + funDetail + ", funBank=" + funBank + ", funAcno=" + funAcno + ", funTrack="
				+ funTrack + ", funDate=" + funDate + "]";
	}
	public int getFunNo() {
		return funNo;
	}
	public void setFunNo(int funNo) {
		this.funNo = funNo;
	}
	public MemberDTO getMember() {
		return member;
	}
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	public ProjectDTO getProject() {
		return project;
	}
	public void setProject(ProjectDTO project) {
		this.project = project;
	}
	public int getFunQuantity() {
		return funQuantity;
	}
	public void setFunQuantity(int funQuantity) {
		this.funQuantity = funQuantity;
	}
	public int getFunFm() {
		return funFm;
	}
	public void setFunFm(int funFm) {
		this.funFm = funFm;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public String getFunAddress() {
		return funAddress;
	}
	public void setFunAddress(String funAddress) {
		this.funAddress = funAddress;
	}
	public String getFunDetail() {
		return funDetail;
	}
	public void setFunDetail(String funDetail) {
		this.funDetail = funDetail;
	}
	public String getFunBank() {
		return funBank;
	}
	public void setFunBank(String funBank) {
		this.funBank = funBank;
	}
	public String getFunAcno() {
		return funAcno;
	}
	public void setFunAcno(String funAcno) {
		this.funAcno = funAcno;
	}
	public int getFunTrack() {
		return funTrack;
	}
	public void setFunTrack(int funTrack) {
		this.funTrack = funTrack;
	}
	public Date getFunDate() {
		return funDate;
	}
	public void setFunDate(Date funDate) {
		this.funDate = funDate;
	}
	public FundingDTO(int funNo, MemberDTO member, ProjectDTO project, int funQuantity, int funFm, String funName,
			String funAddress, String funDetail, String funBank, String funAcno, int funTrack, Date funDate) {
		super();
		this.funNo = funNo;
		this.member = member;
		this.project = project;
		this.funQuantity = funQuantity;
		this.funFm = funFm;
		this.funName = funName;
		this.funAddress = funAddress;
		this.funDetail = funDetail;
		this.funBank = funBank;
		this.funAcno = funAcno;
		this.funTrack = funTrack;
		this.funDate = funDate;
	}

}
