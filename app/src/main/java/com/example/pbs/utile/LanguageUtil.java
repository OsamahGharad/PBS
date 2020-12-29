package com.example.pbs.utile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.SplashScreen;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class LanguageUtil extends Fragment {

    public   void showChangeLanguageDialog(){
        final String [] list_items={"English","العربية"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        // AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
        alert.setTitle("choose language");
        alert.setSingleChoiceItems(list_items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocal("en");
                    Intent dsp = new Intent(getContext(), SplashScreen.class);
                    startActivity(dsp);

                }
                else if(i==1){

                    setLocal("ar");
                    Intent dsp = new Intent(getContext(), SplashScreen.class);
                    startActivity(dsp);



                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();


    }

    public void setLocal(String lang) {
        Locale local=new Locale(lang);
        Locale.setDefault(local);
        // Configuration configuration=new Configuration();
        ;
        Configuration configuration=getResources().getConfiguration();
        configuration.locale=local;
        configuration.setLayoutDirection(new Locale(lang));
        getContext().getResources().updateConfiguration(configuration,getContext().getResources().getDisplayMetrics());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(local);
            getContext().createConfigurationContext(configuration);
        }
        SharedPreferences.Editor editor=getActivity().getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("MY_lang",lang);
        editor.apply();



    }

    public void loadLocal(){
        SharedPreferences prefs=getActivity().getSharedPreferences("Settings", MODE_PRIVATE);
        String language=prefs.getString("MY_lang","");
        Locale newlocale=new Locale(language);
        setLocal(language);

        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(newlocale);
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }



    }

}
