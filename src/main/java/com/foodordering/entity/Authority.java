package com.foodordering.entity;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "authorities")
@EqualsAndHashCode(callSuper=false)
public @Data class Authority extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column
	private String authority;
	@OneToMany(mappedBy = "authority")
	private List<User> users;
}