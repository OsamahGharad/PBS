package com.example.pbs.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.example.pbs.adapter.TaskAdapters;
import com.example.pbs.Modle.sales_invoice;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Force_invoice extends Fragment {
    RecyclerView recyclerView;
    ArrayList<sales_invoice> examples;
    Toolbar toolbar;
    static CallFragment callFragment;
    FloatingActionButton payment;
    @Override
    public void onAttach(Context context) {super.onAttach(context);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        examples = new ArrayList<>();
        examples.add(new sales_invoice("12/4/2020",12.34f,23.45f,R.drawable.invoice_block));
        examples.add(new sales_invoice("12/4/2020",12.34f,23.45f,R.drawable.invoice_block));
        examples.add(new sales_invoice("12/4/2020",12.34f,23.45f,R.drawable.invoice_block));
        examples.add(new sales_invoice("12/4/2020",12.34f,23.45f,R.drawable.invoice_block));
    }

    public void SetFragments(CallFragment callFragment) {
        this.callFragment = callFragment;




    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.force_invoice, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.force_invoice));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        recyclerView=view.findViewById(R.id.force_recycleView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        TaskAdapters taskAdapters=new TaskAdapters(examples);
        recyclerView.setAdapter(taskAdapters);
        taskAdapters.SetOnItemClickListener(new TaskAdapters.OnItemClickListrner() {
            @Override
            public void OnItemClick(int position) {
                callFragment.call_fragment_method(new Edit_Sales_Invoice());

            }
        });

        //---------handel click event -----------
        payment=view.findViewById(R.id.customer_payment);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating new customer in background thread
                openDialog();
            }
        });

        return  view;

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        toolbar.inflateMenu(R.menu.mainactionbar);
    }


    public void openDialog() {
        PaidDialog paidDialog = new PaidDialog();
        paidDialog.show(getFragmentManager(), "example dialog");
    }
}
