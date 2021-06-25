package com.example.demo.dao;

import com.example.demo.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    // RequestRepository now inherits JpaRepository functions with the type Request
}
