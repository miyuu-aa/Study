package com.example.demo.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class AbstractStudyEntity<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(accessMode = AccessMode.READ_ONLY)
	protected int id;
	@Version
	@Schema(accessMode = AccessMode.READ_ONLY)
	protected long version;

	public abstract void validate();

	public abstract void rewrite();
	
	public abstract void preRemove();
	
	public abstract void preUpdate(T entity);

	//	@CreatedBy
	//	String createdBy;
	//	
	//	@CreatedDate
	//	LocalDateTime createdDate;
	//	
	//	@LastModifiedBy
	//	String lastModifiedBy;
	//
	//	@LastModifiedDate
	//	LocalDateTime lastModifiedDate;

}
