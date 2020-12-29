package com.example.pbs.Modle;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

public class sales_invoice_model implements ParentObject {
    List<Object> InvoiceList;
    private String Name,Dates,Paid_amount,Indebt_amount;
    private int image;

    public sales_invoice_model(String name, String dates, int mimageResource) {
        Name = name;
        Dates = dates;
        image = mimageResource;
    }

    public sales_invoice_model(String dates,String PaidAmount,String IndebtAmount, int mimageResource) {
        Dates = dates;
        Paid_amount = PaidAmount;
        Indebt_amount = IndebtAmount;
        image = mimageResource;
    }

    public String getName() {
        return Name;
    }

    public String getDates() {
        return Dates;
    }

    public String getPaidAmount() {
        return Paid_amount;
    }

    public String getIndebtAmount() {
        return Indebt_amount;
    }

    public int getImage() {
        return image;
    }

    public List<Object> getProductsList() {
        return InvoiceList;
    }
    @Override
    public List<Object> getChildObjectList() {
        return InvoiceList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        InvoiceList=list;
    }
  }
