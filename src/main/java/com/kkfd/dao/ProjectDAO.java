package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.ProjectMainDTO;
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
	 * 프로젝트 번호로 검색
	 * @param proj_no 프로젝트 번호
	 * @return 프로젝트 번호에 해당하는 프로젝트
	 * @throws FindException 프로젝트가 없을 경우 또는 프로젝트찾기를 실패한 경우 발생
	 */
	public ProjectDTO selectByNo(int proj_no, String id) throws FindException;
	
	/**
	 * 
	 * @param creato id
	 * @return 해당 creator id가 올린 프로젝트
	 * @throws FindException
	 */
	public List<ProjectDTO> selectProjsByCrId(String crId) throws FindException;
}
