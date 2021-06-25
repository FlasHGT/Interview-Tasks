package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.RequestRepository;
import com.example.demo.model.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService { 
	@Autowired
	private RequestRepository repository;

    public List<Request> getRequests () {
		return repository.findAll();
	}

	public void updateRequestStatusByID (Long id, Integer status) {
		Request request = repository.findById(id).get();
		request.setStatus(status);
		repository.save(request);
	}

    public void addNewRequest(Request request) {
		repository.save(request);
    }

	public void deleteRequest(Long id) {
		repository.deleteById(id);
	}   
}
