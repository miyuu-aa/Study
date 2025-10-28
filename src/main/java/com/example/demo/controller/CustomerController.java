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

import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("customer")
public class CustomerController extends AbstractController<User, UserRepository, UserService> {

	protected CustomerController(UserService service) {
		super(service);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	CustomerService customerService;

	@PostMapping("{id}/all")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }
	
	@PutMapping("{id}/all")
	public Customer update(@PathVariable int id, @RequestBody Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
	
	@PutMapping("/{id}/point")
	public Customer addPoist(@PathVariable int id, @RequestParam long point) {
		return customerService.addPoint(id, point);
	}
}
