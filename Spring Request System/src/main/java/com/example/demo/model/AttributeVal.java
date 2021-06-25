package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class AttributeVal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Creates new ID depending on the last ID in the database,
    // this doesn't mean that every table will start with ID - 1
    private Long id;

    @ManyToOne
    @JoinColumn(name="attribute_id", nullable=false)
    // name determines the name of the column in the DB table
    private Attribute attribute;

    private String name;

    public AttributeVal() {

    }

    public AttributeVal(Attribute attribute, String name) {
        this.attribute = attribute;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
