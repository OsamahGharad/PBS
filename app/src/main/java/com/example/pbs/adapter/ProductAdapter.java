package com.example.pbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Modle.ProductItems;
import com.example.pbs.R;

public class ProductAdapter extends RecyclerView.Adapter< ProductAdapter.ViewHolder>{
    private ProductItems[] listdata;

    // RecyclerView recyclerView;
    public  ProductAdapter(ProductItems[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.fragment_product_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProductItems myListData = listdata[position];


        holder.product.setText(listdata[position].getP_product());
        holder.price.setText(listdata[position].getP_price());


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

        public TextView product,price;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.product = (TextView) itemView.findViewById(R.id.productN);
            this.price = (TextView) itemView.findViewById(R.id.productPrice);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.productRelativeLayout);
        }
    }
}