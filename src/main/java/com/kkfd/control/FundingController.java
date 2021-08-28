package com.kkfd.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FundingService service;
	
	@PutMapping(value={"/fundings"})	//성열님~여기 복수형쓰고 싶어서 클래스 위에 @RequestMapping 뺐어요 메서드마다 지정해줍시다.
	public ResponseEntity<Integer> modifyFuns(@RequestBody List<FundingDTO> list) {
		//String loginId = (String)session.getAttribute("loginId");
		String loginId="t";
		if(loginId == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//권한없음
		}
		try {
			int rowCnt = service.modifyFuns(list);
			if(rowCnt==0){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<Integer>(rowCnt,HttpStatus.OK);
		} catch (ModifyException e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);//SQL   
		}						    
	}
	
}
