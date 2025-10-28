package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;

@Service
public class GroupService extends AbstractService<Group, GroupRepository> {

	protected GroupService(GroupRepository repository) {
		super(repository);
	}
}
