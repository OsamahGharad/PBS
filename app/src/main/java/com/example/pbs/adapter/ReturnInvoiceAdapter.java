package com.example.pbs.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Modle.ReturnInvoice_Items;
import com.example.pbs.R;

public class ReturnInvoiceAdapter extends RecyclerView.Adapter<ReturnInvoiceAdapter.ViewHolder>{
    private ReturnInvoice_Items[] listdata;

    // RecyclerView recyclerView;
    public   ReturnInvoiceAdapter(ReturnInvoice_Items[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.create_return_invoice_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReturnInvoice_Items myListData = listdata[position];


        holder.UnitOfMeasure.setText(listdata[position].getR_UnitOfMeasure());
        holder.category.setText(listdata[position].getR_category());
        holder.Quantity.setText(listdata[position].getR_Quantity());
        holder.ReturnReason.setText(listdata[position].getR_ReturnReason());


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

        public TextView category,UnitOfMeasure,Quantity,ReturnReason;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            this.UnitOfMeasure = (TextView) itemView.findViewById(R.id.Return_invoice_size);
            this.Quantity = (TextView) itemView.findViewById(R.id.Return_invoice_amount);
            this.category = (TextView) itemView.findViewById(R.id.Return_invoice_category);
            this.ReturnReason = (TextView) itemView.findViewById(R.id.Return_invoice_returnReason);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.ReturnInvoiceRelativeLayout);
        }
    }
}