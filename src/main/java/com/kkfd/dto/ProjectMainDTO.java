package com.kkfd.dto;


public class ProjectMainDTO {//메인화면 프로젝트 출력용
	private int projArea;		//1:인기 2:신규 3:공개예정 4:성공임박
	private ProjectDTO project;
	public ProjectMainDTO(int projArea, ProjectDTO project) {
		super();
		this.projArea = projArea;
		this.project = project;
	}
	public ProjectMainDTO() {
		super();
	}
	public int getProjArea() {
		return projArea;
	}
	public void setProjArea(int projArea) {
		this.projArea = projArea;
	}
	public ProjectDTO getProject() {
		return project;
	}
	@Override
	public String toString() {
		return "ProjectMainDTO [projArea=" + projArea + ", project=" + project + "]";
	}
	public void setProject(ProjectDTO project) {
		this.project = project;
	}
	
}
