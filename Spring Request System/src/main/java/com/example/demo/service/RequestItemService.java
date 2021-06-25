package com.example.demo.service;

import com.example.demo.dao.RequestItemRepository;
import com.example.demo.model.RequestItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestItemService {
	@Autowired
    private RequestItemRepository repository;

    public RequestItem addRequestItem (RequestItem requestItem) {
		return repository.save(requestItem);
	} 
}
