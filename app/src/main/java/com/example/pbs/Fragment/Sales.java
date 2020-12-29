package com.example.pbs.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Sales extends Fragment implements PopupMenu.OnMenuItemClickListener {
    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
     static CallFragment callFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public void setCallFragments(CallFragment callFragment) {
        this.callFragment = callFragment;




    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_sales, null);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.sales_add);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_sales));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 show_popup_menu(v);
             }
         });
        return  view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        toolbar.inflateMenu(R.menu.mainactionbar);


    }

   public void show_popup_menu(View v)
   {
         PopupMenu popupMenu=new PopupMenu(getContext(),v);
         popupMenu.setOnMenuItemClickListener(this);
         popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
         popupMenu.show();
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.indebt_invoice)
        {
           callFragment.call_fragment_method(new Force_invoice());
        }
        return true;
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId())
        {
            /*case R.id.popup_paid:
                openDialog();
                //Toast.makeText(getActivity(),getString(R.string.paid),Toast.LENGTH_LONG).show();
                return true;*/
            case R.id.popup_invoice:
                callFragment.call_fragment_method(new CreateInvoice());

                return true;
            case R.id.popup_returnInvoice:
              //  Toast.makeText(getActivity(),getString(R.string.paid),Toast.LENGTH_LONG).show();
                callFragment.call_fragment_method(new CreateReturnInvoice());
                return true;
        }

        return true;
    }

    public void showCustomAlert()
    {
        LayoutInflater inflater = getLayoutInflater();
        View toastRoot = inflater.inflate(R.layout.toast_msg, null);
        Toast toast = new Toast(getActivity());
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM,
                0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }


    public void openDialog() {
        PaidDialog paidDialog = new PaidDialog();
        paidDialog.show(getFragmentManager(), "example dialog");
    }
}
