package com.yang.mydouban.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yang.mydouban.R;
import com.yang.mydouban.been.ZHDailyListResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengzhaoyang_i on 2017/6/9.
 */

public class TopStoriesListAdapter extends RecyclerView.Adapter<TopStoriesListAdapter.TopStoriesListViewHolder> {

    private Context context;
    private List<ZHDailyListResult.TopStories> mData;

    public TopStoriesListAdapter(Context context){
        try {
            this.context = context;
            mData = new ArrayList<>();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    @Override
    public TopStoriesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rcv_zhdaily,parent,false);
        return new TopStoriesListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TopStoriesListViewHolder holder, int position) {
        holder.setDataToView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ZHDailyListResult.TopStories getItem(int position){
        return mData.get(position);
    }

    public void setListData(List<ZHDailyListResult.TopStories> mData) {
        this.mData = mData;
    }

    class TopStoriesListViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvtitle;
        private ImageView mIvImg;

        public TopStoriesListViewHolder(View itemView) {
            super(itemView);
            mTvtitle = (TextView)itemView.findViewById(R.id.tv_title);
            mIvImg = (ImageView)itemView.findViewById(R.id.iv_img);
        }

        public void setDataToView(ZHDailyListResult.TopStories data){
            mTvtitle.setText(data.getTitle());
            Glide.with(context)
                    .load(data.getImage())
                    .error(R.mipmap.ic_launcher)
                    .fitCenter()
                    .centerCrop()
                    .into(mIvImg);
        }
    }
}
