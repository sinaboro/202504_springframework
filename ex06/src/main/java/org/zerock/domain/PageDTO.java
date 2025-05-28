package org.zerock.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Getter
@ToString
@Log4j
public class PageDTO {

	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	//전체 레코드 개수
	private int total;  
	
	//페이지정보, 페이지당 레코드 개수
	private Criterial cri;  
	
	public PageDTO(Criterial cri, int total) {
		this.cri = cri;  //pageNum=15&amount=10, pageNum=22&amount=10, pageNum=1&amount=10
		this.total = total;  // 393215
	
		//endPage
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)) * 10; //20, 30 , 10
		this.startPage = this.endPage - 9;                 //20-9 =11 , 30-9 = 21 , 1
		
		//전체 페이지 목록에서 마지막 페이지
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));  //28,39322
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;  //28
		}
		
		this.prev = this.startPage>1;  //true , true
		this.next = this.endPage < realEnd;   //true ,false
	}
}







