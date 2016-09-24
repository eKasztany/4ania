package queueapp.eu.wilek.kolejelasu.model.department;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class Location {

    private final double lat;
    private final double lon;

    public Location(@NonNull DataSnapshot dataSnapshot) {
        lat = dataSnapshot.child("lat").getValue(Double.class);
        lon = dataSnapshot.child("lon").getValue(Double.class);
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }
}
