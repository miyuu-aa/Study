package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.service.ProfileService;

@RestController
@RequestMapping("profile")
public class ProfileController extends AbstractController<Profile, ProfileRepository, ProfileService> {

	@Autowired
	ProfileService profileService;

	protected ProfileController(ProfileService service) {
		super(service);
	}
}
