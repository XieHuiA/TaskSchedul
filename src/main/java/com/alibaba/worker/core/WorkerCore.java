package com.alibaba.worker.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.worker.WorkerStart;

@Component
public class WorkerCore {
	private static final Logger LOG = LoggerFactory.getLogger(WorkerCore.class);
	public boolean isStop = false;

	public void stop() {
		isStop = true;
	}

	public void init() {
		System.out.println("worker start run to work");
	}

	public void start(String taskName) {
		init();
		Thread worker = new Thread(this.new WorkerRun(), taskName);
		worker.start();
		
	}

	public void reStart(String taskName) {
		stop();
		start(taskName);
	}

	class WorkerRun implements Runnable {

		@Override
		public void run() {

			int i = 0 ;
			while (!isStop) {
				LOG.info("Runing {}----- {}",Thread.currentThread().getName(),i);
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
