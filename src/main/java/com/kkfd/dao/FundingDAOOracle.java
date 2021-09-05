package com.kkfd.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.PageDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

@Repository("fundingDAO")
public class FundingDAOOracle implements FundingDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public int insertFunding(FundingDTO funding) throws AddException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.kkfd.dto.FundingMapper.insertFunding", funding);
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
		
	}

	
	@Override
	public int updateFuns(List<FundingDTO> list) throws ModifyException {
		SqlSession session= null;
		int rowCnt = 0;
		try {
			session = sessionFactory.openSession();
			for(FundingDTO funding : list) {
			int i = session.update("com.kkfd.dto.FundingMapper.updateFun",funding);
			rowCnt+=i;
			log.error(funding.toString());
			}
			return rowCnt;
		}catch (Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}
	@Override
	public List<FundingDTO> selectFunsById(String loginId, int term, int state, int page) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			
			HashMap<String, Object>map = new HashMap<>();
			map.put("loginId", loginId);
			map.put("term", term);
			map.put("state", state);
			map.put("currentPage", page);
			map.put("cntPerPage", PageDTO.CNT_PER_PAGE);
			return session.selectList("com.kkfd.dto.FundingMapper2.selectFunsById", map);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}
	@Override
	public int countMyFunList(String loginId, int term, int state) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Object>map = new HashMap<>();
			map.put("loginId", loginId);
			map.put("term", term);
			map.put("state", state);
			return session.selectOne("com.kkfd.dto.FundingMapper2.countMyFunList", map);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

}
