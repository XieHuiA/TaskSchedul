package com.alibaba.schedule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.schedule.domin.GroupDO;
import com.alibaba.schedule.domin.TaskDo;
import com.alibaba.schedule.service.GroupService;

@RequestMapping("/group")
@RestController
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	/**
	 * 查出所有Task
	 * @return
	 */
	 @RequestMapping(value = "/findall")
	 public List<GroupDO> findAll() {
		 return groupService.findAll();
	 }	

}
