package com.example.demo.entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
public class Post extends AbstractStudyEntity<Post> {

	private String content;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@Schema(accessMode = AccessMode.READ_ONLY)
	private User user;

	@Override
	public void validate() {
	}
	
	@Override
	public void rewrite() {
		user.setFullName(user.getFirstName() + " " + user.getLastName());
		user.setGender(Gender.codeToName(user.getGenderCode()));
		user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
	}

//	@PreRemove
	@Override
	public void preRemove() {
		if (user != null) {
			user.getPosts().remove(this);
		}
	}

	@Override
	public void preUpdate(Post request) {
		this.content = request.getContent();
	}
}
