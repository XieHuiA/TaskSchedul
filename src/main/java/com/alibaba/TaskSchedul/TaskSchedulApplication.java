package com.alibaba.TaskSchedul;


import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.master.MasterStart;
import com.alibaba.worker.WorkerStart;

@SpringBootApplication
public class TaskSchedulApplication {
	public static Logger logger = Logger.getLogger(TaskSchedulApplication.class);


	public static void main(String... args) {
		if (args.length<1) {
			System.out.println("START ERROR : please writer [master/worker] [start]");
		}
		if (args[0].equals("worker") && args[1].equals("start")) {
			SpringApplication.run(WorkerStart.class, args);
			logger.info("start worker ok !");
		} else if (args[0].equals("master") && args[1].equals("start")) {
			SpringApplication.run(MasterStart.class, args);
			logger.info("start master ok !");
		} else {
			System.out.println("start faild ! please confirm your parameter ");
			System.exit(1);
		}

	}
}
