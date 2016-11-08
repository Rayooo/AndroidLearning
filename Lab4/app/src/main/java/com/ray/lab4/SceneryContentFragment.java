package com.ray.lab4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ray on 2016/11/3.
 */
public class SceneryContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.scenery_content_frag,container,false);
        return view;
    }

    public void refresh(String sceneryTitle,String sceneryContent, int sceneryImageResource){
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        if(visibilityLayout == null){
            Log.v("SceneryContentFragment","visibilityLayout错误");
        }
        else{
            visibilityLayout.setVisibility(View.VISIBLE);
        }

        TextView sceneryTitleText = (TextView)view.findViewById(R.id.sceneryTitle);
        TextView sceneryContentText = (TextView)view.findViewById(R.id.sceneryContent);
        ImageView sceneryImage = (ImageView)view.findViewById(R.id.sceneryImage);
        sceneryTitleText.setText(sceneryTitle);
        sceneryContentText.setText(sceneryContent);
        sceneryImage.setImageResource(sceneryImageResource);
    }
}
