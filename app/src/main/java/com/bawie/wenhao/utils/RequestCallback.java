package com.bawie.wenhao.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by HP on 2018/10/23.
 */

public interface RequestCallback {
    void failure(Call call, IOException e);
    void success(Call call, Response response);
}
