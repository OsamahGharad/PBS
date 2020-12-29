package com.example.pbs.Tasks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Fragment.ERBInvoice;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.example.pbs.adapter.TaskAdapter;

import java.util.ArrayList;

public class TaskNumber extends Fragment {

    RecyclerView recyclerView;
    CardView TaskPlan,TaskDetails;
     ArrayList<Task> examples;
    Toolbar toolbar;
    static CallFragment callFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void SetFragments(CallFragment callFragment) {
        this.callFragment = callFragment;

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_task_number, null);

        recyclerView=view.findViewById(R.id.tasknumber_recycleView);
//        TaskPlan=view.findViewById(R.id.taskPlan);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.task_Details));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        examples=new ArrayList<>();
        examples.add(new Task(getString(R.string.task_Details),"12/4/2020", R.drawable.more_vert_black));
        examples.add(new Task(getString(R.string.movement_planing),"12/4/2020", R.drawable.more_vert_black));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        TaskAdapter taskAdapter=new TaskAdapter(examples);
        recyclerView.setAdapter(taskAdapter);
        taskAdapter.SetOnItemClickListener(new TaskAdapter.OnItemClickListrner() {
            @Override
            public void OnItemClick(int position) {
                 if(position==0){
                     callFragment.call_fragment_method(new ERBInvoice());
                 }
             else if(position==1)
                 {
                     callFragment.call_fragment_method(new TaskPlan() );
                 }


            }
        });




        return  view;
    }
    public void setCallFragments(CallFragment callFragment)
    {
        this.callFragment = callFragment;
    }
}
