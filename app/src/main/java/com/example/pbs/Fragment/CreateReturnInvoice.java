package com.example.pbs.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.Modle.ReturnInvoice_Items;
import com.example.pbs.R;
import com.example.pbs.adapter.ReturnInvoiceAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateReturnInvoice extends Fragment {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private ReturnInvoiceAdapter adapter;
    private ArrayList<ReturnInvoice_Items> mInvoicesList;
    private RequestQueue mRequestQueue;
    FloatingActionButton add_items;
    static CallFragment callFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Nullable

    @Override
    public void onResume() {
        super.onResume();
        //  getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.create_return_invoice,container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getClass().getSimpleName());
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        //-------------------handel click event--------------
        add_items=view.findViewById(R.id.add_more_return_invoice_items);
        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment.call_fragment_method(new Return_Invoice_add_Items());
            }
        });

        //------------add items into list --------------
        int Quantity = 1;
        String StringQuantity = Integer.toString(Quantity );

        ReturnInvoice_Items[] myListData = new  ReturnInvoice_Items[] {
                new  ReturnInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"bbbbb" ),
                new  ReturnInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"bbbbb" ),
                new  ReturnInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"bbbbb" ),
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.CreateReturnInvoice_recycleView);
        adapter = new ReturnInvoiceAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void setCallFragments(CallFragment callFragment)
    {
        this.callFragment = callFragment;
    }
    @Override
    public void onPause() {
        super.onPause();
        // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
