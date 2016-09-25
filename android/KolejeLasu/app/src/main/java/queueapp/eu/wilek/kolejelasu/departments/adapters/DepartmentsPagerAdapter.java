package queueapp.eu.wilek.kolejelasu.departments.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import queueapp.eu.wilek.kolejelasu.departments.DepartmentListingType;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class DepartmentsPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    public DepartmentsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        DepartmentListingType mainPageType = DepartmentListingType.LIST.createFromValue(position);
        Fragment fragment = mainPageType.createFragment();

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return context.getString(DepartmentListingType.createFromValue(position).getTitle());
    }

    @Override
    public int getCount() {
        return 2;
    }
}