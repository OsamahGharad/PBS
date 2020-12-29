package com.example.pbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pbs.R;
import com.example.pbs.Tasks.Task;
import com.example.pbs.Tasks.TaskViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import static com.google.gson.reflect.TypeToken.get;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private  static int View_Type_Item= 0;
    private static int View_Type_divider=1;
    private ArrayList<Task> arrayList;
    private  OnItemClickListrner mlistrner;;


    public  interface OnItemClickListrner
    {
        void OnItemClick(int position);

    }

    public TaskAdapter( ArrayList<Task> marrayList) {
        arrayList = marrayList;
        // notifyDataSetChanged();

    }



    public  void SetOnItemClickListener(OnItemClickListrner listrner)
    {
        mlistrner=listrner;
    }
    public  class TaskViewHolder extends  RecyclerView.ViewHolder
    {    int mviewtype;
        public TextView startDate,endDate;
        public ImageView image;


        public TaskViewHolder(@NonNull View itemView , final OnItemClickListrner listrner ) {
            super(itemView);
//            mviewtype=viewtype;
//            if(mviewtype==View_Type_Item) {
            startDate = itemView.findViewById(R.id.card_startDate);
            endDate = itemView.findViewById(R.id.card_endDate);
            image = itemView.findViewById(R.id.card_image);


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (listrner != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listrner.OnItemClick(position);

                        }
                    }


                }
            });
//            }
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate( R.layout.cardview,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view ,mlistrner);

        return  taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if(holder.mviewtype==View_Type_Item) {
            Task current = arrayList.get(position);
            holder.startDate.setText(current.getmStartDates());
            holder.endDate.setText(current.getmEndDates());


        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        if(position%2==0)
//            return  View_Type_Item;
//        return View_Type_divider;
//    }


}
