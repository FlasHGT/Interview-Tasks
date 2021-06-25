package com.example.demo.dao;

import com.example.demo.model.AttributeVal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeValRepository extends JpaRepository<AttributeVal, Long> {
    // AttributeValRepository now inherits JpaRepository functions with the type AttributeVal
}
