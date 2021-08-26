package com.kkfd.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;

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
	@Override
	public List<FundingDTO> selectFunsByProjNo(int projNo, String loginId) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Object>map = new HashMap<>();
			map.put("projNo", projNo);
			map.put("loginId", loginId);
			return session.selectList("com.kkfd.dto.FundingMapper.selectFunsByProjNo", map);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
		
	}

}
