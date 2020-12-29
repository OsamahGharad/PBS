package com.example.pbs.CurrentLocation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pbs.Activity.MainActivity;
import com.example.pbs.CurrentLocation.models.CurrentLocationCoordinate;
import com.example.pbs.CurrentLocation.models.PlaceInfo;
import com.example.pbs.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import static android.app.Activity.RESULT_OK;

public class Map_fragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener{
    private static final String TAG = "Map_fragment";
    private static final float DEFAULT_ZOOM = 15f;
    private CurrentLocationService currentLocationService;
    private CurrentLocationViewModel currentLocationViewModel;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Marker marker;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private PlaceInfo mPlace;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    private static final int PLACE_PICKER_REQUEST = 1;

    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps,mInfo, mPlacePicker;
    Toolbar toolbar;
    Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate: ");
        Toast.makeText(getActivity(),"onCreate: ",Toast.LENGTH_LONG).show();
        currentLocationViewModel=ViewModelProviders.of(getActivity()).get(CurrentLocationViewModel.class);

    }

    private void checkLocationavailable() {

        if(currentLocationViewModel.getLocationLiveData().getValue()==null)
        {          Toast.makeText(getActivity(),currentLocationViewModel.getLocationLiveData().getValue()+"checkLocationavailable",Toast.LENGTH_LONG).show();

            currentLocationViewModel.requestLocationUpdates();

//            button.setText("Start");
//            button.setTextColor(getResources().getColor(R.color.colorPrimary));

//            ProgressDialog progressBar = new ProgressDialog(getActivity());
//            progressBar.setCancelable(true);//you can cancel it by pressing back button
//            progressBar.setMessage("File downloading ...");
//            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            progressBar.setProgress(0);//initially progress is 0
//            progressBar.setMax(100);//sets the maximum value 100
//            progressBar.show();//displays the progress bar
        }


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mapfragment, container, false);
        ((MainActivity) getActivity()).init_actionbar();
        Toast.makeText(getActivity(),"onCreateView",Toast.LENGTH_LONG).show();
        mSearchText = view.findViewById(R.id.input_search);
        mGps = view.findViewById(R.id.ic_gps);



        button = view.findViewById(R.id.serviceBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),currentLocationViewModel+"",Toast.LENGTH_LONG).show();
                if (currentLocationViewModel.getTrackingState().getValue()) {
                    stopLocationUpdates();
                } else {
                    startLocationUpdates();
                }
            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });


        confirmPermissionAndGetMap();
        observeTrackingStateAndReact();
        init();
