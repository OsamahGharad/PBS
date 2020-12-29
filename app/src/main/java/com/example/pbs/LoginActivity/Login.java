package com.example.pbs.LoginActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;
import com.example.pbs.Request.BackOfficeApi;
import com.example.pbs.Request.RetrofitBuilder;
import com.example.pbs.Request.ServiceGenerator;
import com.example.pbs.LoginActivity.AccessToken;
import com.example.pbs.utile.FormValidation;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    BackOfficeApi backOfficeApi;
    private static final String TAG = "login";
    Button button;
    CardView login_card;
    TextInputLayout username, password;
    LoginViewModel loginViewModel;
    ProgressDialog pDialog;
    User user;
    User user1;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        login_card = findViewById(R.id.login_card);
        username = login_card.findViewById(R.id.user_name);
        password = login_card.findViewById(R.id.password);
        button = findViewById(R.id.login);
        user = new User();

        Toast.makeText(this,"login",Toast.LENGTH_LONG).show();
        Log.i(TAG, "loginPostLogin");
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        backOfficeApi= RetrofitBuilder.createService(BackOfficeApi.class);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               /* user.setUsername(username.getEditText().getText().toString().trim());
                user.setPassword(password.getEditText().getText().toString().trim());

                      loginViewModel.validateLogin(user);
                   if (loginViewModel.username.getValue() == "valid" && loginViewModel.password.getValue() == "valid") {
                       pDialog = new ProgressDialog(Login.this);
                       pDialog.setMessage("Validate Login form");
                       pDialog.setIndeterminate(false);
                       pDialog.setCancelable(false);
//                       post_login();
                    loginViewModel.PostLogin(user);
                       if(tokenManager.getToken().accessToken!=null)
                       {
                           pDialog.dismiss();
                           moveToMainActivity();
                       }


                    } else {
                //    pDialog.dismiss();
                    username.setError(loginViewModel.username.getValue().toString());
                    password.setError(loginViewModel.password.getValue().toString());
                           }*/
                moveToMainActivity();
            }
        });
    }

    private void post_login()
    {

      String username="omerali";
      String password="123456";

        Call<AccessToken> loginResponse=backOfficeApi.login(username,password);
        Log.i(TAG,loginResponse+" Call");
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




    @Override
    protected void onStart() {
        super.onStart();
        checkToken();
//      checkSession();
    }

    private void checkToken() {
        if(tokenManager.getToken().accessToken!=null)
        {

            moveToMainActivity();
        }
        else{
            Toast.makeText(Login.this,tokenManager.getToken().accessToken+"null", Toast.LENGTH_LONG).show();

        }
    }

//    private void checkSession() {
//        //check if user is logged in
//        //if user is logged in --> move to mainActivity
//
//        SessionManagment sessionManagement = new SessionManagment(Login.this);
//        int userID = sessionManagement.getSession();
//
//        if (userID != -1) {
//            //user id logged in and so move to mainActivity
//            moveToMainActivity();
//        } else {
//            //do nothing
//        }
//    }

    private void moveToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


}


