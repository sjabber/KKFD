package com.kkfd.control;

import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member/*")
public class MemberController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService service;

    // Note 로그인 기능 구현
    @PostMapping("/login")
    //public ResponseEntity<Map<String, Object>> login(String id, String pwd, HttpSession session) {
    public ResponseEntity login(@RequestBody MemberDTO member, HttpSession session) {
        session.removeAttribute("loginInfo");
        //Map<String, Object> result = new HashMap<String, Object>();
        ResponseEntity responseEntity;
        try {
            MemberDTO loginInfo = service.login(member.getMemId(), member.getMemPwd());
            session.setAttribute("loginInfo", loginInfo);
            //result.put("status", 1);
            //result.put("msg", "로그인 성공");

            // 정상처리 200
            // ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
            responseEntity = new ResponseEntity(HttpStatus.OK);
            //log.info(responseEntity.getBody().toString());

            return responseEntity;

        } catch (Exception e) {
            e.printStackTrace();
//            result.put("status", 0);
//            result.put("msg", "로그인 실패");

            // 비인증 401
            //ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            responseEntity = new ResponseEntity(HttpStatus.UNAUTHORIZED);
            //log.error(responseEntity.getBody().toString());

            return responseEntity;
        }
    }

    @GetMapping("/logincheck")
    public ResponseEntity<Map<String, String>> loginCheck(HttpSession session) {
        ResponseEntity<Map<String, String>> responseEntity;
        MemberDTO m = (MemberDTO) session.getAttribute("loginInfo");

        Map<String, String> map = new HashMap<>();

        if (m == null) {
            responseEntity = new ResponseEntity(HttpStatus.NO_CONTENT); //204
        } else {
            map.put("name", m.getMemName());
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
        }
        return responseEntity;
    }

    // Note 로그아웃 기능 구현
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        ResponseEntity<String> responseEntity;

        try {
            session.invalidate();
            responseEntity = new ResponseEntity<>("isOk", HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            // 서버에러 500
            responseEntity = new ResponseEntity<>("err", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            return responseEntity;
        }
    }

    // Note 아이디 중복 기능 구현, pathVariable로 id값을 받아온다.
    @GetMapping("/{id}")
    public ResponseEntity idDupChk(@PathVariable String id) {
        ResponseEntity<String> responseEntity;
        try {
            boolean check = service.duplicateCheck(id);
            if (check) { // 정상처리된 경우
                // 200 반환
                responseEntity = new ResponseEntity(HttpStatus.OK);
                return responseEntity;
            } else { // 중복될 경우
                // 400에러 반환
                responseEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
                return responseEntity;
            }

        } catch (FindException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody MemberDTO m) {
        ResponseEntity responseEntity;
        try {
            service.signup(m);
            // 회원가입 완료, 200 반환
            responseEntity = new ResponseEntity(HttpStatus.OK);

        } catch (AddException e) {
            e.printStackTrace();
            // 400에러 반환
            responseEntity = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
