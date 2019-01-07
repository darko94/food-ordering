package com.foodordering.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodordering.entity.GroupOrder;

import java.util.UUID;

@Repository
public interface GroupOrderRepository extends JpaRepository<GroupOrder, UUID> {

}
