package com.alibaba.master; 

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.master.core.TaskSchedul;
import com.alibaba.schedule.conf.Config;


@EnableAutoConfiguration
@MapperScan(basePackages={"com.alibaba.schedule.mapper"})
@ComponentScan(value={"com.alibaba.schedule","com.alibaba.master.*"})
public class MasterStart implements CommandLineRunner{
     
	private static final Logger LOG = LoggerFactory.getLogger(MasterStart.class);
	@Autowired
	public TaskSchedul taskSchedul;
	@Override
	public void run(String... paramArrayOfString) throws Exception {
		taskSchedul.init(); 
		taskSchedul.scedule();
	}  
	  
}
