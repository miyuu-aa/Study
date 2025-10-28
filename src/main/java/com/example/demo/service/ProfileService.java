package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;

@Service
public class ProfileService extends AbstractService<Profile, ProfileRepository> {

	protected ProfileService(ProfileRepository repository) {
		super(repository);
	}
}