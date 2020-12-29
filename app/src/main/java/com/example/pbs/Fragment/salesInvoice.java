package com.example.pbs.Fragment;

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

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.Modle.Invoice;
import com.example.pbs.Modle.sales_invoice_model;
import com.example.pbs.Tasks.Task;
import com.example.pbs.adapter.Exbandable_Invoice;
import com.example.pbs.adapter.TaskAdapter;
import com.example.pbs.R;


import java.util.ArrayList;
import java.util.List;

public class salesInvoice extends Fragment {
    Exbandable_Invoice exbandable_invoice;
   RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TaskAdapter taskAdapter;
    Toolbar toolbar;
    ArrayList<Task> examples;
    static CallFragment callFragment;
    private static salesInvoice instance;


    public  static  salesInvoice getInstance(){
        if(instance==null)
        {
            instance=new salesInvoice();
        }
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         examples=new ArrayList<>();
        examples.add(new Task(getString(R.string.sales_invoice)+"0","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"0","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"0","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"8","12/4/2020",R.drawable.more_vert_black));

        examples.add(new Task(getString(R.string.sales_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"0","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.sales_invoice)+"8","12/4/2020",R.drawable.more_vert_black));

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView
            (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sales_invoice, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.sales_invoice));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        recyclerView=view.findViewById(R.id.sales_invoice_recycleView);
        layoutManager=new LinearLayoutManager(getContext());
        set_recycle_adapter();
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//        taskAdapter=new TaskAdapter(examples);
//        recyclerView.setAdapter(taskAdapter);
//       taskAdapter.SetOnItemClickListener(new TaskAdapter.OnItemClickListrner() {
//            @Override
//            public void OnItemClick(int position) {
//               callFragment.call_fragment_method(new ERBInvoice() );
//            }
//        });

        return view;
    }
    private void set_recycle_adapter() {
        recyclerView.setLayoutManager(layoutManager);
        exbandable_invoice=new Exbandable_Invoice(getContext(),intiData());
        recyclerView.setAdapter(exbandable_invoice);
        exbandable_invoice.setParentClickableViewAnimationDefaultDuration();
        exbandable_invoice.setParentAndIconExpandOnClick(true);
        exbandable_invoice.SetOnItemClickListener(new Exbandable_Invoice.OnItemClickListrner() {
            @Override
            public void OnItemClick(int position) {
                callFragment.call_fragment_method(new ERBInvoice()  );
            }
        } );
    }


        public List<ParentObject> intiData()
        {    List<ParentObject> parentObjectList=new ArrayList<>();
            List<sales_invoice_model> list=new ArrayList<>();
                     for(int i=0;i<10;i++)
             {
               // list.add(new sales_invoice_model("Invoice","12/23/2020",R.drawable.invoice_white));
                 list.add(new sales_invoice_model("12/23/2020","9876","97867",R.drawable.invoice_white));

            }
            for(sales_invoice_model  sales_invoice_model:list)
            {
                List<Object> childObject =new ArrayList<>();
                childObject.add(new Invoice(R.drawable.invoice_block,R.drawable.payment,R.drawable.detail_24dp));
                sales_invoice_model.setChildObjectList(childObject);
                parentObjectList.add(sales_invoice_model);
            }

            return parentObjectList;
        }


    public void setCallFragments(CallFragment callFragment)

    {
        this.callFragment = callFragment;
    }


    }

