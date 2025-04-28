package org.zerock.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/root-context.xml"	
		}
)
@Log4j
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	public void test() {
		log.info("boardRepository >> " + boardRepository);
	}
	
	
	@Test
	public void selectAlltest() {
		
		List<BoardVO> list = boardRepository.selectAllBoards();
		for(BoardVO vo : list)
			log.info(vo);
		
		log.info("--------------------------------");
		
		boardRepository.selectAllBoards()
			.forEach(board-> log.info(board));
	}

}




















