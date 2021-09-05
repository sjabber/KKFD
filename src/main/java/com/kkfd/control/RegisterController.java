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
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
    public ResponseEntity register(@RequestPart MultipartFile thumbnail,
                         @RequestPart List<MultipartFile> details,
                         @RequestPart MultipartFile profile,
                         ProjectDTO project, HttpSession session) {
        MemberDTO m = (MemberDTO) session.getAttribute("loginInfo");
        ResponseEntity responseEntity;

        if (m == null) {
            // 로그인 X
            responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
            System.out.println("로그인이 안되어있다.");
            return responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);

        } else {
            // 로그인 O
            // 1. 입력정보 DB에 저장
            try {
                CreatorDTO creator = creatorService.findCrById(m.getMemId());

                if (creator == null) { //todo 창작자 정보가 등록되어 있지 않은 경우 (생성)
                    // 현재 로그인한 계정의 정보를 포함시킨다.
                    creator = project.getCreator();
                    creator.setCrId(m.getMemId());

                    //KK_CREATOR 테이블에 창작자 정보 추가
                    creatorService.addCr(creator);

                } else { // todo 창작자 정보가 등록되어 있는 경우 (수정)
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

                //Note, 창작자 프로필 사진 갱신 OR 저장
                String uploadPath = servletContext.getRealPath("resource/public/img/profile/" + m.getMemId());
//                String uploadPath = servletContext.getRealPath("resource/public/img/profile/");
                // 사진이 올바르게 저장되지 않을 경우 에러를 반환한다.
                if (!SaveImg(uploadPath, profile, m.getMemId(), false)) {
                    File folder = new File(uploadPath);
                    folder.delete(); // 대상폴더 삭제
                    responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    return responseEntity;
                }

                //Note, 프로젝트 등록
                creator = project.getCreator();
                creator.setCrId(m.getMemId());
                project.setCreator(creator);

                int rowCnt = projectService.addProj(project);
                if (rowCnt == 0) {
                    //추후 예외처리
                    responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    return responseEntity;
                }

                uploadPath = servletContext.getRealPath("resource/public/img/project/" + project.getProjNo());

                // 이미지 파일 저장
                if (SaveImg(uploadPath, thumbnail, String.valueOf(project.getProjNo()), true) && SaveImgs(uploadPath, details, project.getProjNo())) {
                    responseEntity = new ResponseEntity(HttpStatus.OK);
                    return responseEntity;
                } else {
                    File folder = new File(uploadPath);
                    folder.delete(); // 대상폴더 삭제
                    responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                    return responseEntity;
                }

            } catch (Exception e) {
                e.printStackTrace();


                responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
        }
    }

    public boolean SaveImg(String uploadPath, MultipartFile file, String id, boolean choice) {
        // 경로생성
        if (!new File(uploadPath).exists()) {
            log.info("업로드 실제경로 생성");
            new File(uploadPath).mkdirs();
        }

        // 업로드한 파일의 본래 확장자를 알아낸다.
        String FileName = file.getOriginalFilename();
        String FileExtention = StringUtils.getFilenameExtension(FileName);

        // 업로드한 파일 검증 로직
        if (!"".equals(FileName) && file.getSize() != 0) {
            System.out.println("thumbnail 파일크기 : " + file.getSize() + ", 파일이름 : " + FileName);
            File realfile;
            if (choice == true) {
                // 섬네일
                realfile = new File(uploadPath, id + "_t." + FileExtention);
            } else {
                // 회원 프로필
                realfile = new File(uploadPath, id + "." + FileExtention);
            }
            try {
                FileCopyUtils.copy(file.getBytes(), realfile); // 파일 내용을 복사하여 file에 붙여넣기
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false;
    }

    public boolean SaveImgs(String uploadPath, List<MultipartFile> files, int id) {
        // return 체크
        boolean check = true;

        // 경로생성
        if (!new File(uploadPath).exists()) {
            log.info("업로드 실제경로 생성");
            new File(uploadPath).mkdirs();
        }

        for (int i = 1; i <= files.size(); i++) {
            // 업로드한 파일의 본래 확장자를 알아낸다.
            MultipartFile file = files.get(i-1);
            String FileName = file.getOriginalFilename();
            String FileExtention = StringUtils.getFilenameExtension(FileName);

            // 업로드한 파일 검증 로직
            if (!"".equals(FileName) && file.getSize() != 0) {
                System.out.println("thumbnail 파일크기 : " + file.getSize() + ", 파일이름 : " + FileName);

                File realfile = new File(uploadPath, id + "_" + i + "." + FileExtention);
                try {
                    FileCopyUtils.copy(file.getBytes(), realfile); // 파일 내용을 복사하여 file에 붙여넣기
                } catch (IOException e) {
                    e.printStackTrace();
                    check = false;
                }
            }
        }

        return check;
    }
}
