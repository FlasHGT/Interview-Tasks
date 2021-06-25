package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class RequestItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Creates new ID depending on the last ID in the database,
    // this doesn't mean that every table will start with ID - 1
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    // CascadeType.REFRESH means that if we delete the RequestItem
    // the Item will not get deleted, but if the Item gets deleted then
    // all requestItems with that Items ID will get deleted.
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne(mappedBy = "request_Item")
    // mappedBy is the name of the variable in the other model
    private Request request;

    public RequestItem() {

    }

    public RequestItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Request getRequest() {
        return this.request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
