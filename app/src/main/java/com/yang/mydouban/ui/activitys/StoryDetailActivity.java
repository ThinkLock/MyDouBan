package com.yang.mydouban.ui.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yang.mydouban.R;
import com.yang.mydouban.base.BaseActivity;
import com.yang.mydouban.been.ZHStoryDetailResult;
import com.yang.mydouban.been.ZHStoryExtraResult;
import com.yang.mydouban.config.Constants;
import com.yang.mydouban.mvp.presenter.impl.StoryDetailPresenterImpl;
import com.yang.mydouban.mvp.view.IStoryDetailView;
import com.yang.mydouban.utils.NetworkUtils;
import com.yang.mydouban.utils.SPUtils;
import com.yang.mydouban.utils.ToastUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by fengzhaoyang_i on 2017/6/12.
 */

public class StoryDetailActivity extends BaseActivity implements IStoryDetailView,View.OnClickListener{

    private int mStoryID;
    protected List<String> imgUrlList;


    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvImageSource;
    private ProgressBar mPsbLoading;
    private WebView  mWebContent;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    private StoryDetailPresenterImpl storyDetailPresenter;

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
        mTvTitle = (TextView)findViewById(R.id.tv_detail_title);
        mTvImageSource = (TextView)findViewById(R.id.tv_imgSource);
        mPsbLoading = (ProgressBar)findViewById(R.id.psb_loading);
        mWebContent = (WebView)findViewById(R.id.web_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ViewGroup mContentContainer = (ViewGroup) findViewById(R.id.content_container);
        mContentContainer.setOnClickListener(this);
        collapsingToolbarLayout.setTitleEnabled(false);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        toolbar.setNavigationIcon(R.drawable.ic_back_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StoryDetailActivity.this.finish();
            }
        });
        storyDetailPresenter = new StoryDetailPresenterImpl(this);


        ///初始化webview
        WebSettings webSettings = mWebContent.getSettings();
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(getApplicationContext().getDir("cache", 0).getPath());
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setLoadsImagesAutomatically(true);
        mWebContent.addJavascriptInterface(this, "BihuDaily");
    }

    private void initData(){
        mStoryID = getIntent().getIntExtra(Constants.INTENT_SOTRY_ID,0);
        Log.i(TAG,mStoryID+"");
        storyDetailPresenter.loadStoryDetail(mStoryID);
        storyDetailPresenter.loadStoryExtra(mStoryID);
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

    @Override
    public void showMessage(String msg) {
        ToastUtils.showShort(msg);
    }

    @Override
    public void showProgress() {
        //mPsbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        //mPsbLoading.setVisibility(View.GONE);
    }

    @Override
    public void loadStoryDetailInfo(Object result) {
        if(result instanceof ZHStoryDetailResult){
            mTvTitle.setText(((ZHStoryDetailResult) result).getTitle());
            mTvImageSource.setText(((ZHStoryDetailResult) result).getImage_source());
            Glide.with(this)
                    .load(((ZHStoryDetailResult) result).getImage())
                    .fitCenter()
                    .centerCrop()
                    .into(mIvImage);

            loadHtml((ZHStoryDetailResult)result);
        }
    }

    @Override
    public void loadStoryExtraInfo(Object result) {
        if(result instanceof ZHStoryExtraResult){
            Log.i(TAG,((ZHStoryExtraResult) result).getComments()+"");
        }
    }

    /**
     * 替换html中的<img class="content-image">标签的属性
     *
     * @param html
     * @return
     */
    protected String replaceImgTagFromHTML(String html, boolean autoLoad, boolean nightMode) {
        imgUrlList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements es = doc.getElementsByTag("img");
        for (Element e : es) {
            if (!"avatar".equals(e.attr("class"))) {
                String imgUrl = e.attr("src");
                imgUrlList.add(imgUrl);
                String src = String.format("file:///android_asset/default_pic_content_image_%s_%s.png",
                        autoLoad ? "loading" : "download",
                        nightMode ? "dark" : "light");
                e.attr("src", src);
                e.attr("zhimg-src", imgUrl);
                e.attr("onclick", "onImageClick(this)");
            }
        }
        return doc.html();
    }

    protected void loadHtml(ZHStoryDetailResult detailContent) {
        StringBuilder htmlSb = new StringBuilder("<!doctype html>\n<html><head>\n<meta charset=\"utf-8\">\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width,user-scalable=no\">");

        String content = detailContent.getBody();
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">\n";
        String img_replace = "<script src=\"file:///android_asset/img_replace.js\"></script>\n";
        String video = "<script src=\"file:///android_asset/video.js\"></script>\n";
        String zepto = "<script src=\"file:///android_asset/zepto.min.js\"></script>\n";
        String autoLoadImage = "onload=\"onLoaded()\"";

        boolean autoLoad = NetworkUtils.isWifi(this) || !(boolean) SPUtils.getFromDefaultPref(this, Constants.KEY_NO_LOAD_IMAGE, false);
        boolean nightMode = (boolean) SPUtils.get(this, Constants.KEY_NIGHT, false);
        boolean largeFont = (boolean) SPUtils.getFromDefaultPref(this, Constants.KEY_BIG_FONT, false);

        htmlSb.append(css)
                .append(zepto)
                .append(img_replace)
                .append(video)
                .append("</head><body className=\"\"")
                .append(autoLoad ? autoLoadImage : "")
                .append(" >")
                .append(content);
        if (nightMode) {
            String night = "<script src=\"file:///android_asset/night.js\"></script>\n";
            htmlSb.append(night);
        }
        if (largeFont) {
            String bigFont = "<script src=\"file:///android_asset/large-font.js\"></script>\n";
            htmlSb.append(bigFont);
        }
        htmlSb.append("</body></html>");
        String html = htmlSb.toString();

        html = html.replace("<div class=\"img-place-holder\">", "");
        Log.e("html1", html);
        html = replaceImgTagFromHTML(html, autoLoad, nightMode);
        Log.e("html2", html);
        mWebContent.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    // ======================= js ========================
    @JavascriptInterface
    public void clickToLoadImage(final String imgPath) {
        if (TextUtils.isEmpty(imgPath))
            return;
        mWebContent.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(StoryDetailActivity.this).load(imgPath)
                        .downloadOnly(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                                String str = "file://" + resource.getAbsolutePath();//加载完成的图片地址
                                try {
                                    String[] arrayOfString = new String[2];
                                    arrayOfString[0] = URLEncoder.encode(imgPath,"UTF-8");//旧url
                                    arrayOfString[1] = str;
                                    onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
                                } catch (Exception e) {

                                }
                            }
                        });
            }
        });
    }

    @JavascriptInterface
    public void loadImage(final String imgPath) {
        if (TextUtils.isEmpty(imgPath))
            return;
        mWebContent.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(StoryDetailActivity.this).load(imgPath)
                        .downloadOnly(new SimpleTarget<File>() {
                            @Override
                            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                                String str = "file://" + resource.getAbsolutePath();//加载完成的图片地址
                                try {
                                    String[] arrayOfString = new String[2];
                                    arrayOfString[0] = URLEncoder.encode(imgPath,"UTF-8");//旧url
                                    arrayOfString[1] = str;
                                    onImageLoadingComplete("onImageLoadingComplete", arrayOfString);
                                } catch (Exception e) {
                                }
                            }
                        });
            }
        });
    }

    @JavascriptInterface
    public void openImage(String imgPath) {
        // 打开图片跳转
    }

    public final void onImageLoadingComplete(String funName, String[] paramArray) {
        String str = "'" + TextUtils.join("','", paramArray) + "'";
        mWebContent.loadUrl("javascript:" + funName + "(" + str + ");");
    }
}
