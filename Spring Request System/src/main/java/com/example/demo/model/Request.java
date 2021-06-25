package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Creates new ID depending on the last ID in the database,
    // this doesn't mean that every table will start with ID - 1
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_item_id")
    // name determines the name of the column in the DB table
    private RequestItem request_Item;

    private Integer status;
    // -1 - Neutral; 0 - Rejected; 1 - Approved
    
    private String reason;
    private LocalDateTime createdDate;

    // This will create another table with the name request_attrs and put
    // attribute name and value in it, it will also add a column with the name 'id'
    // and create a foreign key to this request, so each request can have
    // multiple rows with attribute pairs in this table 
    @ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="request_attrs", joinColumns=@JoinColumn(name="id"))
    private Map<String, String> attributes;

    public Request() {

    }

    public Request(RequestItem request_Item, Integer status, String reason, LocalDateTime createdDate, Map<String,String> attributes) {
        this.request_Item = request_Item;
        this.status = status;
        this.reason = reason;
        this.createdDate = createdDate;
        this.attributes = attributes;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String,String> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String,String> attributes) {
        this.attributes = attributes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestItem getRequest_Item() {
        return this.request_Item;
    }

    public void setRequest_Item(RequestItem request_Item) {
        this.request_Item = request_Item;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


    
}