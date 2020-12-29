package com.example.pbs.LoginActivity;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pbs.utile.FormValidation;

import static com.example.pbs.utile.FormValidation.PASSWORD_PATTERN;

public class LoginViewModel extends AndroidViewModel {
       public static final String TAG="LoginViewModel";
    private LoinRepository loinRepository;
    public MutableLiveData<String> username=new MutableLiveData<>();
    public MutableLiveData<String> password=new MutableLiveData<>();
    LiveData<String> Client;
    User user=new User();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loinRepository=LoinRepository.getInstance(application);

    }

    public  void PostLogin(User user)
    {
        Log.i(TAG,user.getUsername()+"PostLogin");
          loinRepository.PostLogin(user);
        Client=loinRepository.getClient();
//        Log.e(TAG,"PostLoginVM"+Client);
    }

    public LiveData<String> getClient()
    {
        return Client;

    }
    public void validateLogin(User user)
    {     this.user=user;
        if (user.getUsername().isEmpty()) {
            this.username.setValue("Field can't be empty");

        } else if (user.getUsername().length()< 6) {
            this.username.setValue("Username less than 6 digit");

        } else {
            this.username.setValue("valid");
        }
        if (user.getPassword().isEmpty()) {
            this.password.setValue("Field can't be empty");

        } else if(user.getPassword().length()<6)
        {
            this.password.setValue("Password less than 6 digit");
        }
//        (!FormValidation.PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
//            this.password.setValue("Password too weak");

//        }
        else {
            this.password.setValue("valid");

        }

    }



}
