package queueapp.eu.wilek.kolejelasu.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.booking.BookingActivity;
import queueapp.eu.wilek.kolejelasu.database.FetcherListener;
import queueapp.eu.wilek.kolejelasu.database.ServiceFetcher;
import queueapp.eu.wilek.kolejelasu.model.service.Group;
import queueapp.eu.wilek.kolejelasu.model.service.Service;
import queueapp.eu.wilek.kolejelasu.services.adapter.GroupAdapter;
import queueapp.eu.wilek.kolejelasu.services.interfaces.OnGroupClickListener;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class ServicesActivity extends AppCompatActivity implements OnGroupClickListener {

    public static final String DEPARTMENT_ID = "departmentId";
    public static final String DEPARTMENT_NAME = "departmentName";
    private static final int COLUMN_COUNT = 2;

    private String departmentName;

    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle == null || !bundle.containsKey(DEPARTMENT_ID)) {
            finish();
            return;
        }

        departmentName = bundle.getString(DEPARTMENT_NAME);

        getSupportActionBar().setTitle(departmentName);

        groupAdapter = new GroupAdapter(getApplicationContext());
        groupAdapter.setOnGroupClickListener(this);

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
                //nop
            }
        });
        serviceFetcher.get();
    }

    @Override
    public void onClick(@NonNull Group group) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra(BookingActivity.WAITING_COUNT, group.getQueue());
        intent.putExtra(BookingActivity.AVERAGE_TIME, group.getServiceTime());
        intent.putExtra(BookingActivity.OFFICE_COUNT, group.getAvailableOffice());
        intent.putExtra(BookingActivity.GROUP, group.getName());
        intent.putExtra(DEPARTMENT_NAME, departmentName);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
