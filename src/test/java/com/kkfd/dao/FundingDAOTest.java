/*
package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.FundingDTO;
import com.kkfd.exception.FindException;
@SpringBootTest
class FundingDAOTest {
	@Autowired
	FundingDAO dao;
	@Test
	void selectTest() throws FindException {
		List<FundingDTO> list = dao.selectFunsByProjNo(357, "t");
		int expectedSize = 98;
		assertEquals(expectedSize, list.size());
	}

}
*/
