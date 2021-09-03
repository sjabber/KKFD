
package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.ProjectDTO;
import com.kkfd.dto.ProjectMainDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
import com.kkfd.exception.RemoveException;
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
	
	@Test
	void test2() throws FindException {
		List<ProjectDTO> list = dao.selectPrevProj(1);
		int expectedsize = 2;
		assertEquals(expectedsize, list.size());
	}
	
	@Test
	void insertBookmarkTest() throws AddException {
		String id = "id1";
		int projNo = 3;
		dao.insertBookmark(projNo, id);
	}
	
	@Test
	void deleteBookmarkTest() throws RemoveException {
		String id = "id1";
		int projNo = 3;
		dao.deleteBookmark(projNo, id);
	}

}