//        mapFragment.getMapAsync(this);
        return view;

    }
    private void init(){
        Log.d(TAG, "init: initializing");

//        mGoogleApiClient = new GoogleApiClient
//                .Builder(getActivity())
//                .addApi(Places.GEO_DATA_API)
//                .addApi(Places.PLACE_DETECTION_API)
//                .enableAutoManage(getActivity(), this)
//                .build();
//
//        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(getActivity(), mGoogleApiClient,
//                LAT_LNG_BOUNDS, null);
//
//      mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                    //
                    geoLocate();
                }

                return false;
            }
        });


      hideSoftKeyboard();
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PLACE_PICKER_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                Place place = PlacePicker.getPlace(getActivity(), data);
//
//                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                        .getPlaceById(mGoogleApiClient, place.getId());
//                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//            }
//        }
//    }
    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            Toast.makeText(getActivity(), address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }




    private void startLocationUpdates() {

        currentLocationViewModel.requestLocationUpdates();
        startForegroundService();
    }

  private  void observeCurrentLocationService(){
        currentLocationViewModel.getLocationLiveData1().observe(this, new Observer<CurrentLocationCoordinate>() {
            @Override
            public void onChanged(CurrentLocationCoordinate currentLocationCoordinate) {
                if(currentLocationCoordinate!=null)
                {
                  //  Toast.makeText(getActivity(),currentLocationCoordinate.toString(),Toast.LENGTH_LONG).show();
                }

            }
        });
  }
    private void observeTrackingStateAndReact() {
        currentLocationViewModel.getTrackingState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isRunning) {
                if (isRunning) {
                    button.setText("Stop");
                    button.setTextColor(Color.RED);
                    if (marker != null) {
                        marker.setVisible(true);
                    }
                } else {
                    button.setText("Start");
                    button.setTextColor(getResources().getColor(R.color.colorPrimary));

                    if (marker != null) {
                        marker.setVisible(false);
                    }
                }
            }
        });
    }



    private void stopLocationUpdates() {
        currentLocationViewModel.removeLocationUpdates();
        stopForegroundService();
    }

    @Override
    public void onStart() {
      //  Toast.makeText(getActivity(),"onStart",Toast.LENGTH_LONG).show();
        super.onStart();

        //  getTime();
      //  checkTime();
       observeLocationLiveData();
    //    getDeviceIMEI();
//        startLocationUpdates();
    }

    private void stopForegroundService() {
        getActivity().stopService(new Intent(getActivity(), CurrentLocationService.class));
    }

    private void startForegroundService() {
        getActivity().startService(new Intent(getActivity(), CurrentLocationService.class));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            startForegroundService(new Intent(getActivity(), CurrentLocationService.class));
//        } else {
//            getActivity().startService(new Intent(getActivity(), CurrentLocationService.class));
//
//        }
    }

    //    private void startService(){
//        Intent serviceIntent = new Intent(getActivity(), CurrentLocationService.class);
//        getActivity().startService(serviceIntent);
//    }
    private void  observeLocationLiveData() {

        currentLocationViewModel.getLocationLiveData().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(Location location) {
                Log.e(TAG,location+"");
              //  Toast.makeText(getActivity(),location+"",Toast.LENGTH_LONG).show();

//              if (location!=null){
//                  Toast.makeText(getActivity(),location+"",Toast.LENGTH_LONG).show();
//               LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//                moveCamera(new LatLng(location.getLatitude(), location.getLongitude()),
//                        DEFAULT_ZOOM,
//                        "OasQ");}
//              else
//              {
//                  currentLocationViewModel.requestLocationUpdates();
//                 // observeLocationLiveData();
//              }
//            if (marker == null) {
//
//                marker = mMap.addMarker(new MarkerOptions().position(latLng).title("OasQ"));
//                marker.setVisible(currentLocationViewModel.getTrackingState().getValue());
//            } else {
//                //marker.setPosition(latLng);
//            }
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
//                DEFAULT_ZOOM,
//                "My Location");
        Toast.makeText(getActivity(),"onMapReady",Toast.LENGTH_LONG).show();
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 15.375476, 44.213067)
                , DEFAULT_ZOOM));

      //  observeLocationLiveData();
    }
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Need Permissions");
        builder.setCancelable(false);
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getActivity(), "Location Permissions needed to use this app", Toast.LENGTH_SHORT);
                getActivity().finish();

            }
        });
        builder.show();

    }
    private void showSettinglocationsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Turn Location");
        builder.setMessage("This app needs location to be on.");
        builder.setCancelable(false);
        builder.setPositiveButton("GOTO LOCATION", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openLocationSetting();
            }
        });

        builder.show();
    }
    public void openSettings()
    {
        Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 102);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 101:
                if (!isLocationEnabled())
                { showSettinglocationsDialog();}
                break;
             case 102:
                 initMap();

            case PLACE_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    Place place = PlacePicker.getPlace(getActivity(), data);

                    PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                            .getPlaceById(mGoogleApiClient, place.getId());
                    placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
                }

        }


    }
    public void openLocationSetting()
    {
        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 101);
    }

    private void confirmPermissionAndGetMap() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        if (report.areAllPermissionsGranted()) {
                         //   Toast.makeText(getActivity(), "Location Permissions Granted", Toast.LENGTH_SHORT).show();
//                            if(!isLocationEnabled()){
//                            showSettinglocationsDialog();
////                            new Timer().schedule(new TimerTask() {
////                                @Override
////                                public void run() {
////                                    initMap();
////                                }
////                            },2000000);
//                           }
//                           else{
//                                initMap();
//                            }
//
                            initMap();
                            }
                       else if  (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                            showSettingsDialog();


                        }
                        else {
                            Toast.makeText(getActivity(), "Location Permissions needed to use this app", Toast.LENGTH_SHORT);
                            getActivity().finish();
                        }

                        }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();

                    }
                }).check();

                }



    @Override
    public void onResume() {
        super.onResume();
      //  stopLocationUpdates();
    }
