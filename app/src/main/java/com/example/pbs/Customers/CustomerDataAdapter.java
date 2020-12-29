package com.example.pbs.Customers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.R;

public class CustomerDataAdapter  extends RecyclerView.Adapter<CustomerDataAdapter.ViewHolder>{

    private Customer_Entity[] listdata;

    // RecyclerView recyclerView;
    public  CustomerDataAdapter (Customer_Entity[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.customer_list_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Customer_Entity myListData = listdata[position];


        holder.CustomerName.setText(listdata[position].getCust_first_name()+listdata[position].getCust_last_name());
        holder.CustomerAddress.setText(listdata[position].getArea());
        holder.PhoneNumber.setText(listdata[position].getPhone_number());
        holder.MaxiCredit.setText(listdata[position].getMaximum_credit());

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

        public TextView CustomerName,CustomerAddress,PhoneNumber,MaxiCredit;

        public RelativeLayout relativeLayout;
        public ViewHolder(View itemView) {
            super(itemView);


            this.PhoneNumber = (TextView) itemView.findViewById(R.id.DPhoneNumber);
            this.CustomerName = (TextView) itemView.findViewById(R.id.DCustomerName);
            this.CustomerAddress = (TextView) itemView.findViewById(R.id.DCustomerAddress);
            this.MaxiCredit = (TextView) itemView.findViewById(R.id.Data_Maxi_Credit);

            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.CustomerrelativeLayout);
        }
    }
}
