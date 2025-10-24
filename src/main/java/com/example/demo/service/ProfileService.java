package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Profile;
import com.example.demo.repository.ProfileRepository;

@Service
public class ProfileService extends AbstractService<Profile, ProfileRepository> {

	protected ProfileService(ProfileRepository repository) {
		super(repository);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	ProfileRepository profileRepository;
	
//	@Override
//	public Profile update(int id, Profile profile) {
//		profile.preUpdate(profile);
//		return super.update(id, profile);
//	}


//	@Override
//	protected void validate(Profile entity) {
//		// TODO 自動生成されたメソッド・スタブ
//
//	}
//
//	@Override
//	protected void rewrite(Profile profile) {
//		if (profile.getUser() != null) {
//			userService.rewrite(profile.getUser());
//		}
//	}

	//	@Override
	//	public void deleteById(int id) {
	//		System.out.println("**start**");
	//		Profile profile = findById(id);
	//		if (profile.getUser() != null) {
	//			profile.getUser().setProfile(null);
	//	    }
	//		repository.deleteById(id);
	//		System.out.println("**end**");
	//	}

	//	public Profile findById(int id) {
	//		return profileRepository.findById(id)
	//				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
	//	}
	//
	//	public void deleteProfile(int id) {
	//		findById(id);
	//		profileRepository.deleteById(id);
	//	}
	//
	//	public Profile updateProfile(int id,Profile profile) {
	//		findById(id);
	//		profile.setId(id);
	//		return  profileRepository.save(profile);
	//	}

}