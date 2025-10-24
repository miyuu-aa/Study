package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;

@RestController
@RequestMapping("post")
public class PostController extends AbstractController<Post, PostRepository, PostService> {

	@Autowired
	PostService postService;

	protected PostController(PostService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
