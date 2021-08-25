package com.kkfd.dao;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;

public interface FundingDAO {
	/**
	 * 펀딩 신청
	 * @param funding
	 * @throws AddException
	 */
	public void insert(FundingDTO funding) throws AddException;

}
