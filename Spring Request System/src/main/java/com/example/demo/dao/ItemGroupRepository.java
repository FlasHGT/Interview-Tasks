package com.example.demo.dao;

import com.example.demo.model.ItemGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long> {
    // ItemGroupRepository now inherits JpaRepository functions with the type ItemGroup
}
