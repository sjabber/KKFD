package com.kkfd.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.RemoveException;
import com.kkfd.service.ProjectService;

@RestController
@RequestMapping("/project/*")
public class ProjectController {

	@Autowired
	private ProjectService service;
	
	@Autowired
	private ServletContext servletContext;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	 * 이벤트1 : 카테고리,정렬기준,상태,달성률 select값이 달라지면
	 * 이벤트2 : 스크롤을 내리면
	 * 이벤트3 : 검색버튼을 누르면
	 *      이벤트 3-1 : 검색버튼을 누른 후 카테고리 바꾸면 검색+카테고리
	 */
	@GetMapping(value={"/list/{page}"})
	public ResponseEntity<List<ProjectDTO>> projList(HttpSession session,SearchDTO search, @PathVariable int page) {
//		String loginId = (String)session.getAttribute("loginId");
		
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			search.setId(null);
		} else {
			search.setId(m.getMemId());
		}
		search.setPage(page);
		try {
			List<ProjectDTO> list = new ArrayList<ProjectDTO>();
			list = service.findProjs(search);
			//이미지 확장자 확인(.jpg, .png)후 실제 파일이름.확장자 반환
			String imgPath = servletContext.getRealPath("resource/public/img/project");
			String[] extension = {"jpg","png"};
			String imgDir =  "";
			for(ProjectDTO project : list) {
				imgDir = imgPath + "/" + project.getProjNo();
//				File[] files = new File(imgDir).listFiles();
//				for(File file: files) {
//					String fileName = file.getName();
//					int indexOfExtension = fileName.lastIndexOf("_t.");
//					if(indexOfExtension > -1) { //"_t."를 포함하는 이름의 파일이 있는 경우
//						project.setExt(fileName.substring(indexOfExtension+3)); //확장자 
//						break;
//					}
//				}
				for(int i=0;i<extension.length ;i++) {
					File file = new File(imgDir , project.getProjNo() + "_t." + extension[i]);
					if(file.exists()) {
						project.setExt(extension[i]);
					}
				}
			}
			if(list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			/* 1.load시 요청  : list/?p=1
			 * 2.카테고리,진행상태,달성률,정렬 	: /list/?p=1&category=1&state=2&goal=3&order=date>
			 * 3.카테고리,진행상태,달성률,정렬+단어	: /list?p=1&category=0&state=0&goal=0&order=b&word=word
			 */
			return new ResponseEntity<>(list,null,HttpStatus.OK);//응답 변경	
			//html에서 if(list==null) 목록이 존재하지 않습니다.
		}catch(FindException e){
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);//응답 변경	   
		}
	}

	@GetMapping(value={"/{no}"})
	public ResponseEntity<ProjectDTO> proj(HttpSession session, @PathVariable int no) {
		String loginId;
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			loginId = null;
		} else {
			loginId = m.getMemId();
		}
		try {
			ProjectDTO project = service.findByNo(no, loginId);
			return new ResponseEntity<ProjectDTO>(project, HttpStatus.OK);
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
	}

	@GetMapping(value={"/{no}/history"})
	public ResponseEntity<List<ProjectDTO>> prevProj(@PathVariable int no) {
		try {
			List<ProjectDTO> list = service.findPrevProj(no);
			return new ResponseEntity<List<ProjectDTO>>(list, HttpStatus.OK);
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
	}
	
	@PostMapping(value={"/{no}/bookmark"})
	public ResponseEntity<?> addbookmark(HttpSession session, @PathVariable int no) {
		//String loginId = (String)session.getAttribute("loginId");
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		String loginId;
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		} else {
			loginId = m.getMemId();
			try {
				service.addBookmark(no, loginId);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (AddException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		}
	}
	
	@DeleteMapping(value={"/{no}/bookmark"})
	public ResponseEntity<?> removebookmark(HttpSession session, @PathVariable int no) {
		String loginId;
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		} else {
			loginId = m.getMemId();
			try {
				service.removeBookmark(no, loginId);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (RemoveException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		}
	}
	
	@GetMapping(value={"/bmlist/{page}"})
	public ResponseEntity<List<ProjectDTO>> projList(HttpSession session, String id, @PathVariable int page)
			throws FindException {
		String loginId;
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		} else {
			loginId = m.getMemId();
			try {
				List<ProjectDTO> list = new ArrayList<ProjectDTO>();
				list = service.findMyBookmark(loginId, page);
				//이미지 확장자 확인(.jpg, .png)후 실제 파일이름.확장자 반환
				String imgPath = servletContext.getRealPath("resource/public/img/project");
				String[] extension = {"jpg","png"};
				String imgDir =  "";
				for(ProjectDTO project : list) {
					imgDir = imgPath + "/" + project.getProjNo() ;
					for(int i=0;i<extension.length ;i++) {
						File file = new File(imgDir , project.getProjNo() + "_t." + extension[i]);
						if(file.exists()) {
							project.setExt(extension[i]);
						}
					}
				}
				if(list.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(list,null,HttpStatus.OK);
			}catch(FindException e){
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);  
			}
		}
	}

}

