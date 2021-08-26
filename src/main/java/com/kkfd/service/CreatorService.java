package com.kkfd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkfd.dao.CreatorDAO;
import com.kkfd.dao.ProjectDAO;
import com.kkfd.exception.ModifyException;

@Service
public class CreatorService {
	@Autowired
	private CreatorDAO dao;
	
	public int cancleProj(int projNo, String loginId) throws ModifyException{
		return dao.updateProj(projNo, loginId) ;
	}

}
