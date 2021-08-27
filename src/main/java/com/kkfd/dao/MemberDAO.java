package com.kkfd.dao;

import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;

public interface MemberDAO {
    /**
     * 회원가입
     * @param m
     * @throws AddException
     */
    void insert(MemberDTO m) throws AddException;

    /**
     * 로그인
     * @param id
     * @return
     * @throws FindException
     */
    MemberDTO selectById(String id) throws FindException;

    /**
     * 고객정보 수정
     * @param m
     * @throws ModifyException
     */
    void update(MemberDTO m) throws ModifyException;
}
