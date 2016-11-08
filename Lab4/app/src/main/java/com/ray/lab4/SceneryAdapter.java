package com.ray.lab4;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ray on 2016/11/3.
 */
public class SceneryAdapter extends ArrayAdapter<Scenery> {

    private int resourceId;

    public SceneryAdapter(Context context, @LayoutRes int resource, @NonNull List<Scenery> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Scenery scenery = getItem(position);
        View view;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        }
        else{
            view = convertView;
        }
        TextView sceneryTitleText = (TextView) view.findViewById(R.id.sceneryId);
        sceneryTitleText.setText(scenery.getTitle());

        return view;
    }
}
