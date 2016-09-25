package queueapp.eu.wilek.kolejelasu.departments.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import queueapp.eu.wilek.kolejelasu.R;
import queueapp.eu.wilek.kolejelasu.model.department.Address;
import queueapp.eu.wilek.kolejelasu.model.department.Department;
import queueapp.eu.wilek.kolejelasu.model.department.OpeningHours;

/**
 * Created by mateuszwilczynski on 24.09.2016.
 */

public class DepartmentsSwipePageAdapter extends PagerAdapter {

    private final LayoutInflater layoutInflater;
    private final List<Department> departmentList = new ArrayList<>();

    public DepartmentsSwipePageAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Department item = departmentList.get(position);
        Address address = item.getAddress();
        OpeningHours openingHours = item.getOpeningHours();
        ViewGroup layout = (ViewGroup) layoutInflater.inflate(R.layout.department_map_item, container, false);

        ((TextView) layout.findViewById(R.id.name)).setText(item.getName());
        ((TextView) layout.findViewById(R.id.address)).setText(address.getCode() + " " + address.getCity() + "\n" + address.getStreet());
        ((TextView) layout.findViewById(R.id.monday)).setText(openingHours.getMonday());
        ((TextView) layout.findViewById(R.id.tuesday)).setText(openingHours.getTuesday());
        ((TextView) layout.findViewById(R.id.wednesday)).setText(openingHours.getWednesday());
        ((TextView) layout.findViewById(R.id.thursday)).setText(openingHours.getThrusday());
        ((TextView) layout.findViewById(R.id.friday)).setText(openingHours.getFriday());

        TextView phone = (TextView) layout.findViewById(R.id.phone);
        phone.setText(item.getPhone());

        Linkify.addLinks(phone, Linkify.PHONE_NUMBERS);

        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public int getCount() {
        return departmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setItems(@NonNull List<Department> items) {
        departmentList.clear();
        departmentList.addAll(items);
        notifyDataSetChanged();
    }

    public Department getItem(int position) {
        return departmentList.get(position);
    }
}
