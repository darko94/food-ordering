package com.foodordering.repository;

import com.foodordering.entity.Authority;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, UUID> {
	Authority findByAuthority(String authority);
}
