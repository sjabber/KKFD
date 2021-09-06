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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;
import com.kkfd.service.ProjectMainService;

@RestController
@RequestMapping("/main")
public class ProjectMainController {
	@Autowired
	private ProjectMainService service;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping
	public ResponseEntity<List<ProjectMainDTO>> mainProjList(HttpSession session) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		try {
			List<ProjectMainDTO> list = new ArrayList<ProjectMainDTO>();
			if(m == null) {
				list = service.findMainProjs();
				String imgPath = servletContext.getRealPath("img/project");
				String[] extension = {"png","jpg"};
				String imgDir =  "";
				for(ProjectMainDTO projectMain : list) {
					imgDir = imgPath + "/" + projectMain.getProject().getProjNo();
					for(int i=0;i<extension.length ;i++) {
						File file = new File(imgDir , projectMain.getProject().getProjNo() + "_t." + extension[i]);
						if(file.exists()) {
							projectMain.getProject().setExt(extension[i]);
						}
					}
				}
				if(list.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(list, HttpStatus.OK);
			} else {
				String id = m.getMemId();
				list = service.findMainProjs(id);
				String imgPath = servletContext.getRealPath("img/project");
				String[] extension = {"png","jpg"};
				String imgDir =  "";
				for(ProjectMainDTO projectMain : list) {
					imgDir = imgPath + "/" + projectMain.getProject().getProjNo();
					for(int i=0;i<extension.length ;i++) {
						File file = new File(imgDir , projectMain.getProject().getProjNo() + "_t." + extension[i]);
						if(file.exists()) {
							projectMain.getProject().setExt(extension[i]);
						}
					}
				}
				if(list.size() == 0) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
		} catch (FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
