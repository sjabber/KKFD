package com.kkfd.service;

import com.kkfd.dao.MemberDAO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
//    @Qualifier("memberDAO")
    private MemberDAO dao;

    /**
     * 고객이 회원가입한다.
     * @param m 추가할 고객의 정보
     * @throws AddException 회원가입 실패(아이디 중복)했을 경우 발생
     */
    public void signup(MemberDTO m) throws AddException {
        dao.insert(m);
    }

    /**
     *
     * @param id
     * @param pwd
     * @return
     * @throws FindException
     */
    public MemberDTO login(String id, String pwd) throws FindException {
        MemberDTO m = dao.selectById(id);
        if (!m.getMemPwd().equals(pwd)) {
            throw new FindException("로그인 실패!");
        }
        return m;
    }

    /**
     * 고객정보 조회
     * @param id
     * @return
     * @throws FindException
     */
    public MemberDTO detail(String id) throws FindException {
        return dao.selectById(id);
    }

    /**
     * 고객정보 수정
     * @param m
     * @throws ModifyException
     * 추후 enabled 요소 DB에 반영되면 회원 가입 탈퇴여부 확인한다.
     */
    public void modify(MemberDTO m) throws ModifyException {
        dao.update(m);
    }

    /**
     * 고객정보 조회
     * @param id
     * @return
     * @throws FindException
     */
    public MemberDTO findById(String id) throws FindException {
        return dao.selectById(id);
    }

    public boolean duplicateCheck(String id) throws FindException {
        if (dao.selectById(id) == null) {
            return true;
        } else {
            return false;
        }
    }

}
