package com.mavpokit.transitionsapp.fragments;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mavpokit.transitionsapp.R;

/**
 * Created by Alex on 21.12.2016.
 */

public class FragmentAM extends Fragment implements OnMapReadyCallback,View.OnClickListener {
    private static final String TAG = "-----FragmentAM-----";
    private static final String MAP_FRAGMENT_TAG = "MAP_FRAGMENT";

    private GoogleMap mMap;

    //for toast delay on start
    CountDownTimer timer; //for implementation with CountDownTimer
    Handler handler; //for implementation with CountDownTimer
    Runnable runnable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        showToastDelayedByTimer("Click on map to place marker",1000);
        checkNetworkConnection();

    }

    private void checkNetworkConnection() {
        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    private void showToastDelayedByTimer(final String message, final int delay) {
        timer = new CountDownTimer(delay,delay) {
            @Override
            public void onTick(long l) {}

            @Override
            public void onFinish() {
                Toast toast = Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundResource(R.drawable.toast_shape_round);
                //        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //        TextView textView = (TextView)view.findViewById(android.R.id.message);
                //        textView.setTextColor(getResources().getColor(R.color.colorAccent));

                toast.show();

            }
        };
        timer.start();

    }
    private void showToastDelayedByHandler(final String message, final int delay) {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                Toast toast = Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
                View view = toast.getView();
                view.setBackgroundResource(R.drawable.toast_shape_round);
                //        view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //        TextView textView = (TextView)view.findViewById(android.R.id.message);
                //        textView.setTextColor(getResources().getColor(R.color.colorAccent));

                toast.show();

            }
        };
        handler.postDelayed(runnable,delay);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        Snackbar.make(getActivity().findViewById(R.id.map_container), "Click on map to place marker",Snackbar.LENGTH_LONG)
//                .show();
        setRetainInstance(true);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        timer.cancel();
//        handler.removeCallbacks(runnable);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View fragmentContainer = inflater.inflate(R.layout.fragment_am, container, false);
//doesn't work with fragments
//        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
        if (mapFragment == null) {
            mapFragment = new SupportMapFragment();
            replaceFragment(mapFragment, MAP_FRAGMENT_TAG, false);
        }

//        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
//                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        Button buttonL1 = (Button)fragmentContainer.findViewById(R.id.buttonLocation1);
        Button buttonL2 = (Button)fragmentContainer.findViewById(R.id.buttonLocation2);
        ImageButton buttonL3 = (ImageButton)fragmentContainer.findViewById(R.id.buttonLocation3);
        buttonL1.setOnClickListener(this);
        buttonL2.setOnClickListener(this);
        buttonL3.setOnClickListener(this);

        return fragmentContainer;
    }

    private void replaceFragment(SupportMapFragment fragment, String mapFragment, boolean addToBackStackFlag) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.map_container, fragment, MAP_FRAGMENT_TAG);
        if (addToBackStackFlag) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
            ||
            ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED)
        {
            mMap.setMyLocationEnabled(true);
        }


        UiSettings mapSettings = mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setCompassEnabled(true);

//        mMap.addMarker(new MarkerOptions().position(mapCenter).title("Marker in Kiev"));

//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapCenter, 3));

        animateCamera(11,2000,new LatLng(50.444938, 30.520794));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                // finalLatLng is known

                Projection projecion = mMap.getProjection();
                Point point = projecion.toScreenLocation(latLng);
                point.offset(0,dpToPixels(-200));
                LatLng startLatLng = projecion.fromScreenLocation(point);

                Marker marker =  mMap.addMarker(new MarkerOptions()
                                .title("Achtung!")
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.radioact1))
//                                .position(latLng)
                                .position(startLatLng)
                                .anchor(0.5f,0.5f)
                //                .flat(true)
                //                .rotation(245)
                );

                ObjectAnimator posAnimator = ObjectAnimator.ofObject(marker,"position",new LatLngEvaluator(),startLatLng,latLng);
                posAnimator.setDuration(200);
//                posAnimator.setInterpolator(new BounceInterpolator());
//                posAnimator.start();

                ObjectAnimator rotAnimator = ObjectAnimator.ofFloat(marker,"rotation",180,360);
                rotAnimator.setDuration(400);

                ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(marker,"alpha",0,1);
                alphaAnimator.setDuration(600);
//                rotAnimator.start();

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(posAnimator,rotAnimator,alphaAnimator);
                animatorSet.start();


            }
        });

    }

    private int dpToPixels(int dp) {
        return (int)(dp * getResources().getDisplayMetrics().density + 0.5f );
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLocation1:
                animateCamera(8,2000,new LatLng(50.444938, 20.520794));
                break;
            case R.id.buttonLocation2:
                animateCamera(10,2000,new LatLng(50.444938, 10.520794));
                break;
            case R.id.buttonLocation3:
                animateCamera(11,2000,new LatLng(50.444938, 30.520794));
                break;

        }
    }

    private void animateCamera(int zoom, int time_ms, LatLng latLng) {
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(zoom)
//                .bearing(90)
                .build();

        // Animate the change in camera view over time_ms milliseconds
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),time_ms, null);

    }

    class LatLngEvaluator implements TypeEvaluator<LatLng> {

        @Override
        public LatLng evaluate(float fraction, LatLng start, LatLng end) {
            return new LatLng(start.latitude + fraction*(end.latitude-start.latitude),
                    start.longitude + fraction*(end.longitude-start.longitude));
        }
    }
}
