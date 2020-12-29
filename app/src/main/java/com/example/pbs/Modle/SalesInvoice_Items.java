package com.example.pbs.Modle;

public class SalesInvoice_Items {


    private String Category,UnitOfMeasure,Quantity,UnitPrice,Tax,Discount;

    public SalesInvoice_Items(String UnitOfMeasure,String Category, String Quantity,String UnitPrice,String Tax,String Discount) {
        this.UnitOfMeasure = UnitOfMeasure;
        this.Category = Category;
        this.Quantity = Quantity;
        this.UnitPrice=UnitPrice;
        this.Tax=Tax;
        this.Discount=Discount;
    }
    public String getV_UnitOfMeasure() {
        return UnitOfMeasure;
    }
    public void setV_UnitOfMeasure(String UnitOfMeasure) {
        this.UnitOfMeasure = UnitOfMeasure;
    }

    public String getV_category() {
        return Category;
    }
    public void setV_category(String Category) {
        this.Category = Category;
    }

    public String getV_Quantity() {
        return Quantity;
    }
    public void setV_Quantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getV_UnitPrice() {
        return UnitPrice;
    }
    public void setV_UnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public String getV_Tax() {
        return Tax;
    }
    public void setV_Tax(String Tax) {
        this.Tax = Tax;
    }

    public String getV_Discount() {
        return Discount;
    }
    public void setV_Discount(String Discount) {
        this.Discount = Discount;
    }
}
