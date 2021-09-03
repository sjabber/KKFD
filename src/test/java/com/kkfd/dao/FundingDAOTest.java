package com.kkfd.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kkfd.dto.FundingDTO;
import com.kkfd.dto.MemberDTO;
import com.kkfd.dto.ProjectDTO;
import com.kkfd.exception.AddException;
import com.kkfd.exception.FindException;
@SpringBootTest
class FundingDAOTest {
	@Autowired
	FundingDAO dao;
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
//	@Test
//	void selectTest() throws FindException {
//		List<FundingDTO> list = dao.selectFunsByProjNo(357, "t");
//		int expectedSize = 98;
//		assertEquals(expectedSize, list.size());
//	}
	
	/*@Test
	void insertTest() throws AddException {
		FundingDTO funding = new FundingDTO();
		MemberDTO m = new MemberDTO();
		m.setMemId("id1");
		ProjectDTO p = new ProjectDTO();
		p.setProjNo(1);
		funding.setMember(m);
		funding.setProject(p);
		funding.setFunFm(39000);
		funding.setFunQuantity(1);
		funding.setFunName("김성열");
		funding.setFunAddress("주소");
		funding.setFunDetail("상세주소");
		funding.setFunBank("우리은행");
		funding.setFunAcno("1111-2222-3333");
		log.error(funding.toString());
		int result = dao.insertFunding(funding);
		int expectedErrCode = 200;
		assertEquals(expectedErrCode, result);
		//result.getErrCode();
	}*/
	
	@Test
	void insertTest() throws AddException {
		FundingDTO funding = new FundingDTO();
		MemberDTO m = new MemberDTO();
		m.setMemId("id1");
		ProjectDTO p = new ProjectDTO();
		p.setProjNo(3);
		funding.setMember(m);
		funding.setProject(p);
		funding.setFunQuantity(7);
		funding.setFunFm(350000);
		funding.setFunName("김성열");
		funding.setFunAddress("경북 의령군경의동 30번지");
		funding.setFunDetail("상세주소  30");
		funding.setFunBank("농협");
		funding.setFunAcno("1111-1111-1140");
//		SELECT f_kk_funding_insert('id1', 2, 7, 350000, '김태호', 
//		'경북 의령군경의동 30번지', '상세주소 30', '농협', '1111-1111-1140') FROM dual;
		int result = dao.insertFunding(funding);
		int expectedErrCode = 0;
		assertEquals(expectedErrCode, result);
		
	}

}
