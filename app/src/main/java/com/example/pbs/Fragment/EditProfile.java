package com.example.pbs.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class EditProfile extends Fragment {

    Toolbar toolbar;
    ImageView imageProfile;
    FloatingActionButton floatingActionButton;
    private  static  final int Pick_image=1;
    Uri image_uri;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_edit_profile,null);
        imageProfile=view.findViewById(R.id.profile_image);
        floatingActionButton=view.findViewById(R.id.edit_profile_fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_gallery_image();
            }
        });
        toolbar=((MainActivity)this.getActivity()).getToolbar();
        ((AppCompatActivity)(getActivity())).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return view;
    }
    private void get_gallery_image() {
        Intent gallary=new Intent();
        gallary.setType("image/*");
        gallary.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallary,"select_picture"),Pick_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Pick_image && resultCode==RESULT_OK)
        {
            image_uri=data.getData();
            try {

                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),image_uri);
                imageProfile.setImageBitmap(bitmap);

            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }


    }
}
