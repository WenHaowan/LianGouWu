package com.bawie.wenhao.view;

import com.bawie.wenhao.bean.ShowBean;

/**
 * Created by HP on 2018/10/23.
 */

public interface ShowView {
    void failure(String msg);
    void success(ShowBean showBean);
}
