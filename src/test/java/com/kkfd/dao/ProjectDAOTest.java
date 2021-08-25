package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.exception.FindException;
@SpringBootTest
class ProjectDAOTest {
	@Autowired
	ProjectDAO dao;
	@Test
	void test() throws FindException {
		ProjectDTO proj = dao.selectByNo(4, null);
//		String expectedProjId = "classyart";
//		assertEquals(expectedProjId, proj.getCreator().getCrId());
		int expectedProjCategory = 1;
		assertEquals(expectedProjCategory, proj.getProjCategory());
	}

}
