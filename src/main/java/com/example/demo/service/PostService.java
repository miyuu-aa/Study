package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@Service
public class PostService extends AbstractService<Post, PostRepository> {

	protected PostService(PostRepository repository) {
		super(repository);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	PostRepository postRepository;
	
//	@Override
//	public void deleteById(int id) {
//		Post post = repository.findById(id)
//				.orElseThrow(() -> new BusinessException(new ErrorResponse("Data Not Found.", LocalDate.now())));
//		User user = post.getUser();
//		if (user == null) {
//			super.deleteById(id);
//		} else {
//			user.getPosts().remove(post);
//		}
//	}

//	@Override
//	public Post update(int id, Post post) {
//		post.preUpdate(post);
//		return super.update(id, post);
//	}

//	@Override
//	protected void validate(Post entity) {
//		// TODO 自動生成されたメソッド・スタブ
//
//	}
//
//	@Override
//	protected void rewrite(Post post) {
//		if (post.getUser() != null) {
//			userService.rewrite(post.getUser());
//		}
//	}

}
