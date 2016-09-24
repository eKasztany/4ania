package queueapp.eu.wilek.kolejelasu.departments;

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

public class ListDepartmentsFragment extends BaseDepertmentFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_department_list, container, false);
    }

    @Override
    public void setItems(@NonNull List<Department> departmentList) {

    }
}
