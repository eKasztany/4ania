package queueapp.eu.wilek.kolejelasu.model.service;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class Service {

    private final String id;
    private final String date;
    private final String time;
    private final List<Group> groupList = new ArrayList<>();

    public Service(@NonNull String id, @NonNull DataSnapshot dataSnapshot) {
        this.id = id;
        date = dataSnapshot.child("date").getValue(String.class);
        time = dataSnapshot.child("time").getValue(String.class);

        DataSnapshot groupsSnapshot = dataSnapshot.child("grupy");

        for (DataSnapshot snapshot : groupsSnapshot.getChildren()) {
            groupList.add(new Group(snapshot));
        }
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    @NonNull
    public List<Group> getGroupList() {
        return groupList;
    }
}
