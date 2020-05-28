package com.hdj.day01test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hdj.data.TestInfo;


import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private Context context;
    private List<TestInfo.DataInfo> datasBeans = new ArrayList<>();


    public InfoAdapter(Context context) {
        this.context = context;
    }

    private static final String TAG = "InfoAdapter";

    public void setDatasBeans(List<TestInfo.DataInfo> datasBeans) {
        this.datasBeans.addAll(datasBeans);
        notifyDataSetChanged();
    }

    public void setReDatasBeans(List<TestInfo.DataInfo> datasBeans) {
        this.datasBeans.clear();
        this.datasBeans.addAll(datasBeans);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_layout, viewGroup, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder( final ViewHolder viewHolder, final int i) {
        TestInfo.DataInfo dataInfo = datasBeans.get(i);


        Glide.with(context).load(dataInfo.thumbnail).apply(RequestOptions.circleCropTransform()).into(viewHolder.iv_img);
        viewHolder.tv_title.setText(dataInfo.title);
        viewHolder.tv_name.setText(dataInfo.author);

    }


    @Override
    public int getItemCount() {
        return datasBeans.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iv_img;
        public TextView tv_title;
        public TextView tv_name;
        public Button btn_sure;
        public Button btn_cancel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }

}
