package com.example.pbs.Modle;

import android.media.Image;

public class sales_invoice {
   private String Card_paid,card_indept,Dates;
   float paid_amount,indept_amount;
    private int image;

          public sales_invoice(String dates, float paid_amount, float indept_amount, int image) {

              Dates = dates;
              this.paid_amount = paid_amount;
              this.indept_amount = indept_amount;
              this.Card_paid="Paid";
              this.card_indept="Indept";
              this.image=image;

          }



          public String getDates() {
              return Dates;
          }

          public float getPaid_amount() {
              return paid_amount;
          }

          public float getIndept_amount() {
              return indept_amount;
          }

    public int getImage() {
        return image;
    }
}
