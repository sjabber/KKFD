package com.kkfd.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.PageDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import com.kkfd.service.CreatorService;
import com.kkfd.service.FundingService;

@RestController
@RequestMapping("/project/*")
public class ProjectController2 {
	@Autowired
	private CreatorService projService;//ProjService으로 변경 Project Table에 project취소

	@Autowired
	private FundingService funService;//Funding Table에 접근 funding DTO로 목록가져오기

	@PutMapping(value={"/{projNo}"})
	public ResponseEntity<String> cancleProj(HttpSession session
			,@PathVariable int projNo){
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="t";
		if(loginId == null) {
			return new ResponseEntity<String>("로그인 하세요",HttpStatus.UNAUTHORIZED);//권한없음
		} 
		try {
			int rowCnt = projService.cancleProj(projNo, loginId);
			if(rowCnt==0) {//취소할 프로젝트 없음(크리에이터 아이디가 틀리거나 프로젝트번호가 이미 없거나)
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT);//프로젝트 없음	
			}
			return new ResponseEntity<String>(HttpStatus.OK);//취소 성공	

		} catch (ModifyException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping(value={"/{projNo}/fundings"})
	public ResponseEntity<List<FundingDTO>> joinerList(@PathVariable int projNo){
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="t";
		if(loginId == null) {
		} 
		try {
			List<FundingDTO> list = funService.findFunsByProjNo(projNo,loginId);

			if(list.size()==0) {//로그인한 아이디가 펀딩참여자 볼 권한이 없을 때 WHERE p.proj_id=#{loginId}
				return new ResponseEntity<List<FundingDTO>>(HttpStatus.UNAUTHORIZED);
			}

			return new ResponseEntity<List<FundingDTO>>(list,HttpStatus.OK);//참여자 정상정으로 불러오는경우
		}catch(FindException e){
			return new ResponseEntity<List<FundingDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}
	}



}





