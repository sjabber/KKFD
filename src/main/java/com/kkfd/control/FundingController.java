package com.kkfd.control;


import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.PageDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import com.kkfd.service.FundingService;

@RestController
public class FundingController {
	@Autowired
	private FundingService service;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value= {"/funding"})
	public ResponseEntity<String> insertFunding (HttpSession session, @RequestBody FundingDTO funding) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) { //로그인 안된 경우
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				funding.setMember(m);
				service.addFunding(funding);
				//log.info("test2");
				return new ResponseEntity<String>(HttpStatus.OK);
			} catch (AddException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	}

	@PutMapping(value={"/fundings"})
	public ResponseEntity<Integer> trackingNum(HttpSession session, @RequestBody List<FundingDTO> list) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		String loginId = "t";
//		if(m == null) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
//		}
//		String loginId= m.getMemId();
		try {
			int rowCnt = service.modifyFuns(list);
			return new ResponseEntity<Integer>(rowCnt,HttpStatus.OK);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL
		}
	}

	@GetMapping(value={"/fundings"})
	public ResponseEntity<PageDTO<FundingDTO>> fundinglist(HttpSession session,
			@RequestParam int term,
			@RequestParam int state,
			@RequestParam int page){
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		String loginId = "t";
//		if(m == null) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
//		}
//		String loginId= m.getMemId();

		try {
			int totalCnt = service.countMyFunList(loginId, term, state);
			if(totalCnt==0) {
				return new ResponseEntity<PageDTO<FundingDTO>>(HttpStatus.NO_CONTENT);//프로젝트 없음
			}
			int totalPage =  (int) Math.ceil(totalCnt/(double)PageDTO.CNT_PER_PAGE);
			List<FundingDTO> list = service.findFunsById(loginId, term, state, page);
			Date now = new Date();
			//project.getProjStatus()
			//0:펀딩무산(프로젝트 취소+프로젝트 실패)
			//1:진행중(10:진행중 / 11:임박 / 12:결제예정(달성:마감전 100%))
			//2:펀딩성사(프로젝트 성공)-결제
			//3:전체
			int status = 0;
			for(FundingDTO funding : list) {
				ProjectDTO project = funding.getProject();
				if(project.getProjStatus()==0) {	//0: 취소
					continue;
				}
				if(now.before(project.getProjStart())) {		//10:현재가 시작일 전 - 진행예정
					status=10;
				}else if(now.before(project.getProjEnd())) {	//<현재가 시작일 후 & 종료일 전> - 진행중
					if(project.getProjGoals()==100){
						status=12;
					}else if(project.getProjGoals()>=95){
						status=11;
					}else {
						status=10;
					}
				}else {											//<현재가 종료일 후> - 종료
					if(project.getProjGoals()<100) {			//20:종료인 프로젝트중 달성률 100%미만 실패
						status=0;
					}
					else{										//21:종료인 프로젝트중 달성률 100%이상 성공
						status=2;
					}
				}
				project.setProjStatus(status);
				funding.setProject(project);
			}
			String url = "?term=" + term+ "?state=" +state;
			PageDTO<FundingDTO> pd = new PageDTO<FundingDTO>(page,totalPage ,list, url);
			return new ResponseEntity<>(pd,HttpStatus.OK);//프로젝트 있는경우
		}catch(FindException e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//응답 변경
		}
	}
}


