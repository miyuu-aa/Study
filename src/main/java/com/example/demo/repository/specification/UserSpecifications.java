package com.example.demo.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.Gender;
import com.example.demo.entity.User;

public class UserSpecifications {


	public static Specification<User> isWomen() {
		return (root, query, builder) -> builder.equal(root.get("genderCode"), Gender.WOMAN.getCode());
	}

	public static Specification<User> isChildren() {
		return (root, query, builder) -> builder.greaterThan(root.get("birthday"), LocalDate.now().minusYears(18));
	}
	
}
