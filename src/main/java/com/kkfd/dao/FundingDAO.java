package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

public interface FundingDAO {
	/**
	 * 펀딩 신청
	 * @param funding
	 * @throws AddException
	 */
	public int insertFunding(FundingDTO funding) throws AddException;
	//public void insertFunding(FundingDTO funding) throws AddException;

	/**
	 * 
	 * @param 펀딩번호,운송장번호가 담긴 펀딩 리스트
	 * @return update된 행 수(rowCnt)
	 * @throws ModifyException
	 */
	public int updateFuns(List<FundingDTO> list) throws ModifyException;
	
	public List<FundingDTO> selectFunsById(String loginId, int term, int state, int page) throws FindException;
	
	public int countMyFunList(String loginId, int term, int state) throws FindException;
	
	
	
}
