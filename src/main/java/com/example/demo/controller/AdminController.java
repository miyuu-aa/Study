package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("admin")
public class AdminController extends AbstractController<User, UserRepository, UserService> {

    protected AdminController(UserService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}
    
    @Autowired
    AdminService adminService;
    
    @PostMapping("{id}/all")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin add(@RequestBody Admin admin) {
        return adminService.add(admin);
    }
    
    @PutMapping("{id}/all")
    public Admin update(@PathVariable int id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

	@PutMapping("/{id}/role")
    public Admin updateRole(@PathVariable int id, @RequestParam String role) {
        return adminService.updateRole(id, role);
    }
}

