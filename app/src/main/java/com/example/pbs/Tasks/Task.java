package com.example.pbs.Tasks;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task  {
    @SerializedName("Task_Start_date")
    @Expose
    private String Task_Start_date;
    @SerializedName("Task_End_date")
    @Expose
    private String Task_End_date;
    @SerializedName("Task_sales_target")
    @Expose
    private String salesTarget;
    @SerializedName("Task_img")
    @Expose
    private String Task_img;
    @SerializedName("AreaName_Eng")
    @Expose
    private String AreaName_Eng;
    @SerializedName("AreaName_Ar")
    @Expose
    private String AreaName_Ar;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lng")
    @Expose
    private String lng;

    private int image;
    private String mNumber;

    public Task(String StartDates, String endDates,int img) {
      //  mNumber=num;
        Task_Start_date = StartDates;
        Task_End_date = endDates;
        image=img;

    }

    public Task(String StartDates, String endDates) {
        //  mNumber=num;
        Task_Start_date = StartDates;
        Task_End_date = endDates;


    }

    public Task(String StartDates, String endDates,String Area,String saleTarget , String mLat,String mLng,String TaskImage) {
        //  mNumber=num;
        Task_Start_date = StartDates;
        Task_End_date = endDates;
        AreaName_Ar=Area;
        salesTarget=saleTarget;
        lat=mLat;
        lng=mLng;
        Task_img=TaskImage;



    }

    public String getmStartDates() {
        return Task_Start_date;
    }

    public String getmEndDates() {
        return Task_End_date;
    }

    public int getImage() {
        return image;
    }

    public String getSalesTarget() {
        return salesTarget;
    }

    public String getTask_img() {
        return Task_img;
    }

    public String getAreaName_Eng() {
        return AreaName_Eng;
    }

    public String getAreaName_Ar() {
        return AreaName_Ar;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void setmStartDates(String mStartDates) {
        this.Task_Start_date = mStartDates;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setmEndDates(String mEndDates) {
        this.Task_End_date = mEndDates;
    }

    public void setSalesTarget(String salesTarget) {
        this.salesTarget = salesTarget;
    }

    public void setTask_img(String task_img) {
        Task_img = task_img;
    }

    public void setAreaName_Eng(String areaName_Eng) {
        AreaName_Eng = areaName_Eng;
    }

    public void setAreaName_Ar(String areaName_Ar) {
        AreaName_Ar = areaName_Ar;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Task_Start_date='" + Task_Start_date + '\'' +
                ", Task_End_date='" + Task_End_date + '\'' +
                ", salesTarget='" + salesTarget + '\'' +
                ", Task_img='" + Task_img + '\'' +
                ", AreaName_Eng='" + AreaName_Eng + '\'' +
                ", AreaName_Ar='" + AreaName_Ar + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +

                '}';
    }





}
