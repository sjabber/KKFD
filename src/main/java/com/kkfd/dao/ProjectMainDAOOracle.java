package com.kkfd.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;
@Repository("projectMainDAO")
public class ProjectMainDAOOracle implements ProjectMainDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	//메인 프로젝트 목록 : 비로그인
	@Override
	public List<ProjectMainDTO> mainProjs() throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMainMapper.selectMain");
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

	//메인 프로젝트 목록 : 로그인
	@Override
	public List<ProjectMainDTO> mainProjs(String id) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMainMapper.selectMainLogined", id);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}


}
