package queueapp.eu.wilek.kolejelasu.departments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MapDepartmentsFragment extends BaseDepertmentFragment implements OnDepartmentClickListener {

    private MapPresenter mapPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mapPresenter = new MapPresenter();
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
    }

    @Override
    public void setItems(@NonNull List<Department> departmentList) {
        mapPresenter.setItems(departmentList);
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
    public void onDestroy() {
        super.onDestroy();
        mapPresenter.onDestroy();
    }

    @Override
    public void onClick(@NonNull Department department) {
        Log.d("TEST", "CLICKED: " + department.getName());
    }
}
