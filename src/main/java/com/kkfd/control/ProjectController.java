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

	@GetMapping(value={"/list/{page}"})
	public ResponseEntity<List<ProjectDTO>> projList(HttpSession session,SearchDTO search, @PathVariable int page) {
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
			String imgPath = servletContext.getRealPath("img/project");
			String[] extension = {"png","jpg"};
			String imgDir =  "";
			for(ProjectDTO project : list) {
				imgDir = imgPath + "/" + project.getProjNo();
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
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);	   
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
			if(project == null) {
				return new ResponseEntity<ProjectDTO>(project, HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<ProjectDTO>(project, HttpStatus.OK);
			}
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value={"/{no}/history"})
	public ResponseEntity<List<ProjectDTO>> prevProj(@PathVariable int no) {
		try {
			List<ProjectDTO> list = service.findPrevProj(no);
			return new ResponseEntity<List<ProjectDTO>>(list, HttpStatus.OK);
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value={"/{no}/bookmark"})
	public ResponseEntity<?> addbookmark(HttpSession session, @PathVariable int no) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		String loginId;
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			loginId = m.getMemId();
			try {
				service.addBookmark(no, loginId);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (AddException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@DeleteMapping(value={"/{no}/bookmark"})
	public ResponseEntity<?> removebookmark(HttpSession session, @PathVariable int no) {
		String loginId;
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			loginId = m.getMemId();
			try {
				service.removeBookmark(no, loginId);
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (RemoveException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@GetMapping(value={"/bmlist/{page}"})
	public ResponseEntity<List<ProjectDTO>> projList(HttpSession session, String id, @PathVariable int page)
			throws FindException {
		String loginId;
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} else {
			loginId = m.getMemId();
			try {
				List<ProjectDTO> list = new ArrayList<ProjectDTO>();
				list = service.findMyBookmark(loginId, page);
				String imgPath = servletContext.getRealPath("img/project");
				String[] extension = {"png","jpg"};
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
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  
			}
		}
	}
	
}