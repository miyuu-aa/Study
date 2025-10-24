package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileController extends AbstractController<Profile,ProfileRepository,ProfileService>{

	@Autowired
	ProfileService profileService;
	
	protected ProfileController(ProfileService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
//	@Autowired
//	ProfileService profileService;
		
		
//	@GetMapping
//	public List<Profile>findAll(){
//		return profileService.findAll();
//	}
//	
//	@GetMapping("{id}")
//	public Profile findById(@PathVariable int id) {
//		return profileService.findById(id);
//	}
//
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Profile addProfile(@RequestBody Profile profile) {
//		return profileService.add(profile);
//	}
//	
//	@DeleteMapping("{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void deleteProfile(@PathVariable int id) {
//		profileService.deleteById(id);
//	}
//	
//	@PutMapping("{id}")
//	public Profile updateProfile(@PathVariable int id, @RequestBody Profile profile) {
//		return profileService.update(id,profile);
//	}
}
