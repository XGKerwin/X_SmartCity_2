package com.example.x_smartcity_2.net;

import org.json.JSONObject;

import java.io.IOException;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/24  10:57
 */
public interface OkHttpLo {

    void onResponse(JSONObject jsonObject);

    void onFailure(IOException obj);
}
