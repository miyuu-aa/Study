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
public class UserController extends AbstractController<User,UserRepository,UserService>{

	protected UserController(UserService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
//	UserRepository userRepository;
	UserService userService;

	//	private static final Map<Integer, String> genderMap = new HashMap<>();
	//
	//	static {
	//		genderMap.put(1, "男");
	//		genderMap.put(2, "女");
	//		genderMap.put(3, "その他");
	//	}

	//	private static final List<Gender> genders = List.of(
	//			new Gender(1, "男", "Male"),
	//			new Gender(2, "女", "Female"),
	//			new Gender(3, "その他", "Other"));

	//	public final List<User> users = new ArrayList<>();

	//	public UserController() {
	//		users.add(new User(1, "アリス", "女", 17));
	//		users.add(new User(2, "ボブ", "男", 23));
	//	}

//	@GetMapping
//	public List<User> findAll() {
////		//		return users;
////		//		return userRipository.findAll();
////		List<User> all = userRepository.findAll();
////		all.forEach(user -> rewrite(user));
////		return all;
//		return userService.findAll();
//	}

//	@GetMapping("{id}")
//	public User findById(@PathVariable int id) {
////		//		//		for (User u : users) {
////		//		//			if (id == u.getId()) {
////		//		//				return u;
////		//		//			}
////		//		//		}
////		//		//		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
////		//		return users.stream()
////		//				.filter(u -> u.getId() == id)
////		//				.findFirst()
////		//				.orElseThrow(() -> new BusinessException("User not found"));
////
////		//		return userRipository.findById(id)
////		//				//            .orElseThrow(() -> new BusinessException(response));
////		//				.orElseThrow(
////		//						() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
////		User find = userRepository.findById(id)
////				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
////		rewrite(find);
////		return find;
//		return userService.findById(id);
//	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public User addUser(@RequestBody User user) {
////		//		//		int maxId = 0;
////		//		//		for (User u : users) {
////		//		//			if (u.getId() > maxId) {
////		//		//				maxId = u.getId();
////		//		//			}
////		//		//		}
////		//		//		int nextId = maxId + 1;
////		//		int nextId = users.stream()
////		//				.mapToInt(User::getId)
////		//				.max()
////		//				.orElse(0) + 1;
////		//		user.setId(nextId);s
////		//		users.add(user);
////		//		return user;
////		validate(user);
////		//		return userRipository.save(user);
////		User add = userRepository.save(user);
////		rewrite(add);
////		return add;
//		return userService.add(user);
//	}
//
//	@DeleteMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteUser(@PathVariable int id) {
////		//		User target = null;
////		//		//		for (User u : users) {
////		//		//			if (u.getId() == id) {
////		//		//				target = u;
////		//		//			}
////		//		//		}
////		//		//		if (target == null) {
////		//		//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
////		//		//		}
////		//		users.remove(target);
////		//		if (!users.removeIf(u -> u.getId() == id)) {
////		//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
////		findById(id);
////		userRepository.deleteById(id);
//		userService.deleteById(id);
//	}

//	@PutMapping("{id}")
//	public User updateUser(@PathVariable int id, @Valid @RequestBody User user) {
////		//		User existingUser = users.stream()
////		//				.filter(u -> u.getId() == id)
////		//				.findFirst()
////		//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
////		//		existingUser.setName(user.getName());
////		//		existingUser.setGender(user.getGender());
////		//		existingUser.setAge(user.getAge());
////
////		//		int index = IntStream.range(0, users.size())
////		//				.filter(i -> users.get(i).getId() == id)
////		//				.findFirst()
////		//				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
////		findById(id);
////		validate(user);
////		user.setId(id);
////		//		users.set(index, user);
////		//		return user;
////		//		return userRipository.save(user);
////		User update = userRepository.save(user);
////		rewrite(update);
////		return update;
//		return userService.update(id, user);
//	}

//	public void validate(User user) {
//		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
//			throw new BusinessException(new ErrorResponse("FirstName must not be null or blank.", LocalDate.now()));
//		}
//
//		if (user.getLastName() == null || user.getLastName().isEmpty()) {
//			throw new BusinessException(new ErrorResponse("LastName must not be null or blank.", LocalDate.now()));
//		}
//
//		//		if (user.getGenderCode() != 1 && user.getGenderCode() != 2) {
//		//			throw new BusinessException(new ErrorResponse("Gender code must be 1 or 2", LocalDate.now()));
//		//		}
//		//		if (!genderMap.containsKey(user.getGenderCode())) {
//		//			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
//		//		}
//		//		if (gender.stream().noneMatch(g -> g.getCode() == user.getGenderCode())) {
//		//			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
//		//		}
//		//		Gender gender = Gender.fromCode(user.getGenderCode());
//		//		if (gender == null) {
//		//			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
//		//		}
//		if (!Gender.isValid(user.getGenderCode())) {
//			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
//		}
//
//		if (user.getBirthday().isAfter(LocalDate.now())) {
//			throw new BusinessException(new ErrorResponse("Age must be 0 or more.", LocalDate.now()));
//		}
//	}

//	public void rewrite(User user) {
//		if (user != null) {
//			user.setFullName(user.getFirstName() + " " + user.getLastName());
//
//			//			user.setGender(switch (user.getGenderCode()) {
//			//			case 1 -> "男";
//			//			case 2 -> "女";
//			//			default -> "不明";
//			//			});
//			//			user.setGender(genderMap.get(user.getGenderCode()) != null ? genderMap.get(user.getGenderCode()) : "不明");
//
//			//			Gender gender = gender.stream()
//			//					.filter(g -> g.getCode() == user.getGenderCode())
//			//					.findFirst()
//			//					.orElse(new Gender(0, "不明", "Unknown"));
//			//			user.setGender(gender.getJapaneseName());
//			//			Gender gender = Gender.fromCode(user.getGenderCode());
//			//			if (gender != null) {
//			//				user.setGender(gender.getJapaneseName());
//			//			} else {
//			//				user.setGender("不明");
//			//			}
//			//			Gender gender = Gender.fromCode(user.getGenderCode());
//			//			user.setGender(gender.getJapaneseName());
//			user.setGender(Gender.codeToName(user.getGenderCode()));
//
//			user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
//		}
//	}

//	@GetMapping("search-ella")
//	public List<User> searchElla() {
//		List<User> users = userRepository.findByFirstName("Ella");
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("search/{firstName}")
//	public List<User> searchUser(@PathVariable String firstName) {
//		List<User> users = userRepository.findByFirstName(firstName);
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("search/{lastName}/{firstName}")
//	public List<User> searchByFullName(@RequestParam String lastName, @RequestParam String firstName) {
//		List<User> users = userRepository.findDistinctByLastNameAndFirstName(lastName, firstName);
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("search/age/{age}")
//	public List<User> searchByAge(@PathVariable int age) {
//		List<User> users = userRepository.findByBirthdayAfter(LocalDate.now().minusYears(age));
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/gender/{genderCode}")
//	public List<User> searchByGender(@PathVariable int genderCode) {
//		List<User> users = userRepository.findByGenderCodeEquals(genderCode);
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/age-name-desc/{age}")
//	public List<User> searchByAgeNameDesc(@PathVariable int age) {
//		List<User> users = userRepository.findByBirthdayAfterOrderByLastNameDesc(LocalDate.now().minusYears(age));
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/name/{s}")
//	public List<User> findByFirstNameStart(@PathVariable String s) {
//		List<User> users = userRepository.findByFirstNameStartingWith(s);
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/people/women")
//	public List<User> findWomen() {
//		List<User> users = userRepository.findByGenderCodeEquals(2);
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/people/children")
//	public List<User> findChliren() {
//		List<User> users = userRepository.findByBirthdayAfter(LocalDate.now().minusYears(18));
//		users.forEach(user -> rewrite(user));
//		return users;
//	}
//
//	@GetMapping("/search/people/women-children")
//	public List<User> findWomenAndChliren() {
//		List<User> users = userRepository.findByGenderCodeEqualsOrBirthdayAfter(2, LocalDate.now().minusYears(18));
//		users.forEach(user -> rewrite(user));
//		return users;
//	}

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
//		//			public List<User> FindWomen(
//		//					@RequestParam int page,
//		//					@RequestParam int size,
//		//					@RequestParam(name = "sort") List<String> sortparams) {
//		//		//				Sort sort = Sort.by(Direction.DESC, "birthday").and(Sort.by(Direction.ASC, "firstName"));
//		//		//				Sort sort = Sort.unsorted();
//		//		//				if (sortparams != null && !sortparams.isEmpty()) {
//		//		//					List<Sort.Order> orders = new ArrayList<>();
//		//		//					for (String param : sortparams) {
//		//		//						String[] parts = param.split(",");
//		//		//						String property = parts[0];
//		//		//						Sort.Direction direction = switch (parts[1]) {
//		//		//						case "desc" -> Sort.Direction.DESC;
//		//		//						case "asc" -> Sort.Direction.ASC;
//		//		//						default -> throw new IllegalArgumentException("Unexpected value: " + parts[1]);
//		//		//						};
//		//		//						orders.add(new Sort.Order(direction, property));
//		//		//					}
//		//		//					sort = Sort.by(orders);
//		//		//				}
//		//				Sort sort = buildsort(sortparams);
//		//				Pageable pageable = PageRequest.of(page, size, sort);
//		Page<User> users = userRepository.findAll(UserSpecifications.isWomen(), pageable);
//		List<User> usersList = users.getContent();
//		usersList.forEach(user -> rewrite(user));
//		return usersList;
		return userService.findWomen(pageable);
	}

