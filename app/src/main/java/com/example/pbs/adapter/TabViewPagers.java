package com.example.pbs.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pbs.Fragment.ReturnInvoice;
import com.example.pbs.Fragment.salesInvoice;


public class TabViewPagers extends FragmentPagerAdapter {
    int tapCount;
    public TabViewPagers(FragmentManager fm , int tapCount) {
        super(fm);
        this.tapCount=tapCount;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
              fragment =salesInvoice.getInstance();
                return fragment;
            case 1:
               fragment =ReturnInvoice.getInstance();
                return  fragment;

            default:
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return tapCount;
    }



}
