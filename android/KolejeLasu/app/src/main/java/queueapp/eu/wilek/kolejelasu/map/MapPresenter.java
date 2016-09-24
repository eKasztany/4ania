package queueapp.eu.wilek.kolejelasu.map;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import queueapp.eu.wilek.kolejelasu.R;

public class MapPresenter {

    private MapView mapView;
    private GoogleMap googleMap;

    public void onCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        mapView = (MapView) activity.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapPresenter.this.googleMap = googleMap;
            }
        });

        MapsInitializer.initialize(activity);
    }

    public void onResume() {
        mapView.onResume();
    }

    public void onPause() {
        mapView.onPause();
    }

    public void onDestroy() {
        mapView.onDestroy();
        mapView = null;
        googleMap = null;
    }


    public void clear() {
        if (googleMap != null) {
            googleMap.clear();
        }
    }
}
