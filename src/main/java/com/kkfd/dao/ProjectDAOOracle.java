package com.kkfd.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.dto.PageDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
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
			return session.selectList("com.kkfd.dto.ProjectMapper.selectProjs",search);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
		
	}

	//[project]프로젝트 상세
	@Override
	public ProjectDTO selectByNo(int projNo, String id) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			HashMap<String, Object> map = new HashMap<>();
			map.put("projNo", projNo);
			map.put("memId", id);
			return session.selectOne("com.kkfd.dto.ProjectMapper.selectByNo", map);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}
	
	
	@Override
	public List<ProjectDTO> selectPrevProj(int proj_no) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMapper.selectPrevProj", proj_no);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}
	
	
	//----------------------------------------------------//
	
	
	@Override
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

	

	//[creator]마이 프로젝트
	@Override
	public List<ProjectDTO> selectProjsByCrId(String loginId,int currentPage) throws FindException {
		SqlSession session= null;
		try {
			HashMap<String, Object>map = new HashMap<>();
			map.put("loginId", loginId);
			map.put("currentPage", currentPage);
			map.put("cntPerPage", PageDTO.CNT_PER_PAGE);
			session = sessionFactory.openSession();
			return session.selectList("com.kkfd.dto.ProjectMapper2.selectProjsByCrId",map);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

	//[creator]마이 프로젝트 페이징 TotalCnt
	@Override
	public int countMyProjs(String crId) throws FindException {
		SqlSession session= null;
		try {
			session = sessionFactory.openSession();
			return session.selectOne("com.kkfd.dto.ProjectMapper2.countMyProjs",crId);
		}catch (Exception e) {
			throw new FindException(e.getMessage());
		}finally {
			if(session!=null) session.close();
		}
	}

	
}
