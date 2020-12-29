package com.example.pbs.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Modle.GoodItems;
import com.example.pbs.R;
import com.example.pbs.adapter.GoodsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Good extends Fragment {
    Toolbar toolbar;
    private RecyclerView recyclerView;
    private GoodsAdapter adapter;
    private ArrayList<GoodItems> mGoodsList;
    private RequestQueue mRequestQueue;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_good, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_goods));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });



        int CurrentQty = 1;
        int remainQty=767;
        String CurrentQtyString = Integer.toString(CurrentQty );
        String remainQtyString = Integer.toString(remainQty);

        GoodItems[] myListData = new GoodItems[] {
                new GoodItems("Nedo", "Milk",CurrentQtyString,remainQtyString ),
                new GoodItems("Galaxy", "Chocolate" ,CurrentQtyString,remainQtyString ),
                new GoodItems("SunTop", "Juce" ,CurrentQtyString,remainQtyString),
        };

        recyclerView = (RecyclerView) view.findViewById(R.id.good_recycleView);
        adapter = new GoodsAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

       /* mRequestQueue= Volley.newRequestQueue(getActivity());
        parseJson();*/
        return  view;
    }

    public void parseJson(){
        String url="http://192.168.43.75:8080/FinalBackOffice/public/api/fetch_product";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray=response.getJSONArray("products");

                            for(int i=0; i<jsonArray.length();i++){
                                JSONObject product=jsonArray.getJSONObject(i);

                                String productName=product.getString("Product_name");
                                String Category=product.getString("Product_Description");
                                String currentQty="7567";
                                String remainQty="575658";

                                //   mGoodsList.add(new GoodItems(productName,Category,currentQty,remainQty));

                                GoodItems[] myListData = new GoodItems[] {
                                        new GoodItems(productName,Category,currentQty,remainQty ),
                                };


                            }

                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
