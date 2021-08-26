package com.kkfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kkfd.dao.FundingDAO;
import com.kkfd.dao.ProjectDAO;
import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;

@Service
public class FundingService {
	@Autowired
	private FundingDAO dao;

	public List<FundingDTO> findFunsByProjNo(int projNo, String loginId) throws FindException {
		return dao.selectFunsByProjNo(projNo, loginId);
	}
}
