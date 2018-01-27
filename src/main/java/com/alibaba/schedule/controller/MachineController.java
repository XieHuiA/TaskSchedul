package com.alibaba.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.schedule.domin.MachineDO;
import com.alibaba.schedule.service.JobService;
import com.alibaba.schedule.service.MachineService;


@RestController
@RequestMapping("/machine")
public class MachineController {

	@Autowired
	private MachineService machineService;
	
		
		@RequestMapping("/updateByIp")
		public boolean updateByIp(String ip){
			return machineService.updateByIp(ip);
		}
		
		
		/**
		 * 查出超时的ip
		 */
		@RequestMapping("/queryByTimeoutValue")
		public List<MachineDO> queryByTimeoutValue(int machineTimeoutSec) {
			return machineService.queryByTimeoutValue(machineTimeoutSec);
		}
		
}