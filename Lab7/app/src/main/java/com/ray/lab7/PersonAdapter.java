package com.ray.lab7;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ray on 2016/12/4.
 */
public class PersonAdapter extends ArrayAdapter<Person> {

    private int resourceId;

    public PersonAdapter(Context context, @LayoutRes int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Person person = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView nameTextView = (TextView)view.findViewById(R.id.nameTextView);
        nameTextView.setText(person.getName());
        TextView mobileTextView = (TextView)view.findViewById(R.id.mobileTextView);
        mobileTextView.setText(person.getMobile());
        return view;
    }
}
