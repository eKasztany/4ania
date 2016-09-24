package queueapp.eu.wilek.kolejelasu.departments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MapPresenter implements GoogleMap.OnMarkerClickListener {

    private static final int DEFAULT_ZOOM = 12;
    private static final double START_LATITUDE = 52.229676;
    private static final double START_LONGITUDE = 21.012229;

    private final List<Department> departmentList = new ArrayList<>();

    private MapView mapView;
    private GoogleMap googleMap;

    private OnDepartmentClickListener onDepartmentClickListener;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googleMap) {
                MapPresenter.this.googleMap = googleMap;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(START_LATITUDE, START_LONGITUDE), DEFAULT_ZOOM));
                googleMap.setOnMarkerClickListener(MapPresenter.this);
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

    public void setItems(@NonNull List<Department> items) {
        departmentList.clear();
        departmentList.addAll(items);

        if (googleMap == null) {
            return;
        }

        for (Department item : items) {
            googleMap.addMarker(createMarker(item));
        }
    }

    public void moveByY(int y) {
        if (googleMap != null) {
            googleMap.animateCamera(CameraUpdateFactory.scrollBy(0, y));
        }
    }

    public void clear() {
        if (googleMap != null) {
            googleMap.clear();
        }
    }

    private MarkerOptions createMarker(Department item) {
        return new MarkerOptions()
                .position(new LatLng(item.getLocation().getLatitude(), item.getLocation().getLongitude()))
                .title(item.getName());
    }

    public void setOnDepartmentClickListener(@Nullable OnDepartmentClickListener onDepartmentClickListener) {
        this.onDepartmentClickListener = onDepartmentClickListener;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (onDepartmentClickListener == null) {
            return false;
        }

        String clickedDepartmentName = marker.getTitle();

        for (Department department : departmentList) {

            if (clickedDepartmentName.equals(department.getName())) {
                onDepartmentClickListener.onClick(department);
                break;
            }
        }

        return false;
    }
}
