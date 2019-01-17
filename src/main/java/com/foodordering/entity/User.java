package com.foodordering.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public @Data class User extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Column
	private String email;
	@Column
	private String password;
	@JoinColumn(name = "authorities_id", referencedColumnName = "id")
	@ManyToOne
	private Authority authority;
}