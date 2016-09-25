package queueapp.eu.wilek.kolejelasu.services.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.model.service.Group;
import queueapp.eu.wilek.kolejelasu.services.interfaces.OnGroupClickListener;

/**
 * Created by mateuszwilczynski on 25.09.2016.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<Group> items = new ArrayList<>();
    private OnGroupClickListener onGroupClickListener;

    public GroupAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public void setItems(List<Group> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.service_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Group group = items.get(position);

        holder.serviceTextView.setText(group.getName());
        Picasso.with(holder.serviceImageView.getContext()).load(group.getPhotoUrl()).into(holder.serviceImageView);
        holder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (onGroupClickListener != null) {
                    onGroupClickListener.onClick(group);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnGroupClickListener(@Nullable OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView serviceTextView;
        ImageView serviceImageView;
        View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            serviceTextView = (TextView) itemView.findViewById(R.id.service_name);
            serviceImageView = (ImageView) itemView.findViewById(R.id.service_img);
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
