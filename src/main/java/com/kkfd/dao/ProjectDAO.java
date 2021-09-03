package com.kkfd.dao;

import java.util.List;
import java.util.Map;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import com.kkfd.exception.RemoveException;

public interface ProjectDAO {
	/**
	 * 프로젝트 목록
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
	
	/**
	 * 북마크 추가
	 * @param projNo
	 * @param id
	 * @throws AddException
	 */
	public void insertBookmark(int projNo, String id) throws AddException;
	
	/**
	 * 북마크 삭제
	 * @param projNo
	 * @param id
	 * @throws RemoveException
	 */
	public void deleteBookmark(int projNo, String id) throws RemoveException;
	
	/**
	 * 북마크한 프로젝트 목록
	 * @param id
	 * @return
	 * @throws FindException
	 */
	public List<ProjectDTO> myBookmark(String id, int page) throws FindException;
	
	//-----------------------------------------------------------------------------//
	
	/**
	 * 
	 * @param projNo
	 * @param loginId
	 * @return
	 * @throws ModifyException
	 */
	public void updateProj(int projNo, String loginId) throws ModifyException;
	
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
	

	/**
	 * 해당 프로젝트의 참여자 보기
	 * @param projNo : 프로젝트 번호
	 * @param loginId : 세션의 loginId (로그인 아이디와 프로젝트 크리에이터가 일치할때만 목록반환)
	 * @return project : projectDto, fundingList : List<fundingsDTO>  
	 * @throws FindException
	 */
	public Map<String,Object> selectFunsByProjNo(int projNo, String loginId) throws FindException;
	
}
