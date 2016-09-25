package queueapp.eu.wilek.kolejelasu.model.service;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class Group {

    private final String serviceTime;
    private final String id;
    private final int availableOffice;
    private final String name;
    private final String letter;
    private final int queue;
    private final int actuallNumber;
    private final String photoUrl;

    public Group(@NonNull DataSnapshot dataSnapshot) {
        serviceTime = dataSnapshot.child("czasObslugi").getValue(String.class);
        id = dataSnapshot.child("idGrupy").getValue(String.class);
        availableOffice = dataSnapshot.child("liczbaCzynnychStan").getValue(Integer.class);
        name = dataSnapshot.child("nazwaGrupy").getValue(String.class);
        letter = dataSnapshot.child("literaGrupy").getValue(String.class);
        queue = dataSnapshot.child("liczbaKlwKolejce").getValue(Integer.class);
        actuallNumber = dataSnapshot.child("aktualnyNumer").getValue(Integer.class);
        photoUrl = dataSnapshot.child("photoUrl").getValue(String.class);
    }

    @NonNull
    public String getServiceTime() {
        return serviceTime;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public int getAvailableOffice() {
        return availableOffice;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getLetter() {
        return letter;
    }

    public int getQueue() {
        return queue;
    }

    public int getActuallNumber() {
        return actuallNumber;
    }

    @NonNull
    public String getPhotoUrl() {
        return photoUrl;
    }
}
