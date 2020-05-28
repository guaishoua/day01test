package com.hdj.day01test;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hdj.data.TestInfo;
import com.hdj.myframe.ApiConfig;
import com.hdj.myframe.IContract;
import com.hdj.myframe.LoadTypeConfig;
import com.hdj.myframe.MyModel;
import com.hdj.myframe.MyPresenter;
import com.hdj.myframe.ParamHashMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IContract {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refresh;
    int pageId = 0;
    private MyModel myModel;
    private MyPresenter myPresenter;
    private InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        myModel = new MyModel();
        myPresenter = new MyPresenter(this, myModel);

        final Map<String,Object> paramHashMap = new ParamHashMap().add("c","api").add("a","getList");
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageId = 0;
                myPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH,paramHashMap,pageId);
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageId++;
                myPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE,paramHashMap,pageId);
            }
        });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        refresh = (SmartRefreshLayout) findViewById(R.id.refresh);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        infoAdapter = new InfoAdapter(this);
        recyclerView.setAdapter(infoAdapter);
    }

    @Override
    public void onSuccess(int witchApi, int loadType, Object[] d) {
        switch (witchApi){
            case ApiConfig.TEST_GET:
                if(loadType==LoadTypeConfig.MORE){
                    infoAdapter.setDatasBeans(((TestInfo) d[0]).datas);
                    refresh.finishLoadMore();
                }else if(loadType == LoadTypeConfig.REFRESH){
                    refresh.finishRefresh();
                    infoAdapter.setReDatasBeans(((TestInfo) d[0]).datas);
                }
                break;
        }
    }


    @Override
    public void onFailed(int witchApi, Throwable throwable) {
        Toast.makeText(MainActivity.this, throwable.getMessage()!=null ? throwable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
    }
}
