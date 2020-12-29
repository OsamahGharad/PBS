package com.example.pbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Modle.GoodItems;
import com.example.pbs.R;

public class GoodsAdapter extends RecyclerView.Adapter< GoodsAdapter.ViewHolder>{
    private GoodItems[] listdata;

    // RecyclerView recyclerView;
    public  GoodsAdapter(GoodItems[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_good_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GoodItems myListData = listdata[position];


        holder.product.setText(listdata[position].getG_product());
        holder.category.setText(listdata[position].getG_category());
        holder.CurrentQty.setText(listdata[position].getCurrentQty());
        holder.RemainQty.setText(listdata[position].getG_RemainQty());

       /* holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"click on item: "+myListData.getDescription(),Toast.LENGTH_LONG).show();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView product,category,CurrentQty,RemainQty;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            this.CurrentQty = (TextView) itemView.findViewById(R.id.good_curentQTY);
            this.product = (TextView) itemView.findViewById(R.id.good_productN);
            this.category = (TextView) itemView.findViewById(R.id.good_category);
            this.RemainQty = (TextView) itemView.findViewById(R.id.good_remainQty);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}