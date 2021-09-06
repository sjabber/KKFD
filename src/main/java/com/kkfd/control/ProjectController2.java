package com.kkfd.control;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.kkfd.service.ProjectService;

@RestController
@RequestMapping("/project/*")
public class ProjectController2 {

	@Autowired
	private ProjectService service;
	
	@Autowired
	private ServletContext servletContext;

	@PutMapping(value={"/{projNo}"})
	public ResponseEntity cancleProj(HttpSession session
			,@PathVariable int projNo){
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		try {
			service.cancleProj(projNo, loginId);
			return new ResponseEntity<>(HttpStatus.OK);//취소 성공	

		} catch (ModifyException e) {
			if(e.getMessage().equals("0")) {//취소할 프로젝트 없음 proj_id=#{loginId}
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);//취소한 권한없음
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping(value={"/{projNo}/fundings"})
	public ResponseEntity<Map<String,Object>> joinList(HttpSession session,@PathVariable int projNo){
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		try {
			Map<String,Object> result = service.findFunsByProjNo(projNo,loginId);
			ProjectDTO project = (ProjectDTO)(result.get("project"));
			String imgDir = servletContext.getRealPath("img/project") +  "/" + projNo;
			File[] files = new File(imgDir).listFiles();
			if(files!=null) {
			for(File file: files) {
				String fileName = file.getName();
				int indexOfExtension = fileName.lastIndexOf("_t.");
				if(indexOfExtension > -1) { //"_t."를 포함하는 이름의 파일이 있는 경우
					project.setExt(fileName.substring(indexOfExtension+3)); //확장자 
					break;
				}
			}
			}
			result.put("project", project);//확장자 확인후 다시 map에 넣는다.
			if(result.size()==0) {//로그인한 아이디가 펀딩참여자 볼 권한이 없을 때 WHERE p.proj_id=#{loginId}
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);//참여자 정상정으로 불러오는경우
		}catch(FindException e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 
		}
	}
}





