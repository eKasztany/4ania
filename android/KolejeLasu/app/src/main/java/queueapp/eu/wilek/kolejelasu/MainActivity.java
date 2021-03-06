package queueapp.eu.wilek.kolejelasu;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import queueapp.eu.wilek.kolejelasu.database.DepartmentsFetcher;
import queueapp.eu.wilek.kolejelasu.database.FetcherListener;
import queueapp.eu.wilek.kolejelasu.departments.BaseDepertmentFragment;
import queueapp.eu.wilek.kolejelasu.departments.adapters.DepartmentsPagerAdapter;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MainActivity extends AppCompatActivity {

    private final List<Department> departmentList = new ArrayList<>();

    private FragmentPagerAdapter departmentsFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        departmentsFragmentManager = new DepartmentsPagerAdapter(this, getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(departmentsFragmentManager);
        viewPager.setOffscreenPageLimit(departmentsFragmentManager.getCount());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if (departmentList.isEmpty()) {
            fetchDepartments();
        } else {
            populateItemsToFragments(departmentList);
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void fetchDepartments() {
        DepartmentsFetcher departmentsFetcher = new DepartmentsFetcher();
        departmentsFetcher.setFetcherListener(new FetcherListener<Department>() {

            @Override
            public void onDataLoaded(List<Department> items) {
                departmentList.clear();
                departmentList.addAll(items);

                populateItemsToFragments(items);
            }

            @Override
            public void onError() {
                //TODO: handle in some way error
            }
        });
        departmentsFetcher.get();
    }

    private void populateItemsToFragments(List<Department> items) {
        for (int i = 0; i < departmentsFragmentManager.getCount(); i++) {
            Fragment fragment = getFragment(i);

            if (fragment != null) {
                ((BaseDepertmentFragment) fragment).setItems(items);
            }
        }
    }

    public Fragment getFragment(int position) {
        return  getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + position);
    }
}
