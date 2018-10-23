package com.bawie.wenhao.model;

import android.os.Handler;
import android.text.TextUtils;

import com.bawie.wenhao.bean.ShowBean;
import com.bawie.wenhao.utils.OkHttpUtils;
import com.bawie.wenhao.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/23.
 */

public class ShowModel {
    Handler handler = new Handler();

    public void getCarts(HashMap<String,String> params, final String url, final ShowCallback showCallback){
        OkHttpUtils.getInstance().postData(url, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if (showCallback!=null){
                    showCallback.failure("网络异常,稍后重试");
                }
            }

            @Override
            public void success(Call call, Response response) {
                try {
                    String string = response.body().string();
                    if (!TextUtils.isEmpty(string)){
                        parseResponse(string,showCallback);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void parseResponse(String string, final ShowCallback showCallback) {
        Gson gson = new Gson();
        final ShowBean showBean = gson.fromJson(string, ShowBean.class);

        if (showCallback!=null && showBean!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showCallback.success(showBean);
                }
            });
        }
    }

    public interface ShowCallback {
        void failure(String msg);

        void success(ShowBean showBean);
    }
}
