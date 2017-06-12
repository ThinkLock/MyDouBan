package com.yang.mydouban.ui.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseActivity;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fengzhaoyang_i on 2017/6/12.
 */

public class StoryDetailActivity extends BaseActivity implements View.OnClickListener{

    private int mStoryID;

    private ImageView mIvImage;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        initView();
        initData();
    }


    private void initView(){
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mIvImage = (ImageView) findViewById(R.id.iv_detail_top);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ViewGroup mContentContainer = (ViewGroup) findViewById(R.id.content_container);
        mContentContainer.setOnClickListener(this);

    }

    private void initData(){
        mStoryID = getIntent().getIntExtra(Constants.INTENT_SOTRY_ID,0);
        Log.i(TAG,mStoryID+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_story_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_share) {
            ToastUtils.showShort("share");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
    }
}
