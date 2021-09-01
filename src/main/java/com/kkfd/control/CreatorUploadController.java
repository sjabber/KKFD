package com.kkfd.control;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kkfd.dto.MemberDTO;

@RestController
@RequestMapping("/creator/*")
public class CreatorUploadController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ServletContext servletContext;

	@PostMapping("/profile")
	public void upload(HttpSession session, @RequestPart MultipartFile imgFile) {
		MemberDTO m = (MemberDTO)session.getAttribute("loginInfo");
		String loginId = "t";
//		if(m == null) {
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401 : 권한없음
//		} 
//		String loginId= m.getMemId();

		//경로 생성
		String uploadPath = servletContext.getRealPath("resource/public/img/profile");
		if ( ! new File(uploadPath).exists()) {
			new File(uploadPath).mkdirs();
		}
		String imgFileName = imgFile.getOriginalFilename();
		int index = imgFileName.lastIndexOf(".");
		String extension = imgFileName.substring(index); //.jpg

		if(!"".equals(imgFileName) && imgFile.getSize()!=0){
			log.info("파일크기 : " + imgFile.getSize() + ", 파일이름 : " + imgFileName);
			String fileName = loginId+extension;
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
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}




