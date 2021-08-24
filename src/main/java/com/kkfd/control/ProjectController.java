package com.kkfd.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;
import com.kkfd.service.ProjectService;

@RestController
@RequestMapping("/project/*")
public class ProjectController {

	@Autowired
	private ProjectService service;
	/*
	 * 이벤트1 : 카테고리,정렬기준,상태,달성률 select값이 달라지면 
	 * 이벤트2 : 스크롤을 내리면 		
	 * 이벤트3 : 검색버튼을 누르면 	
	 *      이벤트 3-1 : 검색버튼을 누른 후 카테고리 바꾸면 검색+카테고리
	*/
	@GetMapping(value={"/list"})
	public ResponseEntity<List<ProjectDTO>> projList(HttpSession session,SearchDTO search)
			 throws FindException {
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="id1";
		if(loginId != null) {
			search.setId(loginId);
		} 
		
		//초안
		try {
			List<ProjectDTO> list = new ArrayList<ProjectDTO>();
			list = service.findProjs(search);
		/* 1.load시 요청  : list/?p=1
		 * 2.카테고리,진행상태,달성률,정렬 	: /list/?p=1&category=1&state=2&goal=3&order=date>
		 * 3.카테고리,진행상태,달성률,정렬+단어	: /list?p=1&category=0&state=0&goal=0&order=b&word=word
		 */
			return new ResponseEntity(list,null,HttpStatus.OK);//응답 변경	
			//html에서 if(list==null) 목록이 존재하지 않습니다.
		}catch(FindException e){
			return new ResponseEntity(HttpStatus.BAD_GATEWAY);//응답 변경	   
		}
	}
	
}
