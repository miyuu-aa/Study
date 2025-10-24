package com.example.demo.entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Profile extends AbstractStudyEntity<Profile> {

	private String biography;

	@OneToOne
	@JoinColumn(name = "user_id")
	@Schema(accessMode = AccessMode.READ_ONLY)
	User user;

	@Override
	public void validate() {
	}

//	@PreRemove
	@Override
	public void preRemove() {
		if (user != null) {
			user.setProfile(null);
		}
	}

	@Override
	public void preUpdate(Profile request) {
		//	    profile.setUser(request.getUser());
		//	    profile.getUser().setProfile(request);	
		this.biography = request.getBiography();
	}

	@Override
	public void rewrite() {
		user.setFullName(user.getFirstName() + " " + user.getLastName());
		user.setGender(Gender.codeToName(user.getGenderCode()));
		user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
	}

}
