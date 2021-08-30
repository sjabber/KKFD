/*
package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.SearchDTO;
import com.kkfd.exception.FindException;

@SpringBootTest
class ProjectDAOTest2 {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProjectDAO dao;
	
	Date now = new Date();
	@Test
	void myprojectTest() throws FindException {
		HashMap<String, Object>map = new HashMap<>();
		List<ProjectDTO> list = dao.selectProjsByCrId("id4",1);
		int expectedSize= 5;
		assertEquals(expectedSize, list.size());
	}
	
	@Test
	void totalTest() throws FindException {
		int total = dao.countMyProjs("id1");
		int expected= 7;
		assertEquals(7, total);
	}

	
}
*/
