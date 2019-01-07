package com.foodordering.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private UUID id;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@JoinColumn(name = "authorities_id", referencedColumnName = "id")
	@ManyToOne
	private Authority authorityId;

	public User() {
	}

	public User(UUID id) {
		this.id = id;
	}

	public User(UUID id, String email) {
		this.id = id;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Authority getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Authority authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", authorityId=" + authorityId + "]";
	}

}