package com.example.demo.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Creates new ID depending on the last ID in the database,
    // this doesn't mean that every table will start with ID - 1
    private Long id;

    @ManyToOne
    @JoinColumn(name="item_group_id", nullable=false)
    // name determines the name of the column in the DB table
    private ItemGroup item_Group;

    @OneToMany(mappedBy="attribute") 
    // mappedBy is the name of the variable in the other model
    private Set<AttributeVal> attribute_Vals;

    private String name;

    public Attribute() {

    }

    public Attribute(ItemGroup item_Group, String name) {
        this.item_Group = item_Group;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ItemGroup getItem_Group() {
        return this.item_Group;
    }

    public void setItem_Group(ItemGroup item_Group) {
        this.item_Group = item_Group;
    }

    public Set<AttributeVal> getAttribute_Vals() {
        return this.attribute_Vals;
    }

    public void setAttribute_Vals(Set<AttributeVal> attribute_Vals) {
        this.attribute_Vals = attribute_Vals;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
