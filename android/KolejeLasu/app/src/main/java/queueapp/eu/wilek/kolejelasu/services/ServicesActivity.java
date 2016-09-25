package queueapp.eu.wilek.kolejelasu.services;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.database.FetcherListener;
import queueapp.eu.wilek.kolejelasu.database.ServiceFetcher;
import queueapp.eu.wilek.kolejelasu.model.service.Service;
import queueapp.eu.wilek.kolejelasu.services.adapter.GroupAdapter;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class ServicesActivity extends AppCompatActivity {

    public static final String DEPARTMENT_ID = "departmentId";
    private static final int COLUMN_COUNT = 2;

    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null || !bundle.containsKey(DEPARTMENT_ID)) {
            finish();
            return;
        }

        groupAdapter = new GroupAdapter(getApplicationContext());

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.groups_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), COLUMN_COUNT));
        recyclerView.setAdapter(groupAdapter);

        fetchServices(bundle.getString(DEPARTMENT_ID));
    }

    private void fetchServices(final String departmentId) {
        ServiceFetcher serviceFetcher = new ServiceFetcher();
        serviceFetcher.setFetcherListener(new FetcherListener<Service>() {

            @Override
            public void onDataLoaded(List<Service> items) {

                for (Service service : items) {

                    if (departmentId.equals(service.getId())) {
                        groupAdapter.setItems(service.getGroupList());
                        groupAdapter.notifyDataSetChanged();
                        break;
                    }
                }
            }

            @Override
            public void onError() {

            }
        });
        serviceFetcher.get();
    }
}
