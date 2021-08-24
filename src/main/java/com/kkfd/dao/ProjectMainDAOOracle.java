package com.kkfd.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;

public class ProjectMainDAOOracle implements ProjectMainDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;
	
	//메인 프로젝트 목록 : 비로그인
	@Override
	public List<ProjectMainDTO> mainProjs() throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMapper.main");
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
			return session.selectList("com.kkfd.dto.ProjectMapper.main", id);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}


}
