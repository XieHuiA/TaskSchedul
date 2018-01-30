package com.alibaba.schedule.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.schedule.domin.MachineDO;
import com.alibaba.schedule.mapper.MachineMapper;

@Service
public class MachineService {
	
	@Autowired
	MachineMapper machineMapper;
	
	
	//查询所有machine
	@Transactional
	public List<MachineDO> selectAll() {
		
		return machineMapper.selectAll();
	}
	
	/**
	 * 根据id查出machine
	 * @return
	 */
	@Transactional
	public MachineDO findbyId(String id) {
		return machineMapper.findbyId(id);
	}

	/**
	 * 根据id删除machine
	 * @return
	 */
	@Transactional
	public int deleteById(String id) {
		
		return machineMapper.deleteById(id);
	}

	/**
	 * 添加machine
	 * @return
	 */
	@Transactional
	public int add(MachineDO machineDO) {
		
		return machineMapper.add(machineDO);
	}

	/**
	 * 修改machine
	 * @param id
	 * @return
	 */
	@Transactional
	public boolean updateByIp(String ip) {
		return machineMapper.updateByIp(ip);
	}

	/**
	 * 根据ip查出machine
	 * @return
	 */
	@Transactional
	public MachineDO findbyip(String ip) {
		return machineMapper.findbyip(ip);
	}

	/**
	 * 查出超时的ip
	 */
	@Transactional
	public List<MachineDO> queryByTimeoutValue(int machineTimeoutSec) {
		return machineMapper.queryByTimeoutValue(machineTimeoutSec);
	}

	 
}
