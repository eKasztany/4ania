package queueapp.eu.wilek.kolejelasu.model.department;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class Address {

    private final String code;
    private final String city;
    private final String street;

    public Address(@NonNull DataSnapshot dataSnapshot) {
        code = dataSnapshot.child("code").getValue(String.class);
        city = dataSnapshot.child("city").getValue(String.class);
        street = dataSnapshot.child("street").getValue(String.class);
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getCity() {
        return city;
    }

    @NonNull
    public String getStreet() {
        return street;
    }
}
