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
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	GroupRepository groupRepository;

	//	@Autowired
	//	PlatformTransactionManager transactionManager;

	//	public List<User> findAll() {
	//		List<User> all = super.findAll();
	//		all.forEach(user -> rewrite(user));
	//		return all;
	//	}
	//
	//	public User findById(int id) {
	//		User find = userRepository.findById(id)
	//				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
	//		rewrite(find);
	//		return find;
	//	}
	//
	//	public User addUser(User user) {
	//		//		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	//		//		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
	//		//		try {
	//		validate(user);
	//		User add = userRepository.save(user);
	//		//			if (user.getId() % 2 == 0) {
	//		//				throw new BusinessException(new ErrorResponse("Id is even number.", LocalDate.now()));
	//		//			}
	//		rewrite(add);
	//		//			transactionManager.commit(transactionStatus);
	//		return add;
	//		//		} catch (Exception e) {
	//		//			// TODO 自動生成された catch ブロック
	//		//			e.printStackTrace();
	//		//			transactionManager.rollback(transactionStatus);
	//		//			throw e;
	//		//		}
	//	}
	//
	//	public void deleteUser(int id) {
	//		//		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	//		//		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
	//		//		try {
	//		findById(id);
	//
	//		userRepository.deleteById(id);
	//		//			if (id % 2 == 0) {
	//		//				throw new BusinessException(new ErrorResponse("Id is even number.", LocalDate.now()));
	//		//			}
	//		//			transactionManager.commit(transactionStatus);
	//		//		} catch (Exception e) {
	//		//			// TODO 自動生成された catch ブロック
	//		//			e.printStackTrace();
	//		//			transactionManager.rollback(transactionStatus);
	//		//			throw e;
	//		//		}
	//	}
	//
	//	public User updateUser(int id, User user) {
	//		//		DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
	//		//		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
	//		//		try {
	//		findById(id);
	//		validate(user);
	//		user.setId(id); ErrorResponse
	//		User update = userRepository.save(user);
	//		//			if (user.getId()% 2 == 0) {
	//		//				throw new BusinessException(new ErrorResponse("Id is even number.", LocalDate.now()));
	//		//			}
	//		rewrite(update);
	//		//			transactionManager.commit(transactionStatus);
	//		return update;
	//		//		} catch (Exception e) {
	//		//			// TODO 自動生成された catch ブロック
	//		//			e.printStackTrace();
	//		//			transactionManager.rollback(transactionStatus);
	//		//			throw e;
	//		//		}
	//	}
//	@Override
//	public User update(int id, User user) {
//		user.preUpdate(user);
//		return super.update(id, user);
//	}

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
		Group group= groupRepository.findById(groupId)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("Post not found", LocalDate.now())));
		group.getUsers().add(user);
		user.getGroups().add(group);
		groupRepository.save(group);
		User updated = repository.save(user);
		updated.rewrite();
		return updated;
	}

//	@Override
//	public void validate(User user) {
//		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
//			throw new BusinessException(new ErrorResponse("FirstName must not be null or blank.", LocalDate.now()));
//		}
//		if (user.getLastName() == null || user.getLastName().isEmpty()) {
//			throw new BusinessException(new ErrorResponse("LastName must not be null or blank.", LocalDate.now()));
//		}
//		if (!Gender.isValid(user.getGenderCode())) {
//			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
//		}
//		if (user.getBirthday().isAfter(LocalDate.now())) {
//			throw new BusinessException(new ErrorResponse("Age must be 0 or more.", LocalDate.now()));
//		}
//	}

//	@Override
//	public void rewrite(User user) {
//		if (user != null) {
//			user.setFullName(user.getFirstName() + " " + user.getLastName());
//			user.setGender(Gender.codeToName(user.getGenderCode()));
//			user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
//		}
//	}

	//	@Override
	//	public void deleteById(int id) {
	//		User user = findById(id);
	//		if (user.getProfile() != null) {
	//	        user.getProfile().setUser(null);
	//	    }
	//		repository.deleteById(id);
	//	}

	public List<User> findWomen(Pageable pageable) {
		Page<User> users = userRepository.findAll(UserSpecifications.isWomen(), pageable);
		List<User> usersList = users.getContent();
		usersList.forEach(User::rewrite);
//		System.out.println("@");
//		usersList.get(0).getPosts().size();
//		System.out.println("@@");
		
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
