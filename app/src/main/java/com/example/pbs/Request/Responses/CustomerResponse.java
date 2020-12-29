package com.example.pbs.Request.Responses;

import com.example.pbs.Customers.Customer_Entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerResponse {
    @SerializedName("customer_entity")
    @Expose()
    private Customer_Entity customer_entity;
    @SerializedName("state")
    @Expose()
    private  String state;


    public Customer_Entity getCustomer_entity() {
        return customer_entity;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "customer_entity=" + customer_entity +
                ", state='" + state + '\'' +
                '}';
    }
}