	@GetMapping("/search/sp/children")
	public List<User> findChliren(@ParameterObject Pageable pageable){
//			@RequestParam int page,
//			@RequestParam int size,
//			@RequestParam(required = false, name = "sort") List<String> sortparams) {
//		//		Sort sort = Sort.by(Direction.DESC, "birthday").and(Sort.by(Direction.ASC, "firstName"));
//		//		Sort sort = Sort.unsorted();
//		//		if (sortparams != null && !sortparams.isEmpty()) {
//		//			List<Sort.Order> orders = new ArrayList<>();
//		//			for (String param : sortparams) {
//		//				String[] parts = param.split(",");
//		//				String property = parts[0];
//		//				Sort.Direction direction = switch (parts[1]) {
//		//				case "desc" -> Sort.Direction.DESC;
//		//				case "asc" -> Sort.Direction.ASC;
//		//				default -> throw new IllegalArgumentException("Unexpected value: " + parts[1]);
//		//				};
//		//				orders.add(new Sort.Order(direction, property));
//		//			}
//		//			sort = Sort.by(orders);
//		//		}
//		Sort sort = buildsort(sortparams);
//		Pageable pageable = PageRequest.of(page, size, sort);
//		Page<User> users = userRepository.findAll(UserSpecifications.isChildren(), pageable);
//		List<User> usersList = users.getContent();
//		usersList.forEach(user -> rewrite(user));
//		return usersList;
		return userService.findChildren(pageable);
	}

