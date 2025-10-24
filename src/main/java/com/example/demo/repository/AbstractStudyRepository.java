package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.demo.entity.AbstractStudyEntity;
@NoRepositoryBean
public interface AbstractStudyRepository<T extends AbstractStudyEntity<T>>  extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {

}
