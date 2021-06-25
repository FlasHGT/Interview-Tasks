package com.example.demo.validator;

import javax.validation.constraints.Min;

public class ItemFormValidator {

    @Min(value = 1, message = "Please choose an item!")
    // If the value is zero, that means that no item has been selected
    // that is the default option
    private Long itemID;

    public ItemFormValidator() {
        
    }

    public Long getItemID() {
        return this.itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }
}
