package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.entity.AbstractStudyEntity;
import com.example.demo.repository.AbstractStudyRepository;
import com.example.demo.service.AbstractService;

public abstract class AbstractController<T extends AbstractStudyEntity<T>, R extends AbstractStudyRepository<T>, S extends AbstractService<T, R>> {
	protected S service;
	protected AbstractController(S service) {
		this.service = service;
	}
	
	@GetMapping
	public List<T>findAll(){
		return service.findAll();
	}
	
	@GetMapping("{id}")
	public T findById(@PathVariable int id) {
		System.out.println("*****Cstart*****");
		T t= service.findById(id);
		System.out.println("*****Cend*****");
		return t;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public T add(@RequestBody T entity) {
		return service.add(entity);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		service.deleteById(id);
	}
	
	@PutMapping("{id}")
	public T update(@PathVariable int id, @RequestBody T entity) {
		return service.update(id,entity);
	}
}

