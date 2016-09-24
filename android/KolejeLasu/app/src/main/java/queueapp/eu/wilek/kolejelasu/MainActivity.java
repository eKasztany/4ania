package queueapp.eu.wilek.kolejelasu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.database.DepartmentsFetcher;
import queueapp.eu.wilek.kolejelasu.database.FetcherListener;
import queueapp.eu.wilek.kolejelasu.departments.DepartmentsPagerAdapter;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new DepartmentsPagerAdapter(this, getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        DepartmentsFetcher departmentsFetcher = new DepartmentsFetcher();
        departmentsFetcher.setFetcherListener(new FetcherListener<Department>() {
            @Override
            public void onDataLoaded(List<Department> items) {

            }

            @Override
            public void onError() {
                //TODO: handle in some way error
            }
        });
        departmentsFetcher.get();
    }
}
