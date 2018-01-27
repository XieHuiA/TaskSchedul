package com.alibaba.schedule.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.schedule.domin.GroupDO;
import com.alibaba.schedule.mapper.GroupMapper;

@Service
public class GroupService {

	@Autowired
	private GroupMapper groupMapper;

	public List<GroupDO> findAll() {
		return groupMapper.findAll();
	}
	
	 

}
