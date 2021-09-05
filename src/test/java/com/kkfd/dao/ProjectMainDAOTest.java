package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.FindException;
@SpringBootTest
class ProjectMainDAOTest {
	
	@Autowired
	ProjectMainDAO dao;
	
	@Test
	void mainTest() throws FindException {
		List<ProjectMainDTO> list = dao.mainProjs();
		int expectedSize = 17;
		assertEquals(expectedSize, list.size());
	}
	
	@Test
	void mainTestLogined() throws FindException {
		List<ProjectMainDTO> list = dao.mainProjs("id1");
		int expectedSize = 17;
		assertEquals(expectedSize, list.size());
	}

}
