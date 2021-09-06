package com.kkfd.control;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

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

import com.kkfd.dto.CreatorDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.PageDTO;
import com.kkfd.dto.ProjectDTO;
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
	private ServletContext servletContext;

	@Autowired
	private CreatorService creatorService;//Creaotor Table에 접근 조회,입력,수정

	@Autowired
	private ProjectService projetService;//Project Table에 접근 크리에이터 마이 프로젝트 조회

	@GetMapping
	public ResponseEntity<CreatorDTO> inquiryCr(HttpSession session) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();

		try {
			CreatorDTO creator = creatorService.findCrById(loginId);
			if(creator==null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204 : 크리에이터 테이블에 없음
			}

			//이미지 확인("/img/(id)/(id).png)후 없으면 기본이미지 출력
//			String dir = servletContext.getRealPath("resource/public/img/profile")+"/"+loginId;
			String dir = servletContext.getRealPath("img/profile")+"/"+loginId;
			String path = null;
			File file = new File(dir,loginId+".png");
			if(file.exists()) {
				path = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/profile/" +loginId +"/"+loginId+".png";
			}else{
				path = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/blank.png";
			}
			creator.setImgPath(path);
			return new ResponseEntity<CreatorDTO>(creator,HttpStatus.OK);//200 : 크리에이터 정보조회
		}catch(FindException e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500 : 서버 내부 문제  
		}
	}

	@PostMapping
	public ResponseEntity registerCr(HttpSession session, @RequestBody CreatorDTO creator) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		creator.setCrId(loginId);
		try {
			creatorService.addCr(creator);
			return new ResponseEntity(HttpStatus.OK);				//200 : 크리에이터로 등록 완료
		} catch (AddException e) {
			e.printStackTrace();
			if(e.getMessage().equals("0")) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);	//404 : 등록된 행 수 0   
			}
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);//500 : 서버 내부 문제  
		}						    
	}

	@PutMapping	
	public ResponseEntity changeCr(HttpSession session, @RequestBody CreatorDTO creator) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();

		//Creator Table에 id 존재 여부 확인후 update
		try {
			CreatorDTO prevCreator = creatorService.findCrById(loginId);
			if(!prevCreator.getCrId().equals(creator.getCrId())) {//Creator 테이블에 있는 아이디와 입력한 Creator의 id 일치확인
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);	//401 : 권한없음
			}
		} catch (FindException e1) {
			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500 : 서버 내부오류  
		}
		//정보 수정
		try {
			creatorService.modifyCr(creator);
			return new ResponseEntity<>(HttpStatus.OK);				//200 : 크리에이터 정보 수정완료
		} catch (ModifyException e) {
			e.printStackTrace();
			if(e.getMessage().equals("0")) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404 : 수정된 행 수 0   
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500 : 서버 내부오류  
		}						    
	}


	@GetMapping(value={"/projects/{pageNo}"})
	public ResponseEntity<PageDTO<ProjectDTO>> myProjects(HttpSession session
			,@PathVariable(name="pageNo") int currentPage){
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		try {
			int totalCnt = projetService.countMyProjs(loginId);
			log.info(String.valueOf(totalCnt));

			if(totalCnt==0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);//프로젝트 없음	
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
					if(project.getProjQuantity()==project.getProjLimitcnt()){	//19: 조기마감
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
			String url = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/creator/projects/";
			PageDTO<ProjectDTO> pd = new PageDTO<ProjectDTO>(currentPage,totalPage ,list, url);
			log.info(String.valueOf("성공"));

			return new ResponseEntity<PageDTO<ProjectDTO>>(pd,HttpStatus.OK);//프로젝트 있는경우
		}catch(FindException e){
			return new ResponseEntity<PageDTO<ProjectDTO>>(HttpStatus.INTERNAL_SERVER_ERROR);//응답 변경	   
		}
	}
}
