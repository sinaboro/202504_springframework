package org.zerock.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criterial;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public BoardVO read(Long bno);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getListWithPaging(Criterial cri);
	
	public int getTotalCount(Criterial cri);
	
	public List<BoardVO> searchTest(Map<String , Map<String,String>> map);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
