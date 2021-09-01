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
	
	public void addApply(FundingDTO funding) throws AddException {
		dao.insert(funding);
	}

	public int modifyFuns(List<FundingDTO> list) throws ModifyException {
		return dao.updateFuns(list);
	}
	
	public List<FundingDTO> findFunsById(String loginId, int term, int state, int page) throws FindException{
		return dao.selectFunsById(loginId, term, state, page);
	
	}
	
	public int countMyFunList(String loginId, int term, int state) throws FindException{
		return dao.countMyFunList(loginId, term,state);
	}
	
	
}
