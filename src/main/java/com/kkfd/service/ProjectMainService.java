package com.kkfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkfd.dao.ProjectMainDAO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;

@Service
public class ProjectMainService {
	@Autowired
	private ProjectMainDAO dao;
	
	public List<ProjectMainDTO> findMainProjs() throws FindException {
		return dao.mainProjs();
	}
	
	public List<ProjectMainDTO> findMainProjs(String id) throws FindException {
		return dao.mainProjs(id);
	}

}
