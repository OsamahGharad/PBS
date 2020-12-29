package com.example.pbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Modle.SalesInvoice_Items;
import com.example.pbs.R;

public class SalesInvoiceAdapter extends RecyclerView.Adapter< SalesInvoiceAdapter.ViewHolder>{
    private SalesInvoice_Items[] listdata;

    // RecyclerView recyclerView;
    public  SalesInvoiceAdapter(SalesInvoice_Items[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.create_invoice_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SalesInvoice_Items myListData = listdata[position];


        holder.UnitOfMeasure.setText(listdata[position].getV_UnitOfMeasure());
        holder.category.setText(listdata[position].getV_category());
        holder.Quantity.setText(listdata[position].getV_Quantity());
        holder.UnitPrice.setText(listdata[position].getV_UnitPrice());
        holder.Tax.setText(listdata[position].getV_Tax());
        holder.Discount.setText(listdata[position].getV_Discount());

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

        public TextView category,UnitOfMeasure,Quantity,UnitPrice,Tax,Discount;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            this.UnitOfMeasure = (TextView) itemView.findViewById(R.id.S_item_size);
            this.Quantity = (TextView) itemView.findViewById(R.id.S_item_amount);
            this.category = (TextView) itemView.findViewById(R.id.S_item_category);
            this.UnitPrice = (TextView) itemView.findViewById(R.id.S_item_unite_price);
            this.Tax = (TextView) itemView.findViewById(R.id.S_item_tax);
            this.Discount = (TextView) itemView.findViewById(R.id.S_item_discount);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.SalesInvoice_relativeLayout);
        }
    }
}