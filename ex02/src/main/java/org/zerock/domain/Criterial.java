package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criterial {

	private int pageNum;  //페이지
	private int amount;   //페이지 개수
	
	public Criterial() {
		this(1, 10);
	}

	public Criterial(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}		
}
