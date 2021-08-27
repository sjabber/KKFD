package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;

public interface FundingDAO {
	/**
	 * 펀딩 신청
	 * @param funding
	 * @throws AddException
	 */
	public void insert(FundingDTO funding) throws AddException;

	/**
	 * 해당 프로젝트의 참여자 보기
	 * @param projNo : 프로젝트 번호
	 * @param loginId : 세션의 loginId (로그인 아이디와 프로젝트 크리에이터가 일치할때만 목록반환)
	 * @return List<FundingDTO> : 펀딩리스트(funding has a project and a member) 
	 * @throws FindException
	 */
	public List<FundingDTO> selectFunsByProjNo(int projNo, String loginId) throws FindException;
}
