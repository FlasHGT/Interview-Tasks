package com.example.demo.service;

import com.example.demo.dao.AttributeValRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttributeValService {
    @Autowired
    private AttributeValRepository repository;

    public String getAttrValNameByID (Long id) {
        return repository.findById(id).get().getName();
  }
}
