package com.example.pbs.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
import com.example.pbs.Modle.SalesInvoice_Items;
import com.example.pbs.R;
import com.example.pbs.adapter.Exbandable_Invoice;
import com.example.pbs.adapter.SalesInvoiceAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateInvoice extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private SalesInvoiceAdapter adapter;
    private ArrayList<SalesInvoice_Items> mInvoicesList;
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

    public void SetFragments(CallFragment callFragment) {
        this.callFragment = callFragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.create_invoice,container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getClass().getSimpleName());
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        //-----------handel click event-----------
        add_items=view.findViewById(R.id.add_more_invoice_items);
        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment.call_fragment_method(new Invoice_Add_Items());
            }
        });

        //------------add items into list --------------
        int Quantity = 1;
        String StringQuantity = Integer.toString(Quantity );

        SalesInvoice_Items[] myListData = new  SalesInvoice_Items[] {
                new  SalesInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"5000","5%","700" ),
                new  SalesInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"5000","5%","700" ),
                new  SalesInvoice_Items("Cartoon", "Nedo Milk", StringQuantity,"5000","5%","700" )

        };

        recyclerView = (RecyclerView) view.findViewById(R.id.CreateInvoice_recycleView);
        adapter = new SalesInvoiceAdapter(myListData);
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
