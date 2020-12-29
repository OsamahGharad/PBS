package com.example.pbs.Modle;

public class ProductItems{

    private String Product,price;

    public ProductItems(String Product,String price) {
        this.Product = Product;
        this.price = price;

    }
    public String getP_product() {
        return Product;
    }
    public void setP_product(String Product) {
        this.Product = Product;
    }

    public String getP_price() {
        return price;
    }
    public void setP_price(String price) {
        this.price = price;
    }


}