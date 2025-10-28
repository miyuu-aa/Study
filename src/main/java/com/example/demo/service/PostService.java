package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostService extends AbstractService<Post, PostRepository> {

	protected PostService(PostRepository repository) {
		super(repository);
	}
}
