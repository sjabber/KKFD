package com.kkfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkfd.dao.FundingDAO;
import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

@Service
public class FundingService {
	@Autowired
	private FundingDAO dao;
	
	public int addFunding(FundingDTO funding) throws AddException {
		return dao.insertFunding(funding);
	}

	public List<FundingDTO> findFunsByProjNo(int projNo, String loginId) throws FindException {
		return dao.selectFunsByProjNo(projNo, loginId);
	}
	
	public int modifyFuns(List<FundingDTO> list) throws ModifyException {
		return dao.updateFuns(list);
	}
}
