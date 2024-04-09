package com.gravityer.bogo.model;

import java.util.List;

public class CalculationResult {
    private List<Integer> discountedItems;
    private List<Integer> payableItems;

    //Constructor
    public CalculationResult(List<Integer> discountedItems, List<Integer> payableItems) {
        this.discountedItems = discountedItems;
        this.payableItems = payableItems;
    }

    //Getters
    public List<Integer> getDiscountedItems() {
        return discountedItems;
    }
    
    public List<Integer> getPayableItems() {
        return payableItems;
    }
}