package queueapp.eu.wilek.kolejelasu.departments.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.departments.interfaces.OnListItemClickListener;
import queueapp.eu.wilek.kolejelasu.model.department.Department;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class DepartmentsAdapter extends RecyclerView.Adapter<DepartmentsAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Department> items = new ArrayList<>();
    private OnListItemClickListener onListItemClickListener;

    public DepartmentsAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setItems(List<Department> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.department_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Department department = items.get(position);

        holder.departmentTextView.setText(department.getName());
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onListItemClickListener != null) {
                    onListItemClickListener.onClick(department);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnListItemClickListener(@Nullable OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView departmentTextView;
        View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            departmentTextView = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View view) {
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }
}
