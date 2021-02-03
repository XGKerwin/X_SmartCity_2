package com.example.x_smartcity_2.Fragment.zhuye;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Zhuye_huodong_adapter;
import com.example.x_smartcity_2.Bean.GetActionsByType;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  8:32
 */
public class Fragment_huodong extends Fragment {
    private String s;   //活动类型   1 娱乐 2 体育 3 学习 4 政治
    private ImageView back;
    private TextView title;
    private RecyclerView recyclerview;
    private List<GetActionsByType> byTypes;
    private Zhuye_huodong_adapter huodong_adapter;

    public Fragment_huodong(String s) {
        this.s = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_huodong, null);
        initView(view);
        gettitle();
        btn();

        getOKHttp();


        return view;
    }

    private void getOKHttp() {
        if (byTypes == null){
            byTypes = new ArrayList<>();
        }else {
            byTypes.clear();
        }
        new OKHttpTo()
                .setUrl("getActionsByType")
                .setJSONObject("typeid",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        byTypes.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionsByType>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        huodong_adapter = new Zhuye_huodong_adapter(byTypes);
                        recyclerview.setAdapter(huodong_adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_S_huodong());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void gettitle() {
        switch (s){
            case "1":
                title.setText("娱乐");
                break;
            case "2":
                title.setText("体育");
                break;
            case "3":
                title.setText("学习");
                break;
            case "4":
                title.setText("政治");
                break;
        }
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
