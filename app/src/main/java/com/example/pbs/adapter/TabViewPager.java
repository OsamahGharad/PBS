package com.example.pbs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pbs.Customers.Add_customer;
import com.example.pbs.Customers.CustomerData;

public class TabViewPager extends FragmentPagerAdapter {
    int tapCount;
    public TabViewPager(FragmentManager fm ,int tapCount) {
        super(fm);
        this.tapCount=tapCount;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
               fragment=new CustomerData();
                return fragment;
            case 1:
               fragment=new Add_customer();
                return fragment;

            default:
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return tapCount;
    }
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        String tab=new String();
//        if(position==0)
//            return "Customer Data";
//        if(position==1)
//
//            return "Add Customer";
//        return  null;
//    }
}
