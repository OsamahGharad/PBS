package com.example.pbs.Modle;

public class ReturnInvoice_Items {


    private String Category,UnitOfMeasure,Quantity,ReturnReason;

    public ReturnInvoice_Items (String UnitOfMeasure,String Category, String Quantity,String ReturnReason) {
        this.UnitOfMeasure = UnitOfMeasure;
        this.Category = Category;
        this.Quantity = Quantity;
        this.ReturnReason=ReturnReason;
    }
    public String getR_UnitOfMeasure() {
        return UnitOfMeasure;
    }
    public void setV_UnitOfMeasure(String UnitOfMeasure) {
        this.UnitOfMeasure = UnitOfMeasure;
    }

    public String getR_category() {
        return Category;
    }
    public void setV_category(String Category) {
        this.Category = Category;
    }

    public String getR_Quantity() {
        return Quantity;
    }
    public void setV_Quantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getR_ReturnReason() {
        return ReturnReason;
    }
    public void setR_ReturnReason(String ReturnReason) {
        this.ReturnReason = ReturnReason;
    }


}
