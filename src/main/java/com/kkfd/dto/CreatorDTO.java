package com.kkfd.dto;

public class CreatorDTO {
	private String crId;
	private String crNn;
	private String crIntro;
	private String crAcholder;
	private String crBank;
	private String crAcno;
	private String imgPath;
	//private MemberDTO member 추후
	public CreatorDTO() {}

	public CreatorDTO(String crId, String crNn, String crIntro, String crAcholder, String crBank, String crAcno,
			String imgPath) {
		super();
		this.crId = crId;
		this.crNn = crNn;
		this.crIntro = crIntro;
		this.crAcholder = crAcholder;
		this.crBank = crBank;
		this.crAcno = crAcno;
		imgPath = imgPath;
	}

	@Override
	public String toString() {
		return "CreatorDTO [crId=" + crId + ", crNn=" + crNn + ", crIntro=" + crIntro + ", crAcholder=" + crAcholder
				+ ", crBank=" + crBank + ", crAcno=" + crAcno + ", ImgPath=" + imgPath + "]";
	}
	public String getCrId() {
		return crId;
	}
	public void setCrId(String crId) {
		this.crId = crId;
	}
	public String getCrNn() {
		return crNn;
	}
	public void setCrNn(String crNn) {
		this.crNn = crNn;
	}
	public String getCrIntro() {
		return crIntro;
	}
	public void setCrIntro(String crIntro) {
		this.crIntro = crIntro;
	}
	public String getCrAcholder() {
		return crAcholder;
	}
	public void setCrAcholder(String crAcholder) {
		this.crAcholder = crAcholder;
	}
	public String getCrBank() {
		return crBank;
	}
	public void setCrBank(String crBank) {
		this.crBank = crBank;
	}
	public String getCrAcno() {
		return crAcno;
	}
	public void setCrAcno(String crAcno) {
		this.crAcno = crAcno;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
	
}
