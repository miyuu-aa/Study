package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.entity.User;

public interface UserRepository extends AbstractStudyRepository<User> {
	List<User> findByFirstName(String firstName);

	List<User> findDistinctByLastNameAndFirstName(String lastname, String firstname);

	List<User> findByBirthdayAfter(LocalDate date);

	List<User> findByGenderCodeEquals(int genderCode);

	List<User> findByBirthdayAfterOrderByLastNameDesc(LocalDate date);

	List<User> findByFirstNameStartingWith(String s);

	List<User> findByGenderCodeEqualsOrBirthdayAfter(int genderCode, LocalDate date);

}
