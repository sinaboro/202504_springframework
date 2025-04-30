package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.dto.BoardVO;

public interface TimeMapper {

	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTime2();
	
	public List<BoardVO> selectAllList();
	
	public BoardVO selectOnByNum(int num);
}







