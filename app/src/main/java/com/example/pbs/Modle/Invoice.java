package com.example.pbs.Modle;

import androidx.core.widget.ImageViewCompat;

public class Invoice {



   int return_invoice_image,Bond_image,detail_image;

    public Invoice(int return_invoice_image, int bond_image, int detail_image) {
        this.return_invoice_image = return_invoice_image;
        Bond_image = bond_image;
        this.detail_image = detail_image;
    }

    public int getReturn_invoice_image() {
        return return_invoice_image;
    }

    public void setReturn_invoice_image(int return_invoice_image) {
        this.return_invoice_image = return_invoice_image;
    }

    public int getBond_image() {
        return Bond_image;
    }

    public void setBond_image(int bond_image) {
        Bond_image = bond_image;
    }

    public int getDetail_image() {
        return detail_image;
    }

    public void setDetail_image(int detail_image) {
        this.detail_image = detail_image;
    }
}

