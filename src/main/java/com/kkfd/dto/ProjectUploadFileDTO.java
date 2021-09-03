package com.kkfd.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ProjectUploadFileDTO {
	private ProjectDTO project;			//폴더 : 프로젝트번호
	private List<MultipartFile> imgs;	//상세이미지 4개까지 프로젝트번호_1,2,3,4
	private MultipartFile thumbnail;	//대표이미지 프로젝트_0
	public ProjectUploadFileDTO() {}
	public ProjectUploadFileDTO(ProjectDTO project, List<MultipartFile> imgs, MultipartFile thumbnail) {
		super();
		this.project = project;
		this.imgs = imgs;
		this.thumbnail = thumbnail;
	}
	public ProjectDTO getProject() {
		return project;
	}
	public void setProject(ProjectDTO project) {
		this.project = project;
	}
	public List<MultipartFile> getImgs() {
		return imgs;
	}
	@Override
	public String toString() {
		return "ProjectUploadFileDTO [project=" + project + ", imgs=" + imgs + ", thumbnail=" + thumbnail + "]";
	}
	public void setImgs(List<MultipartFile> imgs) {
		this.imgs = imgs;
	}
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}