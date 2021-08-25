package com.kkfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kkfd.dao.ProjectDAO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;

@Service
public class ProjectService {
	@Autowired
	private ProjectDAO dao;
	
	public List<ProjectDTO> findProjs(SearchDTO search) throws FindException {
		return dao.selectProjs(search);
	}
	
	public List<ProjectDTO> findProjsByCrId(String crId,int currentPage) throws FindException {
		return dao.selectProjsByCrId(crId,currentPage);
	}
	
	public int countMyProjs(String crId) throws FindException {
		return dao.countMyProjs(crId);
	}
	
	
}
