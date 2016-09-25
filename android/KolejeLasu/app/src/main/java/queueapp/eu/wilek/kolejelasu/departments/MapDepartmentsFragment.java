package queueapp.eu.wilek.kolejelasu.departments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.departments.adapters.DepartmentsSwipePageAdapter;
import queueapp.eu.wilek.kolejelasu.departments.interfaces.OnDepartmentClickListener;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MapDepartmentsFragment extends BaseDepertmentFragment implements OnDepartmentClickListener, ViewPager.OnPageChangeListener {

    private MapPresenter mapPresenter;
    private DepartmentsSwipePageAdapter departmentsSwipePageAdapter;
    private ViewPager departmentsViewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mapPresenter = new MapPresenter();
        departmentsSwipePageAdapter = new DepartmentsSwipePageAdapter(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_department_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapPresenter.onViewCreated(view, savedInstanceState);
        mapPresenter.setOnDepartmentClickListener(this);

        final BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(view.findViewById(R.id.department_layout));
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
                    mapPresenter.moveByY((int) getResources().getDimension(R.dimen.default_info_height));
                } else if (BottomSheetBehavior.STATE_COLLAPSED == newState) {
                    mapPresenter.moveByY(- (int) getResources().getDimension(R.dimen.default_info_height));
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //nop
            }
        });

        departmentsViewPager = (ViewPager) view.findViewById(R.id.view_pager_departments);
        departmentsViewPager.setAdapter(departmentsSwipePageAdapter);
        departmentsViewPager.addOnPageChangeListener(this);
        view.findViewById(R.id.show_services).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Department department = departmentsSwipePageAdapter.getItem(departmentsViewPager.getCurrentItem());
                Toast.makeText(getContext(), department.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setItems(@NonNull List<Department> departmentList) {
        mapPresenter.setItems(departmentList);
        departmentsSwipePageAdapter.setItems(departmentList);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapPresenter.onPause();
    }


    @Override
    public void onClick(int position) {
        departmentsViewPager.setCurrentItem(position, true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //nop
    }

    @Override
    public void onPageSelected(int position) {
        mapPresenter.setSelectedMarker(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //nop
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        departmentsViewPager.removeOnPageChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapPresenter.onDestroy();
    }
}
