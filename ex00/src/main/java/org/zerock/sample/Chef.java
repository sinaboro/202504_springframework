package org.zerock.sample;

import org.springframework.stereotype.Component;



@Component
public class Chef {
	
	private int number;
	
	public void eat() {
		System.out.println("점심 뭐 먹었지? (붕어) ");
	}
}
