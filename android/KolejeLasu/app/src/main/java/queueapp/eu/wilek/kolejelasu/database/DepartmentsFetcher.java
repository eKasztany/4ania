package queueapp.eu.wilek.kolejelasu.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class DepartmentsFetcher extends DatabaseFetcher<Department> {

    @Override
    protected DatabaseReference getDatabaseFetcher() {
        return getDatabaseReference().child("departments");
    }

    @Override
    protected Department createData(DataSnapshot dataSnapshot) {
        return new Department(dataSnapshot);
    }
}
