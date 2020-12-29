package com.example.pbs.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Modle.Product;
import com.example.pbs.Modle.ProductItems;
import com.example.pbs.R;
import com.example.pbs.adapter.Exbandable_Category;
import com.example.pbs.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

public class Products extends Fragment {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Exbandable_Category exbandable_category;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_products, container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_product));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();

            }
        });

        recyclerView=view.findViewById(R.id.expandable_categoeies_list);
        layoutManager=new LinearLayoutManager(getContext());
        set_recycle_adapter();

        return  view;
    }

    private void set_recycle_adapter() {
        recyclerView.setLayoutManager(layoutManager);
        exbandable_category=new Exbandable_Category(getContext(),intiData());
        recyclerView.setAdapter(exbandable_category);
        exbandable_category.setParentClickableViewAnimationDefaultDuration();
        exbandable_category.setParentAndIconExpandOnClick(true);
    }

    public List<ParentObject> intiData()
    {    List<ParentObject> parentObjectList=new ArrayList<>();
        List<Categories> list=new ArrayList<>();
        for(int i=0;i<10;i++) {
            list.add(new Categories("Milk"));
        }
        for(Categories categories:list)
        {
            List<Object> childObject =new ArrayList<>();
            childObject.add( new  ProductItems("Nedo", "22222" ));
            categories.setChildObjectList(childObject);
            parentObjectList.add(categories);
        }

        return parentObjectList;
    }

}
