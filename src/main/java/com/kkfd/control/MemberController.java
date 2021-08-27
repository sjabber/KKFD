package com.kkfd.control;

import com.kkfd.dto.MemberDTO;
import com.kkfd.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/member/*")
public class MemberController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService service;

    @PostMapping("/login")
    //public ResponseEntity<Map<String, Object>> login(String id, String pwd, HttpSession session) {
    public ResponseEntity<Object> login(String id, String pwd, HttpSession session) {
        session.removeAttribute("loginInfo");
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MemberDTO loginInfo = service.login(id, pwd);
            session.setAttribute("loginInfo", loginInfo);
            //result.put("status", 1);
            //result.put("msg", "로그인 성공");

            // 정상처리 200
            // ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
            ResponseEntity<Object> responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
            log.error(responseEntity.getBody().toString());

            return responseEntity;

        } catch (Exception e) {
            e.printStackTrace();
//            result.put("status", 0);
//            result.put("msg", "로그인 실패");

            // 비인증 401
            ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
            log.error(responseEntity.getBody().toString());

            return responseEntity;
        }
    }

    public ResponseEntity<Map<>>
}
