package com.kkfd.control;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.kkfd.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import com.kkfd.service.CreatorService;
import com.kkfd.service.ProjectService;

@RestController
@RequestMapping("/creator/*")
public class CreatorController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CreatorService creatorService;//Creaotor Table에 접근 조회,입력,수정

	@Autowired
	private ProjectService projetService;//Project Table에 접근 크리에이터 마이 프로젝트 조회

	@GetMapping
	public ResponseEntity<CreatorDTO> inquiryCr(HttpSession session) {
		MemberDTO m = (MemberDTO) session.getAttribute("loginInfo");

		if(m == null) {
			// 로그인 X
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);
		} else {
			// 로그인 O
			try {
				CreatorDTO creator = creatorService.findCrById(m.getMemId());
				if(creator==null) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);//프로젝트 없음

				}
				return new ResponseEntity<CreatorDTO>(creator,HttpStatus.OK);//참여자 정상정으로 불러오는경우
			}catch(FindException e){
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL
			}
		}
	}

	@PostMapping
	public ResponseEntity<Integer> registerCr(@RequestBody CreatorDTO creator) {
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="id12";
		if(loginId == null) {
		}
		creator.setCrId(loginId);
		try {
			int rowCnt = creatorService.addCr(creator);
			if(rowCnt==0) {

			}
			return new ResponseEntity<>(rowCnt,HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}
	}

	@PutMapping
	public ResponseEntity<Integer> changeCr(@RequestBody CreatorDTO creator) {
		log.error(creator.toString());

		try {
			int rowCnt = creatorService.modifyCr(creator);
			if(rowCnt==0) {

			}
			return new ResponseEntity<>(rowCnt,HttpStatus.OK);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}
	}


	@GetMapping(value={"/projects/{pageNo}"})
	public ResponseEntity<PageDTO<ProjectDTO>> myProjects(HttpSession session
			,@PathVariable(name="pageNo") int currentPage){
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="t";
		if(loginId == null) {
			return new ResponseEntity<PageDTO<ProjectDTO>>(HttpStatus.UNAUTHORIZED);//권한없음
		}
		try {
			int totalCnt = projetService.countMyProjs(loginId);
			log.info(String.valueOf(totalCnt));

			if(totalCnt==0) {
				return new ResponseEntity<PageDTO<ProjectDTO>>(HttpStatus.NO_CONTENT);//프로젝트 없음	
			}
			int totalPage =  (int) Math.ceil(totalCnt/(double)PageDTO.CNT_PER_PAGE);
			List<ProjectDTO> list = projetService.findProjsByCrId(loginId,currentPage);

			Date now = new Date();//서버 기준시간이 한국시간?
			//project.getProjStatus() 
			//0:취소
			//1:정상(10:진행예정 / 11:달성률<25 / 12: 25<=달성률<75 / 13: 75<=달성률<100 / 15:100<=달성률<제한수량 /  19:제한수량도달)
			//2:마감(20:실패, 21:성공)
			int status = 0;
			for(ProjectDTO project : list) {
				if(project.getProjStatus()==0) {	//0: 취소	
					continue;
				}
				if(now.before(project.getProjStart())) {		//10:현재가 시작일 전 - 진행예정
					status=10;
				}else if(now.before(project.getProjEnd())) {	//<현재가 시작일 후 & 종료일 전> - 진행중
					if(project.getProjQuantity()==project.getProjTargetcnt()){	//19: 조기마감
						status = 19;
					}else {
						if(project.getProjGoals()<25) {
							status = 11;
						}else if(project.getProjGoals()<75) {
							status = 12;
						}else if(project.getProjGoals()<100){
							status = 13;
						}else {
							status = 15;
						}
					}
				}else {											//<현재가 종료일 후> - 종료
					if(project.getProjGoals()<100) {			//20:종료인 프로젝트중 달성률 100%미만 실패
						status=20;
					}
					else{										//21:종료인 프로젝트중 달성률 100%이상 성공
						status=21;
					}
				}
				project.setProjStatus(status);
			}
			String url = "http://localhost:9999/kkfd/creator/projects/";
			PageDTO<ProjectDTO> pd = new PageDTO<ProjectDTO>(currentPage,totalPage ,list, url);

			return new ResponseEntity<PageDTO<ProjectDTO>>(pd,HttpStatus.OK);//프로젝트 있는경우
		}catch(FindException e){
			return new ResponseEntity<PageDTO<ProjectDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);//응답 변경	   
		}
	}
}
