package com.kkfd.service;

import java.util.List;

import com.kkfd.exception.AddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kkfd.dao.ProjectDAO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

@Service
public class ProjectService {
	@Autowired
	private ProjectDAO dao;
	
	public List<ProjectDTO> findProjs(SearchDTO search) throws FindException {
		return dao.selectProjs(search);
	}
	
	public ProjectDTO findByNo(int projNo, String id) throws FindException {
		return dao.selectByNo(projNo, id);
	}
	
	public List<ProjectDTO> findPrevProj(int projNo) throws FindException {
		return dao.selectPrevProj(projNo);
	}
	
	
	//------------------------------------------------------------//
	
	
	public int cancleProj(int projNo, String loginId) throws ModifyException{
		return dao.updateProj(projNo, loginId) ;
	}


	public List<ProjectDTO> findProjsByCrId(String crId,int currentPage) throws FindException {
		return dao.selectProjsByCrId(crId,currentPage);
	}
	
	public int countMyProjs(String crId) throws FindException {
		return dao.countMyProjs(crId);
	}

	//----------------------------프로젝트 등록---------------------------//
	public int addProj(ProjectDTO project) throws AddException {
		return dao.insertProj(project);
	}


}
