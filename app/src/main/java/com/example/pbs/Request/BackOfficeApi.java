package com.example.pbs.Request;

import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;
import com.example.pbs.LoginActivity.User;
import com.example.pbs.Customers.Customer_Entity;
import com.example.pbs.LoginActivity.AccessToken;
import com.example.pbs.Request.Responses.CustomerResponse;
import com.example.pbs.Tasks.TaskDetails_JsonArray;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BackOfficeApi {

//    @Headers({"",""})
   @POST("LocationRetrival")
    Call<String> sendCurrentLocation(@Body CurrentLocationCoordinate currentLocationCoordinate);



@POST("login")
@FormUrlEncoded
//@Headers("Accept:application/json")
Call<AccessToken> login(@Field("username") String username, @Field("password") String password);

//    @POST("getCurrentLocation")
//    LiveData<ApiResponse<LocationResponse>> sendCurrentLocation(@Body CurrentLocationCoordinate currentLocationCoordinate);
@POST("add_cus")
Call<CustomerResponse> add_cus(@Body  Customer_Entity customer_entity);
    @POST("refresh")
    @FormUrlEncoded
    Call<AccessToken> refresh(@Field("refresh_token") String refreshToken);

    @GET("sp_task")
    Call<TaskDetails_JsonArray> getTasks();

}
