package com.example.demo;

import com.example.demo.dao.AttributeRepository;
import com.example.demo.dao.AttributeValRepository;
import com.example.demo.dao.ItemGroupRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.model.Attribute;
import com.example.demo.model.AttributeVal;
import com.example.demo.model.Item;
import com.example.demo.model.ItemGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
	ItemRepository itemRepository;
    @Autowired
	ItemGroupRepository itemGroupRepository;
    @Autowired
	AttributeRepository attributeRepository;
    @Autowired
	AttributeValRepository attributeValRepository;

	@Override
	public void run(String... args) throws Exception {
		seedData();
	}

    private void seedData() {
		if (itemGroupRepository.count() == 0) {

            ItemGroup monitors = new ItemGroup("Monitors");
            ItemGroup desktops = new ItemGroup("Desktops");
            ItemGroup laptops = new ItemGroup("Laptops");

            List<ItemGroup> itemGroups = new ArrayList<>();
            itemGroups.add(monitors);
            itemGroups.add(desktops);
            itemGroups.add(laptops);
            itemGroupRepository.saveAll(itemGroups);

            List<Item> items = new ArrayList<>();
            items.add(new Item(monitors, "BenQ EX3415R"));
            items.add(new Item(desktops, "MSI Nightblade 3"));
            items.add(new Item(laptops, "MSI GS76 Stealth"));
            itemRepository.saveAll(items);

            Attribute mon1 = new Attribute(monitors, "Size");
            Attribute mon2 = new Attribute(monitors, "Resolution");
            Attribute desk1 = new Attribute(desktops, "Processor");
            Attribute desk2 = new Attribute(desktops, "Video Card");
            Attribute lap1 = new Attribute(laptops, "Video Card");
            Attribute lap2 = new Attribute(laptops, "SSD");

            List<Attribute> attributes = new ArrayList<>();
            attributes.add(mon1);
            attributes.add(mon2);
            attributes.add(desk1);
            attributes.add(desk2);
            attributes.add(lap1);
            attributes.add(lap2);
            attributeRepository.saveAll(attributes);

            List<AttributeVal> attributeVals = new ArrayList<>();
            attributeVals.add(new AttributeVal(mon1, "24"));
            attributeVals.add(new AttributeVal(mon1, "32"));
            attributeVals.add(new AttributeVal(mon2, "1920x1080"));
            attributeVals.add(new AttributeVal(mon2, "3840x2160"));
            attributeVals.add(new AttributeVal(desk1, "Ryzen 9"));
            attributeVals.add(new AttributeVal(desk1, "Ryzen 7"));
            attributeVals.add(new AttributeVal(desk2, "RTX 3090"));
            attributeVals.add(new AttributeVal(desk2, "RTX 3070"));
            attributeVals.add(new AttributeVal(lap1, "RTX A5000"));
            attributeVals.add(new AttributeVal(lap1, "Quadro RTX 6000"));
            attributeVals.add(new AttributeVal(lap2, "500 GB"));
            attributeVals.add(new AttributeVal(lap2, "1 TB"));
            attributeValRepository.saveAll(attributeVals);

		}
	}
}
