package queueapp.eu.wilek.kolejelasu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import queueapp.eu.wilek.kolejelasu.map.MapPresenter;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

public class MainActivity extends AppCompatActivity {

    private MapPresenter mapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapPresenter = new MapPresenter();
        mapPresenter.onCreated(this, savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapPresenter.onDestroy();
    }
}
