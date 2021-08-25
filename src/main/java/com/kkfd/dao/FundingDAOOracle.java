package com.kkfd.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;

@Repository("fundingDAO")
public class FundingDAOOracle implements FundingDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void insert(FundingDTO funding) throws AddException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			session.insert("com.kkfd.dto.FundingMapper.insert", funding);
		}catch (Exception e) {
			throw new AddException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
		
	}

}
