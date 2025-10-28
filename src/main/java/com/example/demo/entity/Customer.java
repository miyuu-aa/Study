package com.example.demo.entity;

import java.time.LocalDate;

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
@DiscriminatorValue(value = "customer")
public class Customer extends User {

	private long point;

	public Customer(String firstName, String lastName, int genderCode, LocalDate birthday, Long point) {
		super(firstName, lastName, genderCode, birthday);
		this.point = point;
	}

	@Override
	public void preUpdate(User request) {
		super.preUpdate(request);
		if (request instanceof Customer customerRequest) {
			this.point = customerRequest.getPoint();
		}
	}
}
