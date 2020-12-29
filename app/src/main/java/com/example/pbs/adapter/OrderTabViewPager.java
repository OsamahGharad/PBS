package com.example.pbs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pbs.Orders.AddOrder;
import com.example.pbs.Orders.OrderData;


public class OrderTabViewPager extends FragmentPagerAdapter {
    int tapCount;
    public OrderTabViewPager(FragmentManager fm , int tapCount) {
        super(fm);
        this.tapCount=tapCount;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
               fragment=new OrderData();
                return fragment;
            case 1:
               fragment=new AddOrder();
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
