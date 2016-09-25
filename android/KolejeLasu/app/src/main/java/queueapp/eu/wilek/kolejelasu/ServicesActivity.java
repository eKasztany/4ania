package queueapp.eu.wilek.kolejelasu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.database.FetcherListener;
import queueapp.eu.wilek.kolejelasu.database.ServiceFetcher;
import queueapp.eu.wilek.kolejelasu.model.service.Service;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class ServicesActivity extends AppCompatActivity {

    public static final String DEPARTMENT_ID = "departmentId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null || !bundle.containsKey(DEPARTMENT_ID)) {
            finish();
            return;
        }

        fetchServices(bundle.getString(DEPARTMENT_ID));
    }

    private void fetchServices(String departmentId) {
        ServiceFetcher serviceFetcher = new ServiceFetcher();
        serviceFetcher.setFetcherListener(new FetcherListener<Service>() {

            @Override
            public void onDataLoaded(List<Service> items) {
                Log.d("SERVICES", "SIZE: " + items.size());
            }

            @Override
            public void onError() {

            }
        });
        serviceFetcher.get();
    }
}
