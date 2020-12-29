package com.example.pbs.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;

public class Return_Invoice_add_Items extends Fragment {


    Toolbar toolbar;
    Spinner product_spinner,category_spinner,unit_of_measures_spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.create_return_invoice_add_items, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.invoiceData));
        toolbar.setNavigationIcon(R.drawable.arrow_navigation);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

               /* FragmentTransaction fr=getFragmentManager().beginTransaction();
               // fr.replace(R.id.fragment_container,new Invoice_Add_Items());
                fr.remove(new Invoice_Add_Items());
                fr.commit();*/
            }
        });

        category_spinner=view.findViewById(R.id.Add_R_category_spinner);
        product_spinner=view.findViewById(R.id.Add_R_product_spinner);
        unit_of_measures_spinner=view.findViewById(R.id.Add_R_UnitOfMeasure_spinner);

        ArrayAdapter category_adapter =ArrayAdapter.createFromResource(getActivity(),R.array.category,android.R.layout.simple_spinner_item);
        category_spinner.setAdapter(category_adapter);

        ArrayAdapter product_adapter =ArrayAdapter.createFromResource(getActivity(),R.array.product,android.R.layout.simple_spinner_item);
        product_spinner.setAdapter(product_adapter);

        ArrayAdapter unit_of_measure_adapter =ArrayAdapter.createFromResource(getActivity(),R.array.unit_of_measures,android.R.layout.simple_spinner_item);
        unit_of_measures_spinner.setAdapter(unit_of_measure_adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();


    }
}
