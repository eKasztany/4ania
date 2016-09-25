package queueapp.eu.wilek.kolejelasu.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import queueapp.eu.wilek.kolejelasu.model.department.Department;
import queueapp.eu.wilek.kolejelasu.model.service.Service;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class ServiceFetcher extends DatabaseFetcher<Service> {

    @Override
    protected DatabaseReference getDatabaseFetcher() {
        return getDatabaseReference().child("services");
    }

    @Override
    protected Service createData(DataSnapshot dataSnapshot) {
        return new Service(dataSnapshot.child("service_data"));
    }
}
