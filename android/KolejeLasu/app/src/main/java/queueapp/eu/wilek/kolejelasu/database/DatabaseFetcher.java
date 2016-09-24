package queueapp.eu.wilek.kolejelasu.database;

import android.support.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public abstract class DatabaseFetcher<D> {

    private DatabaseReference rootReference;
    private FetcherListener<D> fetcherListener;

    public DatabaseFetcher() {
        rootReference = FirebaseDatabase.getInstance().getReference();
    }

    public void setFetcherListener(@Nullable FetcherListener fetcherListener) {
        this.fetcherListener = fetcherListener;
    }

    public void get() {
        getDatabaseFetcher().addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<D> items = new ArrayList();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    items.add(createData(dataSnapshot));
                }

                if (fetcherListener != null) {
                    fetcherListener.onDataLoaded(items);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (fetcherListener != null) {
                    fetcherListener.onError();
                }
            }
        });
    }

    protected DatabaseReference getDatabaseReference() {
        return rootReference;
    }

    protected abstract DatabaseReference getDatabaseFetcher();

    protected abstract D createData(DataSnapshot dataSnapshot);
}
