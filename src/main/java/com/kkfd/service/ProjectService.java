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
	
	public ProjectDTO findByNo(int projNo, String id) throws FindException {
		return dao.selectByNo(projNo, id);
	}
	
}
