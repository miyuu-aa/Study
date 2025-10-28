package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AdminService extends AbstractService<User, UserRepository> {

	public AdminService(UserRepository repository) {
		super(repository);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	AdminRepository adminRepository;

	@Override
	public Admin findById(int id) {
		User user = super.findById(id);
		if (!(user instanceof Admin admin)) {
			throw new BusinessException(new ErrorResponse("Not an Admin.", LocalDate.now()));
		}
		admin.rewrite();
		return admin;
	}
	
	public Admin add (Admin request) {
		request.validate();
        Admin add = repository.save(request);
        add.setRole(request.getRole());
        add.rewrite();
        return add;
	}

	public Admin updateAdmin(int id, Admin request) {
//		admin.setFirstName(request.getFirstName());
//		admin.setLastName(request.getLastName());
//		admin.setGenderCode(request.getGenderCode());
//		admin.setBirthday(request.getBirthday());
//		admin.setRole(request.getRole());
		request.validate();
		Admin existing = findById(id);
		existing.preUpdate(request);
		Admin update = repository.save(existing);
		update.setRole(request.getRole());
		update.rewrite();
		return update;
	}

	public Admin updateRole(int id, String role) {
		User user = repository.findById(id)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
		if (!(user instanceof Admin admin)) {
			throw new BusinessException(new ErrorResponse("Not an Admin.", LocalDate.now()));
		}
		admin.setRole(role);
		return repository.save(admin);
	}

}
