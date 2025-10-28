package com.example.demo.controller;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("user")
public class UserController extends AbstractController<User, UserRepository, UserService> {

	protected UserController(UserService service) {
		super(service);
	}

	@Autowired
	UserService userService;

	@PostMapping("{userId}/profile/{profileId}")
	public User linkProfileToUser(@PathVariable int userId, @PathVariable int profileId) {
		return userService.linkProfileToUser(userId, profileId);
	}

	@PostMapping("{userId}/post/{postId}")
	public User linkPostToUser(@PathVariable int userId, @PathVariable int postId) {
		return userService.linkPostToUser(userId, postId);
	}

	@PostMapping("{userId}/group/{groupId}")
	public User linkGroupToUser(@PathVariable int userId, @PathVariable int groupId) {
		return userService.linkGroupToUser(userId, groupId);
	}

	@GetMapping("/search/sp/women")
	public List<User> findWomen(@ParameterObject Pageable pageable) {
		return userService.findWomen(pageable);
	}

	@GetMapping("/search/sp/children")
	public List<User> findChliren(@ParameterObject Pageable pageable) {
		return userService.findChildren(pageable);
	}

	@GetMapping("/search/sp/women-children")
	public List<User> findWomenAndChliren(@ParameterObject Pageable pageable) {
		return userService.findWomenAndChildren(pageable);
	}
}
