package com.kkfd.dao;

import com.kkfd.dto.MemberDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.ModifyException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAOOracle implements MemberDAO {

    //spring container에서 관리되는 sqlSessionFactory객체가 하나여서 바로 session 객체가 생성된다.
    @Autowired
    private SqlSessionFactory sessionFactory;

    public MemberDAOOracle() throws Exception {
        // JDBC 드라이버 로드
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(MemberDTO m) throws AddException {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            int rowCnt = session.insert("com.kkfd.dto.MemberMapper.insert", m);
            if(rowCnt==0) {
            	throw new AddException("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AddException(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MemberDTO selectById(String id) throws FindException {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            MemberDTO memberDTO = session.selectOne("com.kkfd.dto.MemberMapper.selectById", id);
            return memberDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new FindException(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void update(MemberDTO m) throws ModifyException {
        SqlSession session = null;
        try {
            session = sessionFactory.openSession();
            int rowCnt = session.update("com.kkfd.dto.MemberMapper.update", m);
            if(rowCnt==0) {
                throw new ModifyException("0");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
