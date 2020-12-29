package com.example.pbs.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.pbs.R;

public class PaidDialog extends AppCompatDialogFragment {

    private EditText editTextInvoiceNO,editTextPaidAmount,editTextRemainAmount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_paid_dialog, null);

        builder.setView(view)
                .setTitle(getString(R.string.paid))
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String invoiceNo = editTextInvoiceNO.getText().toString();
                        String amount = editTextPaidAmount.getText().toString();
                        String remaining_amount =editTextRemainAmount.getText().toString();
                    }
                });

        editTextInvoiceNO = view.findViewById(R.id.Dialog_PaidInvoiceNo);
        editTextPaidAmount = view.findViewById(R.id.Dialog_Paid_Amount);
        editTextRemainAmount = view.findViewById(R.id.Dialog_Remain_Amount);

        return builder.create();
    }


}
