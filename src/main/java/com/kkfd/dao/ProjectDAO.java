package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;

public interface ProjectDAO {
	/**
	 * 
	 * @param search
	 * @return
	 * @throws FindException
	 */
	public List<ProjectDTO> selectProjs(SearchDTO search) throws FindException;
	
	/**
	 * 
	 * @param creator id
	 * @return 해당 creator id가 올린 프로젝트
	 * @throws FindException
	 */
	public List<ProjectDTO> selectProjsByCrId(String crId,int currentPage) throws FindException;
	
	/**
	 * 
	 * @return 해당 creator id가 올린 프로젝트
	 * @throws FindException
	 */
	public int countMyProjs(String crId) throws FindException;

}
