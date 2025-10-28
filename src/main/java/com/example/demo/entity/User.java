package com.example.demo.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "discriminatorJsonType")
@JsonSubTypes({ @JsonSubTypes.Type(value = Admin.class), @JsonSubTypes.Type(value = Customer.class) })
public abstract class User extends AbstractStudyEntity<User> {


	//	@Schema(accessMode = AccessMode.READ_ONLY)
	//	private int id;
	//	private String name;
	//	private String gender;
	//	private int age;
	//	@NotBlank
	//	private String name;

	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
	protected String fullName;

	protected String firstName;

	protected String lastName;

	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
	private String gender;

	protected int genderCode;

	@Transient
	@Schema(accessMode = AccessMode.READ_ONLY)
	private int age;

	protected LocalDate birthday;

	@OneToOne(mappedBy = "user", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	@Schema(accessMode = AccessMode.READ_ONLY)
	Profile profile;

	@OneToMany(mappedBy = "user", orphanRemoval = true)
	@Cascade(CascadeType.ALL)
	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<Post> posts = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
	@Schema(accessMode = AccessMode.READ_ONLY)
	private List<Group> groups = new ArrayList<>();

	@Override
	public String toString() {
		return "User{id=" + id + ", name='" + fullName + "', gender='" + gender + "', age=" + age + "}";
	}

	public User(String firstName, String lastName, int genderCode, LocalDate birthday) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.genderCode = genderCode;
		this.birthday = birthday;
	}

	@Override
	public void validate() {
		if (firstName == null || firstName.isEmpty()) {
			throw new BusinessException(new ErrorResponse("FirstName must not be null or blank.", LocalDate.now()));
		}
		if (lastName == null || lastName.isEmpty()) {
			throw new BusinessException(new ErrorResponse("LastName must not be null or blank.", LocalDate.now()));
		}
		if (!Gender.isValid(genderCode)) {
			throw new BusinessException(new ErrorResponse("This gender code is invalid.", LocalDate.now()));
		}
		if (birthday.isAfter(LocalDate.now())) {
			throw new BusinessException(new ErrorResponse("Age must be 0 or more.", LocalDate.now()));
		}
	}

	public void rewrite() {
		fullName = firstName + " " + lastName;
		gender = Gender.codeToName(genderCode);
		age = Period.between(birthday, LocalDate.now()).getYears();
		groups.size();
	}

	//	@PreRemove
	//	private void preRemove() {
	//		if (profile != null) {
	//			profile.setUser(null);
	//		}
	//		if (posts != null) {
	//			posts.forEach(post -> post.setUser(null));
	//		}
	//	}

	@Override
	public void preRemove() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void preUpdate(User request) {
		//		User existing = userRepository.findById(id)
		//				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
		//		setId(existing.getId());
		//		setVersion(existing.getVersion());
		//		setProfile(existing.getProfile());
		//		getProfile().setUser(this);
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.genderCode = request.getGenderCode();
		this.birthday = request.getBirthday();
	}
}
