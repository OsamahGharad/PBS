package com.example.pbs.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;

import com.example.pbs.adapter.TabViewPagers;
import com.google.android.material.tabs.TabLayout;

public class Invoice extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;


   Toolbar toolbar;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_invoice, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_invoice));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });
        tabLayout=view.findViewById(R.id.invoice_tablayout);
        viewPager=view.findViewById(R.id.invoice_viewpager);


        return  view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        toolbar.inflateMenu(R.menu.mainactionbar);
    }

    @Override

    public void onResume() {
        super.onResume();
        TabViewPagers Adapter =new TabViewPagers(getChildFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(Adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}
