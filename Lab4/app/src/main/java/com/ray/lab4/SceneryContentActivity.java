package com.ray.lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

/**
 * Created by Ray on 2016/11/3.
 */
public class SceneryContentActivity extends Activity {
    public static void actionStart(Context context,String sceneryTitle, String sceneryContent, int sceneryImageResource){
        Intent intent = new Intent(context,SceneryContentActivity.class);
        intent.putExtra("sceneryTitle",sceneryTitle);
        intent.putExtra("sceneryContent",sceneryContent);
        intent.putExtra("sceneryImageResource",sceneryImageResource);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.scenery_content);
        String sceneryTitle = getIntent().getStringExtra("sceneryTitle");
        String sceneryContent = getIntent().getStringExtra("sceneryContent");
        int sceneryImageResource = getIntent().getIntExtra("sceneryImageResource",0);
        SceneryContentFragment sceneryContentFragment = (SceneryContentFragment)getFragmentManager().findFragmentById(R.id.sceneryContentFragment);
        sceneryContentFragment.refresh(sceneryTitle,sceneryContent,sceneryImageResource);
    }
}
