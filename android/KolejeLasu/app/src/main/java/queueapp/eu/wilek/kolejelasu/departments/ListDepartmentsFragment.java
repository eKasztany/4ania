package queueapp.eu.wilek.kolejelasu.departments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.ServicesActivity;
import queueapp.eu.wilek.kolejelasu.departments.adapters.DepartmentsAdapter;
import queueapp.eu.wilek.kolejelasu.departments.interfaces.OnListItemClickListener;
import queueapp.eu.wilek.kolejelasu.model.department.Department;
import queueapp.eu.wilek.kolejelasu.utils.DividerItemDecoration;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class ListDepartmentsFragment extends BaseDepertmentFragment implements OnListItemClickListener {

    private DepartmentsAdapter departmentsAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        departmentsAdapter = new DepartmentsAdapter(context);
        departmentsAdapter.setOnListItemClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_department_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.departments_recycler);
        recyclerView.setAdapter(departmentsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setItems(@NonNull List<Department> departmentList) {
        departmentsAdapter.setItems(departmentList);
        departmentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(Department department) {
        Intent intent = new Intent(getContext(), ServicesActivity.class);
        intent.putExtra(ServicesActivity.DEPARTMENT_ID, department.getDepartmentId());
        getContext().startActivity(intent);
    }
}
