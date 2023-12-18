package com.sirma.staffprojectmanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@MappedSuperclass
public class BaseEntity {

	private Long id;

	public BaseEntity(Long id) {
		this.id = id;
	}

	public BaseEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
