package com.kkfd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kkfd.dao.ProjectDAO;
import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import com.kkfd.exception.RemoveException;

@Service
public class ProjectService {
	@Autowired
	private ProjectDAO dao;

	public List<ProjectDTO> findProjs(SearchDTO search) throws FindException {
		return dao.selectProjs(search);
	}

	public ProjectDTO findByNo(int projNo, String id) throws FindException {
		return dao.selectByNo(projNo, id);
	}

	public List<ProjectDTO> findPrevProj(int projNo) throws FindException {
		return dao.selectPrevProj(projNo);
	}

	public void addBookmark(int projNo, String id) throws AddException {
		dao.insertBookmark(projNo, id);
	}

	public void removeBookmark(int projNo, String id) throws RemoveException {
		dao.deleteBookmark(projNo, id);
	}

	public List<ProjectDTO> findMyBookmark(String id, int page) throws FindException {
		return dao.myBookmark(id, page);
	}


	//------------------------------------------------------------//


	public int cancleProj(int projNo, String loginId) throws ModifyException{
		return dao.updateProj(projNo, loginId) ;
	}


	public List<ProjectDTO> findProjsByCrId(String crId,int currentPage) throws FindException {
		return dao.selectProjsByCrId(crId,currentPage);
	}

	public int countMyProjs(String crId) throws FindException {
		return dao.countMyProjs(crId);
	}

	public Map<String,Object> findFunsByProjNo(int projNo, String loginId) throws FindException {
		return dao.selectFunsByProjNo(projNo, loginId);
	}

	//----------------------------프로젝트 등록---------------------------//
	public int addProj(ProjectDTO project) throws AddException {
		return dao.insertProj(project);
	}
}