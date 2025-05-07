package org.zerock.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"
		})
@Log4j
public class BoardServiceImplTests {

	@Autowired
	private BoardService service;
	
	@Test
	public void testRegister() {
		BoardVO vo = BoardVO.builder()
				.title("서비스 제목")
				.content("서비스 내용")
				.writer("service00")
				.build();
		
		service.register(vo);
	}
	
	@Test
	public void testGetList() {
		service.getList(new Criterial()).forEach(board -> log.info(board));
	}

}
