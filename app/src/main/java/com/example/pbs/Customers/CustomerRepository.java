package com.example.pbs.Customers;

import android.content.Context;

import com.example.pbs.CurrentLocation.TrackingRepository;

public class CustomerRepository {
    private  static  CustomerRepository ourInstance;
    public static CustomerRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new CustomerRepository(context.getApplicationContext());
        }

        return ourInstance;
    }

    public CustomerRepository(Context context) {

    }
}
