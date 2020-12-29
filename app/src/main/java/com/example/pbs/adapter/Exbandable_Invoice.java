package com.example.pbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.pbs.Fragment.Edit_Sales_Invoice;
import com.example.pbs.Fragment.PaidDialog;
import com.example.pbs.Fragment.ReturnInvoice;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.Modle.Invoice;

import com.example.pbs.Modle.sales_invoice_model;
import com.example.pbs.R;

import java.util.List;


public class Exbandable_Invoice  extends ExpandableRecyclerAdapter<Exbandable_Invoice.CategoryViewHolder, Exbandable_Invoice.InvoiceViewHolder> {
    static CallFragment callFragment;
        LayoutInflater inflater;
         OnItemClickListrner mlistrner ;

        public Exbandable_Invoice(Context context, List<ParentObject> parentItemList) {
            super(context, parentItemList);
            inflater=LayoutInflater.from(context);
        }

    public static  void setCallFragments(CallFragment callFragments) {
        callFragment = callFragments;
    }
    public  interface OnItemClickListrner
    {
        void OnItemClick(int position);

    }
    public  void SetOnItemClickListener(Exbandable_Invoice.OnItemClickListrner listrner)
    {
        mlistrner=listrner;
    }
        @Override
        public com.example.pbs.adapter.Exbandable_Invoice.CategoryViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
            View view=inflater.inflate(R.layout.sales_invoice_card,null);
            com.example.pbs.adapter.Exbandable_Invoice.CategoryViewHolder categoryViewHolder=new com.example.pbs.adapter.Exbandable_Invoice.CategoryViewHolder(view);
            return categoryViewHolder;
        }

        @Override
        public InvoiceViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
            View view=inflater.inflate(R.layout.card_invoice_type,null);
           InvoiceViewHolder invoice_ViewHolder=new InvoiceViewHolder(view,mlistrner);
            return invoice_ViewHolder;
        }

        @Override
        public void onBindParentViewHolder(com.example.pbs.adapter.Exbandable_Invoice.CategoryViewHolder categoryViewHolder, int i, Object o) {
            sales_invoice_model sales_invoice_models=(sales_invoice_model) o;
           // categoryViewHolder.invoice_title.setText(sales_invoice_models.getName());
            categoryViewHolder.invoice_image_view.setImageResource(sales_invoice_models.getImage());
            categoryViewHolder.invoice_date.setText(sales_invoice_models.getDates());
            categoryViewHolder.paid_amount.setText(sales_invoice_models.getPaidAmount());
            categoryViewHolder.indebt_amount.setText(sales_invoice_models.getIndebtAmount());

        }



    @Override
        public void onBindChildViewHolder(com.example.pbs.adapter.Exbandable_Invoice.InvoiceViewHolder invoice_ViewHolder, int i, Object o) {
            Invoice invoice =(Invoice) o;
            invoice_ViewHolder.return_image.setImageResource(invoice.getReturn_invoice_image());
            invoice_ViewHolder.Bonds_image.setImageResource(invoice.getBond_image());
            invoice_ViewHolder.details_image.setImageResource(invoice.getDetail_image());

        }

        class CategoryViewHolder extends ParentViewHolder {
            public TextView invoice_title,invoice_date,paid_amount,indebt_amount;
            public ImageView invoice_image_view;


            public CategoryViewHolder(View itemView) {
                super(itemView);
                invoice_title=itemView.findViewById(R.id.car_task);
                invoice_image_view=itemView.findViewById(R.id.card_image);
                invoice_date=itemView.findViewById(R.id.card_date);
                paid_amount=itemView.findViewById(R.id.card_invoice_paid_amount);
                indebt_amount=itemView.findViewById(R.id.card_invoice_indebt_amount);



            }
        }


        class InvoiceViewHolder extends ChildViewHolder implements View.OnClickListener {
            public AppCompatImageView return_image,Bonds_image,details_image;
            public InvoiceViewHolder(View itemView,final OnItemClickListrner listrner) {

                super(itemView);
                mlistrner=listrner;
                return_image=itemView.findViewById(R.id.invoice_return);
                return_image.setOnClickListener(this);
                Bonds_image=itemView.findViewById(R.id.invoice_bound);
                Bonds_image.setOnClickListener(this);
                details_image=itemView.findViewById(R.id.invoice_details);
                details_image.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                   int id=v.getId();
                   switch (id)
                   {
                       case R.id.invoice_return:
                           Toast.makeText(mContext,"return",Toast.LENGTH_LONG).show();
                           callFragment.call_fragment_method(new ReturnInvoice());

                           break;
                       case R.id.invoice_bound:
                               Toast.makeText(mContext,"Bond",Toast.LENGTH_LONG).show();
                                 callFragment.call_fragment_method(new PaidDialog());

                               break;
                       case R.id.invoice_details:
                              // Toast.makeText(mContext,"Detail",Toast.LENGTH_LONG).show();
                              callFragment.call_fragment_method(new Edit_Sales_Invoice());
                               break;
                   }
//                if (mlistrner != null) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        mlistrner.OnItemClick(position);
//
//
//                    }
//                }

            }
        }




}


