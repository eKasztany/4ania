package queueapp.eu.wilek.kolejelasu.departments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class MapDepartmentsFragment extends BaseDepertmentFragment {


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
    }

    @Override
    public void setItems(@NonNull List<Department> departmentList) {
        //TODO: display items on map
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
}