	@GetMapping("/search/sp/women-children")
	public List<User> findWomenAndChliren(@ParameterObject Pageable pageable){
//			@RequestParam int page,
//			@RequestParam int size,
//			@RequestParam(required = false, name = "sort") List<String> sortparams) {
//		sortparams.forEach(System.out::println);
//		//		Sort sort = Sort.by(Direction.DESC, "birthday").and(Sort.by(Direction.ASC, "firstName"));
//		//		Sort sort = Sort.unsorted();
//		//		if (sortparams != null && !sortparams.isEmpty()) {
//		//			List<Sort.Order> orders = new ArrayList<>();
//		//			for (String param : sortparams) {
//		//				String[] parts = param.split(",");
//		//				String property = parts[0];
//		//				Sort.Direction direction = switch (parts[1]) {
//		//				case "desc" -> Sort.Direction.DESC;
//		//				case "asc" -> Sort.Direction.ASC;
//		//				default -> throw new IllegalArgumentException("Unexpected value: " + parts[1]);
//		//				};
//		//				orders.add(new Sort.Order(direction, property));
//		//			}
//		//			sort = Sort.by(orders);
//		//		}
//		Sort sort = buildsort(sortparams);
//		Pageable pageable = PageRequest.of(page, size, sort);
//		Page<User> users = userRepository.findAll(UserSpecifications.isWomen().or(UserSpecifications.isChildren()),
//				pageable);
//		List<User> usersList = users.getContent();
//		usersList.forEach(user -> rewrite(user));
//		return usersList;
		return userService.findWomenAndChildren(pageable);
	}

//	public Sort buildsort(List<String> sortparams) {
//		List<Sort.Order> orders = new ArrayList<>();
//		String property = "";
//		Sort.Direction direction = Sort.Direction.ASC;
//		if (!sortparams.get(0).contains(",")) {
//			property = sortparams.get(0);
//			direction = sortparams.get(1).equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//			orders.add(new Sort.Order(direction, property));
//		} else {
//			for (int i = 0; i < sortparams.size(); i++) {
//				String param = sortparams.get(i);
//				String[] parts = param.split(",");
//				property = parts[0];
//				direction = parts[1].equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//				orders.add(new Sort.Order(direction, property));
//			}
//		}
//		return Sort.by(orders);
//	}
}
