package com.kkfd.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;
import com.kkfd.service.ProjectMainService;

@RestController
@RequestMapping("/main")
public class ProjectMainController {
	@Autowired
	private ProjectMainService service;
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping
	public ResponseEntity<List<ProjectMainDTO>> mainProjList(HttpSession session) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
//		MemberDTO m = new MemberDTO();
		//m.setMemId("id1");
//		log.error(m.toString());
		try {
			List<ProjectMainDTO> list = new ArrayList<ProjectMainDTO>();
			if(m == null) {
				list = service.findMainProjs();
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				String id = m.getMemId();
				list = service.findMainProjs(id);
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
		}
		
	}
}
