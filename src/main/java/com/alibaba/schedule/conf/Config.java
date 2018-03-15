package com.alibaba.schedule.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

	/**
	 * init config 
	 */
@Component
@ConfigurationProperties(prefix = "task")
@PropertySource("classpath:config.properties")
public class Config {
	
	

	@Value("${task.worker.ip}")
	public  String WORKER_IP ;

	@Value("${ERROR_CODE_INVALID_OPERATION}")
	public static  int ERROR_CODE_INVALID_OPERATION ;
	@Value("${ERROR_CODE_INTERNAL_ERROR}")
	public static  int ERROR_CODE_INTERNAL_ERROR ;
	
	
	@Value("${machine.timeout.time}")
	public  int MACHINE_TIMEOUT_TIME;
	
	
	
	//public static  int STOPPED = 0;
	
	//public static  int RUNNING =1;
	
	//public static  int RESTARTING =2;
	
	
}
