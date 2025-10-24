package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PostRepository;
@Service
public class GroupService extends AbstractService<Group, GroupRepository>{

		protected GroupService(GroupRepository repository) {
			super(repository);
			// TODO 自動生成されたコンストラクター・スタブ
		}

		@Autowired
		PostRepository postRepository;
}
