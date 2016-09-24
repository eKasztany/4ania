package queueapp.eu.wilek.kolejelasu.model.department;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */
public class OpeningHours {

    private final String monday;
    private final String tuesday;
    private final String wednesday;
    private final String thursday;
    private final String friday;

    public OpeningHours(@NonNull DataSnapshot dataSnapshot) {
        monday = dataSnapshot.child("mon").getValue(String.class);
        tuesday = dataSnapshot.child("tue").getValue(String.class);
        wednesday = dataSnapshot.child("wen").getValue(String.class);
        thursday = dataSnapshot.child("thr").getValue(String.class);
        friday = dataSnapshot.child("fri").getValue(String.class);
    }

    @NonNull
    public String getMonday() {
        return monday;
    }

    @NonNull
    public String getTuesday() {
        return tuesday;
    }

    @NonNull
    public String getWednesday() {
        return wednesday;
    }

    @NonNull
    public String getThrusday() {
        return thursday;
    }

    @NonNull
    public String getFriday() {
        return friday;
    }
}
