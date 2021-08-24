package com.kkfd.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;
@Repository("projectDAO")
public class ProjectDAOOracle implements ProjectDAO {

	@Autowired
	private SqlSessionFactory sessionFactory;

	//[project]프로젝트 목록
	@Override
	public List<ProjectDTO> selectProjs(SearchDTO search) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMapper.selectProjs",search);//변경하세요~
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
		
	}

	//[creator]마이 프로젝트
	@Override
	public List<ProjectDTO> selectProjsByCrId(String crId) throws FindException {
		// TODO Auto-generated method stub
		return null;
	}


	
}
