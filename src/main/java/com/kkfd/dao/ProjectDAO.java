package com.kkfd.dao;

import java.util.List;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

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
	 * 크리에이터의 이전 프로젝트 검색
	 * @param proj_no
	 * @return
	 * @throws FindException
	 */
	public List<ProjectDTO> selectPrevProj(int proj_no) throws FindException;
	
	//-----------------------------------------------------------------------------//
	
	/**
	 * 
	 * @param projNo
	 * @param loginId
	 * @return
	 * @throws ModifyException
	 */
	public int updateProj(int projNo, String loginId) throws ModifyException;
	
	/**
	 * 
	 * @param loginId
	 * @return 로그인아이디가 올린 프로젝트리스트
	 * @throws FindException
	 */

	public List<ProjectDTO> selectProjsByCrId(String loginId,int currentPage) throws FindException;
  
	/**
	 * @param loginId
	 * @return 로그인아이디가 올린 프로젝트전체 수
	 * @throws FindException
	 */
	public int countMyProjs(String loginId) throws FindException;
}
