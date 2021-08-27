package com.kkfd.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkfd.exception.ModifyException;

@Repository("creatorDAO")
public class CreatorDAOOracle implements CreatorDAO{
	
	@Autowired
	private SqlSessionFactory sessionFactory;
	


}
