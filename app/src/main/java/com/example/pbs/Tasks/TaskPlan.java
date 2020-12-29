package com.example.pbs.Tasks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;

import static android.content.Context.MODE_PRIVATE;

public class TaskPlan extends Fragment {
    private TaskViewModel taskViewModel;
    Toolbar toolbar;
    LinearLayout linearLayout;
     TextView mStartDate,mEndDate,mSalesTarget,mAreaName,mLat,mLng;
    ImageView mTaskImg;

    public TaskPlan(){ }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view= inflater.inflate(R.layout.fragment_task_plan, null);
            toolbar=((MainActivity)this.getActivity()).getToolbar();
            toolbar=((MainActivity)this.getActivity()).getToolbar();
            toolbar.setTitle(getString(R.string.movement_planing));
            toolbar.setNavigationIcon(R.drawable.arrow_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { getActivity().onBackPressed(); }
            });

            final TaskViewModel taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);
            mStartDate=(TextView) view.findViewById(R.id.task_start_date);
            mEndDate=(TextView)view.findViewById(R.id.task_end_date);
            mSalesTarget=(TextView)view.findViewById(R.id.task_sales_target);
            mTaskImg=view.findViewById(R.id.task_image);
            mAreaName=(TextView)view.findViewById(R.id.task_area);

        taskViewModel.getAreaName().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {
                mAreaName.setText(o.toString());
            }
        });

        taskViewModel.getStartDate().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                mStartDate.setText(o.toString());
            }
        });

        taskViewModel.getEndDate().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                mEndDate.setText(o.toString());
            }
        });

        taskViewModel.getSalesTarget().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {
                mSalesTarget.setText(o.toString());
            }
        });

       taskViewModel.getImg().observe(this, new Observer() {
            @Override
            public void onChanged(Object o) {

               int  img_source=Integer.parseInt(o.toString());
                mTaskImg.setImageResource(img_source);
               // mLat.setText(o.toString());
            }
        });
            return  view;
        }




}

