package com.kkfd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkfd.dao.CreatorDAO;
import com.kkfd.dto.CreatorDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

@Service
public class CreatorService {
	@Autowired
	private CreatorDAO dao;
	
	public void addCr(CreatorDTO creator) throws AddException{
		dao.insertCr(creator);
	}
	public CreatorDTO findCrById(String loginId) throws FindException{
		return dao.selectCrById(loginId);
	}
	public void modifyCr(CreatorDTO creator) throws ModifyException{
		dao.updateCr(creator);
	}


}
