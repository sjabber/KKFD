package com.kkfd.control;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.ModifyException;
import com.kkfd.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController2 {
	@Autowired
	private MemberService service;

	@GetMapping
	public ResponseEntity<MemberDTO> inquiryMem(HttpSession session) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		try {
			MemberDTO member = service.findById(loginId);
			return new ResponseEntity<MemberDTO>(member, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}
	}
	
	@PutMapping	
	public ResponseEntity changeMem(HttpSession session, @RequestBody MemberDTO member) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId = m.getMemId();
		member.setMemId(loginId);
		try {
			service.modify(member);
			return new ResponseEntity(HttpStatus.OK);
		} catch (ModifyException e) {
			e.printStackTrace();
			if(e.getMessage().equals("0")) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);	//403 : 등록된 행 수 0   
			}
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}						    
	}

}
