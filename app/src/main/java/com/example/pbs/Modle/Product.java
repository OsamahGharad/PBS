package com.example.pbs.Modle;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

public class Product {
    String prodct1,product2;

    public Product(String prodct1, String product2) {
        this.prodct1 = prodct1;
        this.product2 = product2;
    }

    public String getProdct1() {
        return prodct1;
    }

    public void setProdct1(String prodct1) {
        this.prodct1 = prodct1;
    }

    public String getProduct2() {
        return product2;
    }

    public void setProduct2(String product2) {
        this.product2 = product2;
    }
}
