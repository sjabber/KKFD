package com.kkfd.control;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kkfd.dto.CreatorDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.FindException;
import com.kkfd.service.CreatorService;

@RestController
@RequestMapping("/creator/*")
public class CreatorUploadController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private CreatorService service;

	@PostMapping("/profile")
	public ResponseEntity upload(HttpSession session, @RequestPart MultipartFile imgFile) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		if(m == null) {
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);//401 : 권한없음
		} 
		String loginId= m.getMemId();
		try {
			CreatorDTO creator = service.findCrById(loginId);
			if(creator==null) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);//204 : 크리에이터 미등록 소개부터 작성
			}
		} catch(FindException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500 : 
		}

		//경로 생성
		String uploadPath = servletContext.getRealPath("resource/public/img/profile") +"/"+loginId;
		if ( ! new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}
		String imgFileName = imgFile.getOriginalFilename();
		if(!"".equals(imgFileName) && imgFile.getSize()!=0){//form에 required로 전송되었지만 한번더 확인
			log.info("파일크기 : " + imgFile.getSize() + ", 파일이름 : " + imgFileName);
			String fileName = loginId+".png";
			File file = new File(uploadPath,fileName); //파일생성
			try {
				FileCopyUtils.copy(imgFile.getBytes(), file);//imgFile파일 내용을 복사해서 file에 붙여 넣기
				/*				
				//섬네일
				String contentType = imgFile.getContentType(); //파일형식				
				if(contentType.startsWith("image")) { //이미지파일인 경우
					String thumbnailName =  "s_"+fileName; //s_파일이름
					File thumbnailFile = new File(uploadPath,thumbnailName);
					FileOutputStream thumbnail = new FileOutputStream(thumbnailFile);
					InputStream imgFileIS = imgFile.getInputStream();
					int width = 100;
					int height = 100;
					//섬네일파일 만들기
					Thumbnailator.createThumbnail(imgFileIS, thumbnail, width, height);
				 */
				return new ResponseEntity(HttpStatus.OK);//200:정상 업로드
			}catch(Exception e){
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // FileCopyUtils 
			}
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // imgFile.getSize()==0
		}
	}
}






