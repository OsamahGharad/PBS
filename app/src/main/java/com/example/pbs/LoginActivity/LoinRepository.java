package com.example.pbs.LoginActivity;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.RetrofitBuilder;
import com.example.pbs.Request.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class LoinRepository {
     User user=new User();
    public BackOfficeApi backOfficeApi;
     private static final String TAG="LoinRepository";
    TokenManager tokenManager;
     MutableLiveData<String> Client=new MutableLiveData<>();
    private static    LoinRepository ourInstance ;
    public static LoinRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LoinRepository(context.getApplicationContext());
        }

        return ourInstance;
    }

    public LoinRepository(Context applicationContext) {
        tokenManager = TokenManager.getInstance(applicationContext.getSharedPreferences("prefs", MODE_PRIVATE));
        backOfficeApi= RetrofitBuilder.createService(BackOfficeApi.class);
    }
    public LiveData<String> getClient()
    {
        return Client;

    }
    public void PostLogin(User user)
    {      Log.i(TAG,"Call");
        Log.i(TAG,user.getUsername()+"RepositoryPostLogin");


        Call<AccessToken> loginResponse=backOfficeApi.login(user.username,user.password);
        Log.i(TAG," Call");
        loginResponse.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response)
            {

                if(response.isSuccessful())
                {
                    Log.i(TAG,response.body().accessToken+"successfule");
                    tokenManager.saveToken(response.body());
                }

                else
                {
                    Log.i(TAG,response.code()+""+"notsuccessfule");

                }
//                Client.setValue("response");
//                if (BuildConfig.DEBUG) {
//                Log.e(TAG,new Gson().toJson(response.body())+"successfule");
//                }
//                Log.i(TAG,response.body().getAccessToken());



            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.i(TAG, "failed connection");
                Log.i(TAG, t.getMessage());

            }





        });

    }

//    {
//
//        Log.i(TAG,user.getUsername()+"RepositoryPostLogin");
//
//
//        Call<AccessToken> loginResponse=backOfficeApi.login("","");
//        Log.i(TAG,loginResponse+" Call");
//        loginResponse.enqueue(new Callback<AccessToken>() {
//            @Override
//            public void onResponse(Call<AccessToken> call, Response<AccessToken> response)
//            {
//                Log.i(TAG,"successfule");
//                if(response.isSuccessful())
//                {
//                    Log.i(TAG,"successfule");
//                    tokenManager.saveToken(response.body());
//                }
//                else
//                    {
//                        Log.i(TAG,"notsuccessfule");
//                        try {
//                            Log.i(TAG,response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
////                Client.setValue("response");
////                if (BuildConfig.DEBUG) {
////                Log.e(TAG,new Gson().toJson(response.body())+"successfule");
////                }
////                Log.i(TAG,response.body().getAccessToken());
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<AccessToken> call, Throwable t) {
//                Log.i(TAG, "failed connection");
//                Log.i(TAG, t.getMessage());
//
//            }
//
//
//
//
//
//        });
//
//    }

}
