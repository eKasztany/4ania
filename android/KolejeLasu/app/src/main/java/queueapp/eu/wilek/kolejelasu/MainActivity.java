package queueapp.eu.wilek.kolejelasu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import queueapp.eu.wilek.kolejelasu.model.department.Department;

public class MainActivity extends AppCompatActivity {


    private DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootReference.child("departments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //Log.d("DEPARTMENT", "DATA: " + dataSnapshot.getValue());
                    //Department department = dataSnapshot.getValue(Department.class);
                    Log.d("DEPARTMENT", "OUT: " + new Department(dataSnapshot).getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("BAZA", "The read failed: " + databaseError.getMessage());
            }
        });
    }
}
