package com.kkfd.control;

import com.kkfd.dto.CreatorDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.service.CreatorService;
import com.kkfd.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/project")
public class RegisterController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CreatorService creatorService;

    @Autowired
    private ServletContext servletContext;

    @PostMapping("/register")
    public ResponseEntity register(@RequestPart List<MultipartFile> details , MultipartFile thumbnail, MultipartFile profile,
                                   ProjectDTO project, HttpSession session) {
        MemberDTO m = (MemberDTO) session.getAttribute("loginInfo");
        ResponseEntity responseEntity;

        log.info("요청 전달 데이터 title() :" + project.getProjTitle() + ", Intro=" + project.getProjIntro());
        log.info("이미지 사이즈 : " + details.size());
        log.info("상세 이미지1 사이즈 : " + details.get(0).getSize());
        log.info("상세 이미지2 사이즈 : " + details.get(1).getSize());
        log.info("상세 이미지3 사이즈 : " + details.get(2).getSize());
        log.info("대표 이미지 사이즈 : " + thumbnail.getSize());
        log.info("프로필 이미지 사이즈 : " + profile.getSize());

        if (m == null) {
            // 로그인 X
            responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
            return responseEntity;

        } else {
            // 로그인 O
            String uploadPath = servletContext.getRealPath("resource/public/img/profile");
            log.info("업로드 실제경로:" + uploadPath);

            // 경로생성성
            /*if (!new File(uploadPath).exists()) {
                log.info("업로드 실제경로 생성");
                new File(uploadPath).mkdirs();
            }*/
            if (details != null) {
                for (MultipartFile detail : details) {
                    String detailFileName = detail.getOriginalFilename();

                    if (!"".equals(detailFileName) && detail.getSize() != 0) {

                    }
                }
            }


           try {
                CreatorDTO creator = creatorService.findCrById(m.getMemId());

                if (creator == null) { //todo 창작자 정보가 등록되어 있지 않은 경우
                    // 현재 로그인한 계정의 정보를 포함시킨다.
                    creator = project.getCreator();
                    creator.setCrId(m.getMemId());

                    //KK_CREATOR 테이블에 창작자 정보 추가
                    creatorService.addCr(creator);


                } else { // todo 창작자 정보가 등록되어 있는 경우
                    // 수정된 정보를 반영한다.
                    CreatorDTO updatedCr = project.getCreator();
                    creator.setCrNn(updatedCr.getCrNn());
                    creator.setCrIntro(updatedCr.getCrIntro());
                    creator.setCrBank(updatedCr.getCrBank());
                    creator.setCrAcno(updatedCr.getCrAcno());
                    creator.setCrAcholder(updatedCr.getCrAcholder());
                    project.setCreator(creator);

                    // KK_CREATOR 테이블 창작자 정보 수정
                    creatorService.modifyCr(creator);

                }

                // 공통사항 (프로젝트 등록)
                // creator id 정보에 현재 로그인한 계정의 정보를 넣는다.
                // 위에서 이미 id 정보가 들어간 CreatorDTO 객체가 생성됨.
                /*creator = project.getCreator();
                creator.setCrId(m.getMemId());
                project.setCreator(creator);*/


                //이미지 확장자 확인(.jpg, .png)후 실제 파일이름.확장자 반환
                String uploadPath = servletContext.getRealPath("resource/public/img/profile");
                String[] extension = {".jpg", ".png"};
                String path = "/img/blank.png";
                for (int i = 0; i < extension.length; i++) {
                    File file = new File(uploadPath, m.getMemId() + extension[i]);
                    if (file.exists()) {
                        path = "/img/profile/" + m.getMemId() + extension[i];
                        creator.setImgPath(path);
                    }
                }


                int rowCnt = projectService.addProj(project);
                if (rowCnt == 0) {
                    //추후 예외처리
                    responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    return responseEntity;
                }


                responseEntity = new ResponseEntity(HttpStatus.OK);
                return responseEntity;

            } catch (Exception e) {
                e.printStackTrace();
                responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }
    }

    /*@PostMapping("/register")
    public ResponseEntity register(@RequestBody ProjectDTO project, HttpSession session) {
        MemberDTO m = (MemberDTO) session.getAttribute("loginInfo");
        ResponseEntity responseEntity;

        if (m == null) {
            // 로그인 X
            responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
            return responseEntity;

        } else {
            // 로그인 O
            try {
                CreatorDTO creator = creatorService.findCrById(m.getMemId());

                if (creator == null) { //todo 창작자 정보가 등록되어 있지 않은 경우
                    // 현재 로그인한 계정의 정보를 포함시킨다.
                    creator = project.getCreator();
                    creator.setCrId(m.getMemId());

                    //KK_CREATOR 테이블에 창작자 정보 추가
                    creatorService.addCr(creator);


                } else { // todo 창작자 정보가 등록되어 있는 경우
                    // 수정된 정보를 반영한다.
                    CreatorDTO updatedCr = project.getCreator();
                    creator.setCrNn(updatedCr.getCrNn());
                    creator.setCrIntro(updatedCr.getCrIntro());
                    creator.setCrBank(updatedCr.getCrBank());
                    creator.setCrAcno(updatedCr.getCrAcno());
                    creator.setCrAcholder(updatedCr.getCrAcholder());
                    project.setCreator(creator);

                    // KK_CREATOR 테이블 창작자 정보 수정
                    creatorService.modifyCr(creator);

                }

                // 공통사항 (프로젝트 등록)
                // creator id 정보에 현재 로그인한 계정의 정보를 넣는다.
                // 위에서 이미 id 정보가 들어간 CreatorDTO 객체가 생성됨.
                *//*creator = project.getCreator();
                creator.setCrId(m.getMemId());
                project.setCreator(creator);*//*


                //이미지 확장자 확인(.jpg, .png)후 실제 파일이름.확장자 반환
                String uploadPath = servletContext.getRealPath("resource/public/img/profile");
                String[] extension = {".jpg", ".png"};
                String path = "/img/blank.png";
                for (int i = 0; i < extension.length; i++) {
                    File file = new File(uploadPath, m.getMemId() + extension[i]);
                    if (file.exists()) {
                        path = "/img/profile/" + m.getMemId() + extension[i];
                        creator.setImgPath(path);
                    }
                }


                int rowCnt = projectService.addProj(project);
                if (rowCnt == 0) {
                    //추후 예외처리
                    responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    return responseEntity;
                }


                responseEntity = new ResponseEntity(HttpStatus.OK);
                return responseEntity;

            } catch (Exception e) {
                e.printStackTrace();
                responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }
    }*/

}
