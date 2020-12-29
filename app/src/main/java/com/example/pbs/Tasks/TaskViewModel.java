package com.example.pbs.Tasks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class TaskViewModel  extends AndroidViewModel {

    TaskRepository taskRepository;


    private final MutableLiveData startDate = new MutableLiveData();
    private final MutableLiveData endDate = new MutableLiveData();
    private final MutableLiveData areaName = new MutableLiveData();
    private final MutableLiveData salesTarget = new MutableLiveData();
    private final MutableLiveData lat = new MutableLiveData();
    private final MutableLiveData lng = new MutableLiveData();
    private final MutableLiveData img = new MutableLiveData();

    public MutableLiveData<Task> task_entityMutableLiveData=new MutableLiveData<>();

    public TaskViewModel(@NonNull Application application) {
        super(application);

        taskRepository=TaskRepository.getInstance(application);
    }

    //Getter
    public MutableLiveData getStartDate() {
        return startDate;
    }
    public MutableLiveData getEndDate() {
        return endDate;
    }
    public MutableLiveData getAreaName() {
        return areaName;
    }
    public MutableLiveData getSalesTarget() {
        return salesTarget;
    }
    public MutableLiveData getLat() {
        return lat;
    }
    public MutableLiveData getLng() {
        return lng;
    }
    public MutableLiveData getImg() {
        return img;
    }

    //Setter
    public void setSalesTarget(String sTarget){
        salesTarget.setValue(sTarget);
    }
    public void setStartDate(String sDate){
        startDate.setValue(sDate);
    }
    public void setEndDate(String eDate){
        endDate.setValue(eDate);
    }
    public void setAreaName(String area){
        areaName.setValue(area);
    }
    public void setLat(String mLat){
        lat.setValue(mLat);
    }
    public void setLng(String mLng){
        lng.setValue(mLng);
    }
    public void setImage(String mImg){
        img.setValue(mImg);
    }

}
