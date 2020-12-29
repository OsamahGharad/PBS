package com.example.pbs.Request;

import com.example.pbs.utile.Constants;
import com.example.pbs.utile.LiveDataCallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import  com.example.pbs.Request.BackOfficeApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.pbs.utile.Constants.CONNECTION_TIMEOUT;
import static com.example.pbs.utile.Constants.READ_TIMEOUT;
import static com.example.pbs.utile.Constants.WRITE_TIMEOUT;

public class ServiceGenerator {

    private static OkHttpClient client = new OkHttpClient.Builder()

            // establish connection to server
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte read from the server
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)

            // time between each byte sent to server
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)

            .retryOnConnectionFailure(false)

            .build();
//     Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
//                    .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                            .setLenient()
                            .create()));

    private static Retrofit retrofit = retrofitBuilder.build();
   private static BackOfficeApi backOfficeApi=retrofit.create(BackOfficeApi.class);

    public static BackOfficeApi backOfficeApi(){
        return backOfficeApi;
    }
}
