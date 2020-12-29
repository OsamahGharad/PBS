package com.example.pbs.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;

import com.example.pbs.R;
import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.Responses.ApiClient;
import com.example.pbs.adapter.TaskAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static androidx.constraintlayout.widget.Constraints.TAG;


public class Tasks extends Fragment {

    private TaskViewModel taskViewModel;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
    List<Task> examples;
    List<Task> taskList;
    BackOfficeApi apiInterface;
    String startDate ,endDate ,mTaskImg ,mAreaName,taskImg;
    String salesTarget ,lat ,lng;
    private ProgressDialog pDialog;
    Toolbar toolbar;
    static CallFragment callFragment;

    public Tasks(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {super.onAttach(context);
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* examples=new ArrayList<>();
        examples.add(new Task("12/4/2020","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task("12/4/2020","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task("12/4/2020","12/4/2020",R.drawable.more_vert_black));
        examples.add(new Task("12/4/2020","12/4/2020",R.drawable.more_vert_black));*/
    }


    public void setCallFragments(CallFragment callFragment) {
        this.callFragment = callFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_tasks, null);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_tasks));
        toolbar.setNavigationIcon(R.drawable.arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                hideLoading();
            }
        });

        taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);

      //  getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        taskList = new ArrayList<>();
        recyclerView=view.findViewById(R.id.task_recycleView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
       /* taskAdapter=new TaskAdapter((ArrayList<Task>) examples);
        recyclerView.setAdapter(taskAdapter);*/

         loadTasks();
        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", "your message");
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
       // TaskPlan.recieveTaskPlan("h","l","ooo");
        return  view;

    }

    //-----------load all tasks--------
    private void loadTasks() {

        apiInterface= ApiClient.getClient().create(BackOfficeApi.class);
        showLoading();
        apiInterface.getTasks().enqueue(new Callback<TaskDetails_JsonArray>() {
            @Override
            public void onResponse(Call<TaskDetails_JsonArray> call, Response<TaskDetails_JsonArray> response) {
                if(response.isSuccessful()){
                    hideLoading();
                     taskList=response.body().getTask_details();
                         for(int i=0;i<taskList.size();i++){
                            taskAdapter=new TaskAdapter((ArrayList<Task>)taskList);
                            recyclerView.setAdapter(taskAdapter);
                            taskAdapter.notifyDataSetChanged();

                             showTaskOptions();
                        }
                }
            }
            @Override
            public void onFailure(Call<TaskDetails_JsonArray> call, Throwable t) {

                Log.w(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    //-----------show tasks options--------
    public  void showTaskOptions(){
        taskAdapter.SetOnItemClickListener(new TaskAdapter.OnItemClickListrner() {
            @Override
            public void OnItemClick(int position) {

                taskViewModel.setStartDate(taskList.get(position).getmStartDates());
                taskViewModel.setEndDate(taskList.get(position).getmEndDates());
                taskViewModel.setAreaName(taskList.get(position).getAreaName_Ar());
                taskViewModel.setSalesTarget(taskList.get(position).getSalesTarget());
                taskViewModel.setLat(taskList.get(position).getLat());
                taskViewModel.setLng(taskList.get(position).getLng());
                taskViewModel.setImage(taskList.get(position).getTask_img());

                //move into task details
                callFragment.call_fragment_method(new TaskNumber());

            }
        });
    }

    //-----------show dialog while loading data from the server--------
   public void showLoading(){

       pDialog = new ProgressDialog(getActivity());
       pDialog.setMessage(getString(R.string.pls_wait));
       pDialog.setIndeterminate(false);
       pDialog.setCancelable(false);
       pDialog.show();

   }

    //-----------hide dialog while loading data from the server--------

   public void hideLoading(){
       pDialog.dismiss();
   }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
           getActivity().getMenuInflater().inflate(R.menu.mainactionbar, menu);
           MenuItem item =menu.findItem(R.id.indebt_invoice);
           item.setVisible(false);


    }






}
