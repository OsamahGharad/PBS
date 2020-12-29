package com.example.pbs.Fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Activity.SplashScreen;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;
import com.example.pbs.utile.LanguageUtil;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class Setting extends Fragment {
    static  CallFragment callFragment;
    LinearLayoutCompat Profile,Help,Notification,Backup,Language,About;
    TextView about_app;
    Toolbar toolbar;
    LanguageUtil language;

    public void setCallFragments(CallFragment callFragment)
    {
        this.callFragment = callFragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadLocal();
        View view= inflater.inflate(R.layout.fragment_, container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_setting));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        Profile=view.findViewById(R.id.linear_profile);
        About=view.findViewById(R.id.about_LinearLayout);
        Help=view.findViewById(R.id.help_LinearLayout);
        Notification=view.findViewById(R.id.notification_LinearLayout);
        Backup=view.findViewById(R.id.backup_LinearLayout);
        Language=view.findViewById(R.id.language_LinearLayout);



        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment.call_fragment_method(new Profiles());
            }
        });

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment.call_fragment_method(new Notification());
            }
        });


        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
                alert.setMessage("Performance Booster version 1.0")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        Language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeLanguageDialog();
            }
        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });


        return  view;
    }

    public void showChangeLanguageDialog(){
        final String [] list_items={"English","العربية"};
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        // AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
        alert.setTitle("choose language");
        alert.setSingleChoiceItems(list_items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocal("en");
                    Intent dsp = new Intent(getContext(),SplashScreen.class);
                    startActivity(dsp);

                }
                else if(i==1){

                    setLocal("ar");
                    Intent dsp = new Intent(getContext(),SplashScreen.class);
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
        ;Configuration configuration=getResources().getConfiguration();
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



    private void sendMail() {


        String[] recipientList={"mailto@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipientList);
        intent.putExtra(Intent.EXTRA_SUBJECT, "العنوان");
        intent.putExtra(Intent.EXTRA_TEXT, "الرساله");
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }


}
