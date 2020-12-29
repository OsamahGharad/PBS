package com.example.pbs.Orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;
import com.example.pbs.adapter.OrderTabViewPager;
import com.google.android.material.tabs.TabLayout;

public class Order  extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.order, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.nav_order));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        tabLayout=view.findViewById(R.id.order_tablayout);
        viewPager=view.findViewById(R.id.order_viewpager);
        OrderTabViewPager Adapter =new OrderTabViewPager(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(Adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));


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
