package com.kkfd.dto;

import java.util.Date;

public class ProjectDTO {
	private int projNo;
	private CreatorDTO creator;	//project has a creator
	private int projCategory;	//1:캔들 2:조향 3:비누 4:도예 5:섬유,자수
	private String projTitle;
	private String projSummary;
	private String projIntro;
	private int projFm; 		//리워드(물품)1개당 금액(모인금액은 1개당금액*주문수량)
	private int projTargetcnt;
	private int projLimitcnt;
	private int projQuantity;
	private int projGoals;
	/*반올림 시점(
	SELECT
	 -SELECT proj_goals 
	 -펀딩테이블 한 행 추가될때 UPDATE SET 수량 = 수량+신청갯수 / 달성률 = ((수량/목표갯수)*100 반올림
	*/
	private Date projStart;
	private Date projEnd;
	private Date projDelivery;
	private int projBmcnt;	
	private int projStatus;		//0:취소(이미 취소 한 상태) 1:정상(취소가능 한 상태)
	private int projBm;			//로그인 한 아이디의 해당프로젝트에 대한 북마크여부 (1:북마크 on / 0: 북마크 off)
	public ProjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProjectDTO(int projNo, CreatorDTO creator, int projCategory, String projTitle, String projSummary,
			String projIntro, int projFm, int projTargetcnt, int projLimitcnt, int projQuantity, int projGoals,
			Date projStart, Date projEnd, Date projDelivery, int projBmcnt, int projStatus, int projBm) {
		super();
		this.projNo = projNo;
		this.creator = creator;
		this.projCategory = projCategory;
		this.projTitle = projTitle;
		this.projSummary = projSummary;
		this.projIntro = projIntro;
		this.projFm = projFm;
		this.projTargetcnt = projTargetcnt;
		this.projLimitcnt = projLimitcnt;
		this.projQuantity = projQuantity;
		this.projGoals = projGoals;
		this.projStart = projStart;
		this.projEnd = projEnd;
		this.projDelivery = projDelivery;
		this.projBmcnt = projBmcnt;
		this.projStatus = projStatus;
		this.projBm = projBm;
	}
	public int getProjNo() {
		return projNo;
	}
	public void setProjNo(int projNo) {
		this.projNo = projNo;
	}
	public CreatorDTO getCreator() {
		return creator;
	}
	public void setCreator(CreatorDTO creator) {
		this.creator = creator;
	}
	public int getProjCategory() {
		return projCategory;
	}
	public void setProjCategory(int projCategory) {
		this.projCategory = projCategory;
	}
	public String getProjTitle() {
		return projTitle;
	}
	public void setProjTitle(String projTitle) {
		this.projTitle = projTitle;
	}
	public String getProjSummary() {
		return projSummary;
	}
	public void setProjSummary(String projSummary) {
		this.projSummary = projSummary;
	}
	public String getProjIntro() {
		return projIntro;
	}
	public void setProjIntro(String projIntro) {
		this.projIntro = projIntro;
	}
	public int getProjFm() {
		return projFm;
	}
	public void setProjFm(int projFm) {
		this.projFm = projFm;
	}
	public int getProjTargetcnt() {
		return projTargetcnt;
	}
	public void setProjTargetcnt(int projTargetcnt) {
		this.projTargetcnt = projTargetcnt;
	}
	public int getProjLimitcnt() {
		return projLimitcnt;
	}
	public void setProjLimitcnt(int projLimitcnt) {
		this.projLimitcnt = projLimitcnt;
	}
	public int getProjQuantity() {
		return projQuantity;
	}
	public void setProjQuantity(int projQuantity) {
		this.projQuantity = projQuantity;
	}
	public int getProjGoals() {
		return projGoals;
	}
	public void setProjGoals(int projGoals) {
		this.projGoals = projGoals;
	}
	public Date getProjStart() {
		return projStart;
	}
	public void setProjStart(Date projStart) {
		this.projStart = projStart;
	}
	public Date getProjEnd() {
		return projEnd;
	}
	public void setProjEnd(Date projEnd) {
		this.projEnd = projEnd;
	}
	public Date getProjDelivery() {
		return projDelivery;
	}
	public void setProjDelivery(Date projDelivery) {
		this.projDelivery = projDelivery;
	}
	public int getProjBmcnt() {
		return projBmcnt;
	}
	public void setProjBmcnt(int projBmcnt) {
		this.projBmcnt = projBmcnt;
	}
	public int getProjStatus() {
		return projStatus;
	}
	public void setProjStatus(int projStatus) {
		this.projStatus = projStatus;
	}
	public int getProjBm() {
		return projBm;
	}
	public void setProjBm(int projBm) {
		this.projBm = projBm;
	}
	@Override
	public String toString() {
		return "ProjectDTO [projNo=" + projNo + ", creator=" + creator + ", projCategory=" + projCategory
				+ ", projTitle=" + projTitle + ", projSummary=" + projSummary + ", projIntro=" + projIntro + ", projFm="
				+ projFm + ", projTargetcnt=" + projTargetcnt + ", projLimitcnt=" + projLimitcnt + ", projQuantity="
				+ projQuantity + ", projGoals=" + projGoals + ", projStart=" + projStart + ", projEnd=" + projEnd
				+ ", projDelivery=" + projDelivery + ", projBmcnt=" + projBmcnt + ", projStatus=" + projStatus
				+ ", projBm=" + projBm + "]";
	}
	
	

}
