package com.example.pbs.Customers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;


import java.util.ArrayList;

public class CustomerData extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private CustomerDataAdapter adapter;
    private ArrayList<Customer_Entity> mGoodsList;
    private RequestQueue mRequestQueue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_data, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_customer));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        int phoneNumber = 7734567;
        int maxCredit=767;
        String phoneNumberString = Integer.toString(phoneNumber );
        String maxCreditString = Integer.toString(maxCredit);

        Customer_Entity[] myListData = new Customer_Entity[] {
                new Customer_Entity(),
                new Customer_Entity(),
                new Customer_Entity(),
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.Dcustomer_recycleView);
        adapter = new CustomerDataAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return  view;
    }

}
