package com.example.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "admin")
public class Admin extends User {

	private String role;

	@Override
	public void preUpdate(User request) {
		super.preUpdate(request);
		if (request instanceof Admin adminRequest) {
			this.role = adminRequest.getRole();
		}
	}

}