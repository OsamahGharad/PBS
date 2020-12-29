package com.example.pbs.Orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddOrder extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    FloatingActionButton add_items;
    static CallFragment callFragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Nullable

    @Override
    public void onResume() {
        super.onResume();
      //  getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void SetFragments(CallFragment callFragment) {
        this.callFragment = callFragment;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.add_order,container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getClass().getSimpleName());
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        //-----------handel click event-----------
        add_items=view.findViewById(R.id.add_more_order_items);
        add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment.call_fragment_method(new AddOrderItems());
            }
        });




        return view;
    }

    public void setCallFragments(CallFragment callFragment)
    {
        this.callFragment = callFragment;
    }
    @Override
    public void onPause() {
        super.onPause();
       // getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
