package queueapp.eu.wilek.kolejelasu.departments;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.departments.adapters.PagerAdapterFragment;


/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public enum DepartmentListingType implements PagerAdapterFragment {

    MAPS(0, R.string.maps) {
        @Override
        public Fragment createFragment() {
            return new MapDepartmentsFragment();
        }
    },
    LIST(1, R.string.department_list) {
        @Override
        public Fragment createFragment() {
            return new ListDepartmentsFragment();
        }
    };

    private final int departmentListingType;

    @StringRes
    private final int title;

    DepartmentListingType(int departmentListingType, @StringRes int title) {
        this.departmentListingType = departmentListingType;
        this.title = title;
    }

    @StringRes
    public int getTitle() {
        return title;
    }

    public static DepartmentListingType createFromValue(int value) {
        for (DepartmentListingType type : DepartmentListingType.values()) {

            if (type.departmentListingType == value) {
                return type;
            }
        }

        return DepartmentListingType.MAPS;
    }
}
