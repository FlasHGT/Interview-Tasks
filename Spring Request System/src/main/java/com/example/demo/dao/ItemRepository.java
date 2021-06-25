package com.example.demo.dao;

import com.example.demo.model.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // ItemRepository now inherits JpaRepository functions with the type Item
}
