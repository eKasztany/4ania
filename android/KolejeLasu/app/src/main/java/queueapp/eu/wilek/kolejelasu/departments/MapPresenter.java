package queueapp.eu.wilek.kolejelasu.departments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;

import queueapp.eu.wilek.kolejelasu.R;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MapPresenter {

    private MapView mapView;
    private GoogleMap googleMap;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapPresenter.this.googleMap = googleMap;
            }
        });

        MapsInitializer.initialize(view.getContext());
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
