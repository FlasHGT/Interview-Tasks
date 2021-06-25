package com.example.demo.service;

import java.util.List;

import com.example.demo.dao.ItemRepository;
import com.example.demo.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	@Autowired
    private ItemRepository repository;

    public List<Item> getItems () {
		return repository.findAll();
	}

	public Item findItemByID (Long id) {
		return repository.findById(id).get();
	}

	public String findItemName (Long id) {
		return repository.findById(id).get().getName();
	}

	public Long findItemGroup (Long id) {
		return repository.findById(id).get().getItem_Group().getId();
	}
}
