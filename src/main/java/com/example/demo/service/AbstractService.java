package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.entity.AbstractStudyEntity;
import com.example.demo.exception.BusinessException;
import com.example.demo.repository.AbstractStudyRepository;

@Transactional
public abstract class AbstractService<T extends AbstractStudyEntity<T>, R extends AbstractStudyRepository<T>> {

	protected R repository;

	protected AbstractService(R repository) {
		this.repository = repository;
	}

	public List<T> findAll() {
		List<T> all = repository.findAll();
		all.forEach(T::rewrite);
		return all;
	}

	public T findById(int id) {
		System.out.println("*****Sstart*****");
		T entity = repository.findById(id)
				.orElseThrow(() -> new BusinessException(new ErrorResponse("Data Not Found.", LocalDate.now())));
		entity.rewrite();
		System.out.println("*****Send*****");
		return entity;

	}

	public T add(T entity) {
		entity.validate();
		T add = repository.save(entity);
		entity.rewrite();
		return add;
	}

	public void deleteById(int id) {
		T existing = findById(id);
		existing.preRemove();
		repository.deleteById(id);
	}

	public T update(int id, T entity) {
		entity.validate();
		T existing = findById(id);
		//		entity.setId(existing.getId());
		//		entity.setVersion(existing.getVersion());
		existing.preUpdate(entity);
		T update = repository.save(existing);
		update.rewrite();
		return update;
	}

//	protected abstract void validate(T entity);
//
//	protected abstract void rewrite(T entity);
}
