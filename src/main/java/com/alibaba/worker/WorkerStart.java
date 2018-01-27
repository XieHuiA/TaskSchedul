package com.alibaba.worker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.schedule.conf.Config;
import com.alibaba.worker.core.Worker;

@EnableAutoConfiguration
@MapperScan(basePackages = { "com.alibaba.schedule.mapper" })
@ComponentScan(value = { "com.alibaba.schedule.*", "com.alibaba.worker.*" })
public class WorkerStart implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(WorkerStart.class);
	@Autowired
	public Worker worker;
	@Autowired
	Config config;
	private ScheduledExecutorService updateLastHeartBeat;
	private ScheduledExecutorService scheduleService;

	public void init() {
		this.updateLastHeartBeat = Executors.newScheduledThreadPool(1);
		this.scheduleService = Executors.newScheduledThreadPool(1);
	}

	@Override
	public void run(String... paramArrayOfString) throws Exception {
		// caoqiankun
		init();
		scheduleService.scheduleWithFixedDelay(() -> {
			worker.schedule();
			if (worker.updateLastHeartBeat(config.WORKER_IP)) {
				LOG.info("{} update lastHeartBeat success !",config.WORKER_IP);
			} else {
				LOG.info("{} update lastHeartBeat failed !",config.WORKER_IP);
			}
		}, 0, 30, TimeUnit.SECONDS);
		
	}

}
