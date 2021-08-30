package com.kkfd.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.service.FundingService;

@RestController
@RequestMapping("/funding/*")
public class FundingController {
	
	@Autowired
	private FundingService service;
	
	@PostMapping(value= {"/apply"})
	public ResponseEntity<String> insertFunding (HttpSession session, @RequestBody FundingDTO funding) {
		String loginId="id3";
		MemberDTO m = new MemberDTO();
		m.setMemId(loginId);
		try {
			service.addApply(funding);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

}
