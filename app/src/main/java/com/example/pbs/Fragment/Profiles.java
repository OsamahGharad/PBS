package com.example.pbs.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.Interface.CallFragment;
import com.example.pbs.R;

public class Profiles extends Fragment {
    static  CallFragment callFragment;
    Button editProfile;
    TextView about_app;
    Toolbar toolbar;
    public void setCallFragments(CallFragment callFragment)
    {
        this.callFragment = callFragment;
    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.show_profile, container,false);
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        toolbar.setTitle(getString(R.string.menu_setting));
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();


            }
        });

        editProfile=view.findViewById(R.id.edit_profile_btn);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callFragment.call_fragment_method(new EditProfile());
//                FragmentTransaction fr=getFragmentManager().beginTransaction();
//                fr.replace(R.id.fragment_container,new EditProfile());
//
//                fr.commit();
            }
        });
        return  view;
    }

}
