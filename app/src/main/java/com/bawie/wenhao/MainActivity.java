package com.bawie.wenhao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bawie.wenhao.adapter.CartAdapter;
import com.bawie.wenhao.adapter.ShowAdapter;
import com.bawie.wenhao.bean.ShowBean;
import com.bawie.wenhao.common.ShowCommon;
import com.bawie.wenhao.presenter.ShowPresenter;
import com.bawie.wenhao.view.ShowView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShowView{

    private XRecyclerView x_recy_view;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        x_recy_view = (XRecyclerView) findViewById(R.id.x_recy_view);
        x_recy_view.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

        x_recy_view.setLoadingMoreEnabled(true);

        x_recy_view.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });
    }

    private void initView() {
        loadData();
    }

    private void loadData() {
        HashMap<String, String> hashMap = new HashMap<>();
        ShowPresenter presenter = new ShowPresenter(MainActivity.this);
        presenter.getCarts(hashMap, ShowCommon.GETCARTS);
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(ShowBean showBean) {
        List<ShowBean.DataBean> data = showBean.getData();
        CartAdapter adapter = new CartAdapter(MainActivity.this,data);
        x_recy_view.setAdapter(adapter);
        x_recy_view.refreshComplete();
    }
}
