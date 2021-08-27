package com.kkfd.dao;

import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAOOracle implements MemberDAO {
    @Override
    public void insert(MemberDTO m) throws AddException {

    }

    @Override
    public MemberDTO selectById(String id) throws FindException {
        return null;
    }

    @Override
    public void update(MemberDTO m) throws ModifyException {

    }
}
