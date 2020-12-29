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

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.example.pbs.Tasks.Task;
import com.example.pbs.adapter.TaskAdapter;


import java.util.ArrayList;

public class ReturnInvoice extends Fragment {
    RecyclerView recyclerView;
    Toolbar toolbar;
   static CallFragment callFragment;
    private static ReturnInvoice instance;
    public  static  ReturnInvoice getInstance(){
        if(instance==null)
        {
            instance=new ReturnInvoice();
        }
        return instance;
    }
    public void SetFragments(CallFragment callFragment) {
        this.callFragment = callFragment;




    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view= inflater.inflate(R.layout.fragment_return_invoice, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.return_invoice));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        recyclerView=view.findViewById(R.id.return_invoice_recycleView);
        ArrayList<Task> examples=new ArrayList<>();
        examples.add(new Task(getString(R.string.return_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"1","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"2","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"3","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"4","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"5","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"6","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"7","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.return_invoice)+"8","12/4/2020",R.drawable.more_vert_black));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
       // setHasOptionsMenu(true);
        recyclerView.setLayoutManager(layoutManager);
        TaskAdapter taskAdapter=new TaskAdapter(examples);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.SetOnItemClickListener(new TaskAdapter.OnItemClickListrner() {
            @Override
            public void OnItemClick(int position) {
              callFragment.call_fragment_method(new Edit_Return_Invoice());
            }
        });

        return view;
    }
    public void setCallFragments(CallFragment callFragment) {
        this.callFragment = callFragment;
    }


}
