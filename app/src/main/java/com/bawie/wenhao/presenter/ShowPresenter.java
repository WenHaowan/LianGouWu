package com.bawie.wenhao.presenter;

import com.bawie.wenhao.bean.ShowBean;
import com.bawie.wenhao.model.ShowModel;
import com.bawie.wenhao.view.ShowView;

import java.util.HashMap;

/**
 * Created by HP on 2018/10/23.
 */

public class ShowPresenter {

    private ShowModel showModel;
    private ShowView showView;

    public ShowPresenter(ShowView showView) {
        this.showView = showView;
        showModel = new ShowModel();
    }

    public void getCarts(HashMap<String,String> params,String url){
        showModel.getCarts(params, url, new ShowModel.ShowCallback() {
            @Override
            public void failure(String msg) {
                if (showView!=null){
                    showView.failure(msg);
                }
            }

            @Override
            public void success(ShowBean showBean) {
                if (showView!=null){
                    showView.success(showBean);
                }
            }
        });
    }

    /**
     * 解除绑定，把view和presenter解绑，避免内存泄漏
     */
    public void detachView(){
        this.showView = null;
    }
}
