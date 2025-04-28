package org.zerock.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

//@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
public class TestChoice {

	private final List<String> names = new ArrayList<>();

	public void  RandomNameTask2() {
		 names.add("노유경");
		 names.add("차누리");
		 names.add("이연수");
	}
	 
	
	
	
    public void  RandomNameTask() {
        names.add("노유경");
        names.add("오세희");
        names.add("민병흠");
        names.add("이용호");
        names.add("김나영");
        names.add("차누리");
        names.add("방희경");
        names.add("김정명");
        names.add("이연수");
        names.add("이지혜");
        names.add("최은지");
        names.add("이은지");
        names.add("유성열");
        names.add("김용석");
        names.add("인예지");
        names.add("박지안");
    }
    
    @Test
    public void pickRandomNames() {
    	RandomNameTask2();
        Collections.shuffle(names);
        log.info("랜덤으로 뽑힌 3명:");
        for (int i = 0; i < 3; i++) {
            log.info(names.get(i));
        }
        log.info("--------------------------------------");
    }
}
