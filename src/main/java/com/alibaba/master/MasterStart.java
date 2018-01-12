package com.alibaba.master; 

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@EnableAutoConfiguration
@MapperScan(basePackages={"com.alibaba.schedule.mapper"})
@ComponentScan(value={"com.alibaba.schedule","com.alibaba.master.*"})
public class MasterStart implements CommandLineRunner{
     
	@Override
	public void run(String... paramArrayOfString) throws Exception {
		// todo
		System.out.println("master Start fine .........");
		
	}  
	  
}
