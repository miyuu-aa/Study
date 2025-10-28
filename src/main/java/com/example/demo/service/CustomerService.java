package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CustomerService extends AbstractService<User, UserRepository> {

	protected CustomerService(UserRepository repository) {
		super(repository);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer findById(int id) {
		User user = super.findById(id);
		if (!(user instanceof Customer customer)) {
			throw new BusinessException(new ErrorResponse("Not an Customer.", LocalDate.now()));
		}
		customer.rewrite();
		return customer;
	}
	
	public Customer add (Customer request) {
		request.validate();
        Customer add = repository.save(request);
        add.setPoint(request.getPoint());
        add.rewrite();
        return add;
	}
	
	public Customer updateCustomer(int id, Customer request) {
		request.validate();
		Customer existing = findById(id);
		existing.preUpdate(request);
		Customer update = repository.save(existing);
		update.setPoint(request.getPoint());
		update.rewrite();
		return update;
	}
	
	public Customer addPoint(int id, long point) {
		User user = repository.findById(id)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("User Not Found.", LocalDate.now())));
		if (!(user instanceof Customer customer)) {
			throw new BusinessException(new ErrorResponse("Not a Customer.", LocalDate.now()));
		}
		customer.setPoint(customer.getPoint() + point);
		return repository.save(customer);
	}

}
