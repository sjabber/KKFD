package com.kkfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkfd.dao.CreatorDAO;
import com.kkfd.dao.ProjectDAO;
import com.kkfd.dto.CreatorDTO;
import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

@Service
public class CreatorService {
	@Autowired
	private CreatorDAO dao;
	
	public int addCr(CreatorDTO creator) throws AddException{
		return dao.insertCr(creator);
	}
	public CreatorDTO findCrById(String loginId) throws FindException{
		return dao.selectCrById(loginId);
	}
	public int modifyCr(CreatorDTO creator) throws ModifyException{
		return dao.updateCr(creator);
	}


}
