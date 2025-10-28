package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.entity.Group;
import com.example.demo.entity.Post;
import com.example.demo.entity.Profile;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.specification.UserSpecifications;

@Service
@Transactional
public class UserService extends AbstractService<User, UserRepository> {

	public UserService(UserRepository repository) {
		super(repository);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	PostRepository postRepository;

	@Autowired
	GroupRepository groupRepository;

	public User linkProfileToUser(int userId, int profileId) {
		User user = repository.findById(userId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("User not found", LocalDate.now())));
		Profile profile = profileRepository.findById(profileId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("Profile not found", LocalDate.now())));
		profile.setUser(user);
		user.setProfile(profile);
		profileRepository.save(profile);
		User updated = repository.save(user);
		updated.rewrite();
		return updated;
	}

	public User linkPostToUser(int userId, int postId) {
		User user = repository.findById(userId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("User not found", LocalDate.now())));
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("Post not found", LocalDate.now())));
		post.setUser(user);
		user.getPosts().add(post);
		postRepository.save(post);
		User updated = repository.save(user);
		updated.rewrite();
		return updated;
	}

	public User linkGroupToUser(int userId, int groupId) {
		User user = repository.findById(userId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("User not found", LocalDate.now())));
		Group group = groupRepository.findById(groupId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("Post not found", LocalDate.now())));
		group.getUsers().add(user);
		user.getGroups().add(group);
		groupRepository.save(group);
		User updated = repository.save(user);
		updated.rewrite();
		return updated;
	}

	public List<User> findWomen(Pageable pageable) {
		Page<User> users = userRepository.findAll(UserSpecifications.isWomen(), pageable);
		List<User> usersList = users.getContent();
		usersList.forEach(User::rewrite);

		return usersList;
	}

	public List<User> findChildren(Pageable pageable) {
		Page<User> users = userRepository.findAll(UserSpecifications.isChildren(), pageable);
		List<User> usersList = users.getContent();
		usersList.forEach(User::rewrite);
		return usersList;
	}

	public List<User> findWomenAndChildren(Pageable pageable) {
		Page<User> users = userRepository.findAll(
				UserSpecifications.isWomen().or(UserSpecifications.isChildren()), pageable);
		List<User> usersList = users.getContent();
		usersList.forEach(User::rewrite);
		return usersList;
	}

}
