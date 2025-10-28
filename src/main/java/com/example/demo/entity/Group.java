package com.example.demo.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

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
@Table(name = "groups")
public class Group extends AbstractStudyEntity<Group> {

	private String name;

	@ManyToMany(mappedBy = "groups")
	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<User> users = new ArrayList<>();

	@Override
	public void validate() {
	}

	@Override
	public void rewrite() {
		for (User user : users) {
			user.setFullName(user.getFirstName() + " " + user.getLastName());
			user.setGender(Gender.codeToName(user.getGenderCode()));
			user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());
		}
	}

	@Override
	public void preRemove() {
		if (users != null) {
			users.forEach(user -> user.getGroups().remove(this));
		}
	}

	@Override
	public void preUpdate(Group request) {
		this.name = request.getName();
	}
}
