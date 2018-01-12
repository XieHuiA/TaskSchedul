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
	
	@Value("${task.schedule.time}")
	public static int TASK_SCHEDULE_TIME ;

	
}
