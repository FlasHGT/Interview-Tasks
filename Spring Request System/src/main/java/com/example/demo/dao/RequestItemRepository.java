package com.example.demo.dao;

import com.example.demo.model.RequestItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestItemRepository extends JpaRepository<RequestItem, Long> {
    // RequestItemRepository now inherits JpaRepository functions with the type RequestItem
}
