package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criterial;
import org.zerock.domain.ReplyVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	@Autowired
	private ReplyMapper mapper;

	
	private Long[] bnoArr = {
			1769533L, 1769532L, 1769531L,
			1769530L, 1769529L
	};
	
	
	@Test
	public void testCreate() {
		IntStream.range(1, 10)
		.forEach(i-> {
			ReplyVO vo = ReplyVO.builder()
					.bno(bnoArr[i%5])
					.reply("댓글 테스트"+i)
					.replyer("replyer"+i)
					.build();
			
			mapper.insert(vo);
		});
	}
	
	
	@Test 
	public void testRead() {
		log.info(mapper.read(1L));
	}

	@Test 
	public void testDelete() {
		log.info(mapper.delete(9L));
	}

	@Test 
	public void testUpdate() {
		
		ReplyVO vo = ReplyVO.builder()
				.reply("댓글 수정내용")
				.rno(8L)
				.build();
		
		log.info(
				mapper.update(vo)
		);
	}
	
	
	@Test
	public void testGetList() {
		Criterial cri = new Criterial();		
		Long bno  = 1769532L;
		
		mapper.getListWithPaging(cri, bno)
			.forEach(reply -> log.info(reply));
	}
}






