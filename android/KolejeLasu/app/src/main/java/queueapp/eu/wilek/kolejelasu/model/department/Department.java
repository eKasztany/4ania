package queueapp.eu.wilek.kolejelasu.model.department;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class Department {

    private final int cityId;
    private final String name;
    private final OpeningHours openingHours;
    private final String departmentId;
    private final Address address;
    private final Location location;
    private final String phone;
    private final String description;

    public Department(@NonNull DataSnapshot dataSnapshot) {
        cityId = dataSnapshot.child("city_id").getValue(Integer.class);
        name = dataSnapshot.child("name").getValue(String.class);
        openingHours = new OpeningHours(dataSnapshot.child("opening_hours"));
        departmentId = dataSnapshot.child("department_id").getValue(String.class);
        address = new Address(dataSnapshot.child("address"));
        location = new Location(dataSnapshot.child("location"));
        phone = dataSnapshot.child("phone").getValue(String.class);
        description = dataSnapshot.child("description").getValue(String.class);
    }

    public double getCityId() {
        return cityId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    @NonNull
    public String getDepartmentId() {
        return departmentId;
    }

    @NonNull
    public Address getAddress() {
        return address;
    }

    @NonNull
    public Location getLocation() {
        return location;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    @NonNull
    public String getDescription() {
        return description;
    }
}
