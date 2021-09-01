package com.kkfd.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.CreatorDTO;
import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import org.springframework.transaction.annotation.Transactional;

@Repository("creatorDAO")
public class CreatorDAOOracle implements CreatorDAO{
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	@Transactional(rollbackFor = AddException.class)
	public int insertCr(CreatorDTO creator) throws AddException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.insert("com.kkfd.dto.CreatorMapper.insertCr", creator);
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

	@Override
	public CreatorDTO selectCrById(String loginId) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.kkfd.dto.CreatorMapper.selectCrById", loginId);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

	@Override
	public int updateCr(CreatorDTO creator) throws ModifyException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.update("com.kkfd.dto.CreatorMapper.updateCr", creator);
		}catch (Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}





}
