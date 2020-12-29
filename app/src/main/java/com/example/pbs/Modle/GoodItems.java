package com.example.pbs.Modle;

public class GoodItems{

    private String Product,Category,CurrentQty,RemainQty;

    public GoodItems(String Product,String Category, String CurrentQty,String RemainQty) {
        this.Product = Product;
        this.Category = Category;
        this.CurrentQty = CurrentQty;
        this.RemainQty=RemainQty;
    }
    public String getG_product() {
        return Product;
    }
    public void setG_product(String Product) {
        this.Product = Product;
    }

    public String getG_category() {
        return Category;
    }
    public void setG_category(String Category) {
        this.Category = Category;
    }

    public String getCurrentQty() {
        return CurrentQty;
    }
    public void setCurrentQty(String CurrentQty) {
        this.CurrentQty = CurrentQty;
    }


    public String getG_RemainQty() {
        return RemainQty;
    }
    public void setG_RemainQty(String RemainQty) {
        this.RemainQty = RemainQty;
    }


}