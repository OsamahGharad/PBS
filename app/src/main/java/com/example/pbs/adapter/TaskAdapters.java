package com.example.pbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pbs.R;
import com.example.pbs.Modle.sales_invoice;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class TaskAdapters extends RecyclerView.Adapter<TaskAdapters.TaskViewHolder> {
    private  static int View_Type_Item= 0;
    private static int View_Type_divider=1;
    private ArrayList<sales_invoice> arrayList;
    private  OnItemClickListrner mlistrner;




    public  interface OnItemClickListrner
    {
        void OnItemClick(int position);

    }
    public TaskAdapters(ArrayList<sales_invoice> marrayList) {
        arrayList = marrayList;
    }

    public  void SetOnItemClickListener(OnItemClickListrner listrner)
    {
        mlistrner=listrner;
    }
    public  static class TaskViewHolder extends  RecyclerView.ViewHolder
    {    int mviewtype;
        public ImageView invoice_Image;
        public TextView paid,indept,invoice_date;



        public TaskViewHolder(@NonNull View itemView , final OnItemClickListrner listrner,int viewtype) {
            super(itemView);
            mviewtype=viewtype;
            if(mviewtype==View_Type_Item) {
                invoice_Image = itemView.findViewById(R.id.card_incoice_image);
                paid = itemView.findViewById(R.id.card_paid_amount);
                indept = itemView.findViewById(R.id.card_indebt_amount);
                invoice_date = itemView.findViewById(R.id.card_date);
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
            }
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size()*2;
    }




    @Override
    public int getItemViewType(int position) {
        if(position%2==0)
            return  View_Type_Item;
        return View_Type_divider;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(viewType==View_Type_Item? R.layout.card_invoice_card :R.layout.recycleviewdivider,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view ,mlistrner,viewType);

        return  taskViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        if (holder.mviewtype == View_Type_Item) {
            sales_invoice current = arrayList.get(position / 2);
            holder.invoice_Image.setImageResource(current.getImage());
            holder.paid.setText(String.valueOf(current.getPaid_amount()));
            holder.indept.setText(String.valueOf(current.getIndept_amount()));
            holder.invoice_date.setText(current.getDates());
        }


    }
}
