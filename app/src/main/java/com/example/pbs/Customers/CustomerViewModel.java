package com.example.pbs.Customers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class CustomerViewModel extends AndroidViewModel {
    CustomerRepository customerRepository;
    public MutableLiveData<Customer_Entity> customer_entityMutableLiveData=new MutableLiveData<>();

    public CustomerViewModel(@NonNull Application application) {
        super(application);
        customerRepository=CustomerRepository.getInstance(application);
    }

//  public void  FormValidation(Customer_Entity customer_entity) {
//      if (customer_entity.getCust_first_name().isEmpty()) {
//          this.username.setValue("Field can't be empty");
//
//      } else if (user.getUsername().length()< 6) {
//          this.username.setValue("Username less than 6 digit");
//
//      }
//    }

}
