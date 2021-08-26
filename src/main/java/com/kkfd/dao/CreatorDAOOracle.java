package com.kkfd.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.exception.ModifyException;

@Repository("creatorDAO")
public class CreatorDAOOracle implements CreatorDAO{
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public int updateProj(int projNo, String loginId) throws ModifyException{
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Object> map = new HashMap<>();
			map.put("projNo", projNo);
			map.put("loginId", loginId);
			return session.update("com.kkfd.dto.ProjectMapper2.updateProj",map);
		}catch (Exception e) {
			throw new ModifyException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}		
	}

}