//    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
//        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//
//        mMap.clear();
//
//        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapActivity.this));
//
//        if(placeInfo != null){
//            try{
//                String snippet = "Address: " + placeInfo.getAddress() + "\n" +
//                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
//                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
//                        "Price Rating: " + placeInfo.getRating() + "\n";
//
//                MarkerOptions options = new MarkerOptions()
//                        .position(latLng)
//                        .title(placeInfo.getName())
//                        .snippet(snippet);
//                marker = mMap.addMarker(options);
//
//            }catch (NullPointerException e){
//                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
//            }
//        }else{
//            mMap.addMarker(new MarkerOptions().position(latLng));
//        }
//
//        hideSoftKeyboard();
//    }


    private void moveCamera(LatLng latLng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
      if(latLng==null)
      {

          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng( 44.213067, 15.375476)
                  , zoom));
      }
      else {
          mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

          if (!title.equals("OasQ")) {
              MarkerOptions options = new MarkerOptions()
                      .position(latLng)
                      .title(title);
              mMap.addMarker(options);
          }
      }


        hideSoftKeyboard();
    }
    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getActivity()));

        if(placeInfo != null){
            try{
                String snippet = "Address: " + placeInfo.getAddress() + "\n" +
                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price Rating: " + placeInfo.getRating() + "\n";

                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(placeInfo.getName())
                        .snippet(snippet);
                marker = mMap.addMarker(options);

            }catch (NullPointerException e){
                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
            }
        }else{
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

        hideSoftKeyboard();
    }

    private void hideSoftKeyboard(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

//        private void init(){
//            Log.d(TAG, "init: initializing");
//
//            mGoogleApiClient = new GoogleApiClient
//                    .Builder(getActivity())
//                    .addApi(Places.GEO_DATA_API)
//                    .addApi(Places.PLACE_DETECTION_API)
//                    .enableAutoManage(getActivity(), this)
//                    .build();
//
//            mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(getActivity(), mGoogleApiClient,
//                    LAT_LNG_BOUNDS, null);
//
//            mSearchText.setAdapter(mPlaceAutocompleteAdapter);
//
//            mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//                @Override
//                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                    if(actionId == EditorInfo.IME_ACTION_SEARCH
//                            || actionId == EditorInfo.IME_ACTION_DONE
//                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
//                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){
//
//                        //execute our method for searching
//                        geoLocate();
//                    }
//
//                    return false;
//                }
//            });
//
//            mGps.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Log.d(TAG, "onClick: clicked gps icon");
//                    getDeviceLocation();
//                }
//            });
//
//            hideSoftKeyboard();
//        }

//        private void geoLocate(){
//            Log.d(TAG, "geoLocate: geolocating");
//
//            String searchString = mSearchText.getText().toString();
//
//            Geocoder geocoder = new Geocoder(getActivity());
//            List<Address> list = new ArrayList<>();
//            try{
//                list = geocoder.getFromLocationName(searchString, 1);
//            }catch (IOException e){
//                Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
//            }
//
//            if(list.size() > 0){
//                Address address = list.get(0);
//
//                Log.d(TAG, "geoLocate: found a location: " + address.toString());
//                //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
//
//                moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
//                        address.getAddressLine(0));
//            }
//        }

//        hideSoftKeyboard();


    private void getDeviceLocation() {
     //   Toast.makeText(getActivity(),currentLocationViewModel.getLocationLiveData().getValue() +"",Toast.LENGTH_LONG).show();
       if( !isLocationEnabled())
       {
           showSettinglocationsDialog();
           currentLocationViewModel.requestLocationUpdates();
       }
        if (currentLocationViewModel.getLocationLiveData().getValue() != null) {
            moveCamera(new LatLng(currentLocationViewModel.getLocationLiveData().getValue().getLatitude(), currentLocationViewModel.getLocationLiveData().getValue().getLongitude()),
                    DEFAULT_ZOOM,
                    "OasQ");
//            LatLng latLng = new LatLng(currentLocationViewModel.getLocationLiveData().getValue().getLatitude(), currentLocationViewModel.getLocationLiveData().getValue().getLongitude());
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        }
        else
        {

       //     startLocationUpdates();
//            moveCamera(new LatLng(currentLocationViewModel.getLocationLiveData().getValue().getLatitude(), currentLocationViewModel.getLocationLiveData().getValue().getLongitude()),
//                    DEFAULT_ZOOM,
//                    "OasQ");
        }

//        observeLocationLiveData();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void initMap(){
     //   Toast.makeText(getActivity(),"initMap: initializing map",Toast.LENGTH_LONG).show();
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
         mapFragment.getMapAsync(Map_fragment.this);
    }
    /*
        --------------------------- google places API autocomplete suggestions -----------------
            */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(TAG, "onResult: address: " + place.getAddress());
//                mPlace.setAttributions(place.getAttributions().toString());
//                Log.d(TAG, "onResult: attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: id:" + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(TAG, "onResult: phone number: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: website uri: " + place.getWebsiteUri());

                Log.d(TAG, "onResult: place: " + mPlace.toString());
            }catch (NullPointerException e){
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace);

            places.release();
        }
    };
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(15.330277, 44.225917);
//        LatLng sydney1 = new LatLng(15.337604, 44.203424);
//        LatLng sydney2 = new LatLng(15.330371, 44.206893);
//
//        mMap.addMarker(new MarkerOptions().position(sydney).title("OasQ"));
//        mMap.addMarker(new MarkerOptions().position(sydney1).title("Mohmmed"));
//        mMap.addMarker(new MarkerOptions().position(sydney2).title("Osamah"));
//
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
    public void getTime()
    {
        Calendar instance = Calendar.getInstance();
        int hour = instance.get(Calendar.HOUR);
        int minute = instance.get(Calendar.MINUTE);
        int second = instance.get(Calendar.SECOND);
        Toast.makeText(getActivity(),hour+"",Toast.LENGTH_LONG).show();
        Date date = new Date();
        Toast.makeText(getActivity(),date+"",Toast.LENGTH_LONG).show();
    }
    public  void checkTime()
    {
        Calendar cal = Calendar.getInstance(); //Create Calendar-Object
        cal.setTime(new Date());               //Set the Calendar to now
        int hour = cal.get(Calendar.HOUR_OF_DAY); //Get the hour from the calendar
        if(hour <= 23 && hour >= 8)              // Check if hour is between 8 am and 11pm
        {      //startForegroundService();
            Toast.makeText(getActivity(),hour+"",Toast.LENGTH_LONG).show();
        }

    }
//    public String getDeviceIMEI() {
//        String deviceUniqueIdentifier = null;
//        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
//        if (null != tm) {
//            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                ///  return TODO;
//            }
//            deviceUniqueIdentifier = tm.getDeviceId();
//        }
//        if (null == deviceUniqueIdentifier || 0 == deviceUniqueIdentifier.length()) {
//            deviceUniqueIdentifier = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
//        }
//      Toast.makeText(getActivity(),deviceUniqueIdentifier+"",Toast.LENGTH_LONG).show();
//        return deviceUniqueIdentifier;
//    }
}

