package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.service.GroupService;

@RestController
@RequestMapping("group")
public class GroupController extends AbstractController<Group, GroupRepository, GroupService> {

	protected GroupController(GroupService service) {
		super(service);
	}

}
