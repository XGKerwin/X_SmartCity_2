package com.example.x_smartcity_2.Fragment.xinwen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.x_smartcity_2.Adapter.Xinwen_adapter;
import com.example.x_smartcity_2.Bean.GetNEWsList;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.Uitl.MyRecyclerView;
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
 * date   : 2021/1/25  9:59
 */

public class Fragment_xinwen extends Fragment {
    private TextView txtShizheng;
    private TextView txtYiqing;
    private TextView txtYule;
    private MyRecyclerView myRecyclerView;
    private List<GetNEWsList> neWsLists,list;
    private Xinwen_adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_xinwen, container, false);
        initView(view);
        getOkHttp();
        btn();

        return view;
    }

    private void btn() {

        txtShizheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShow("时政");
            }
        });

        txtYiqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShow("疫情");
            }
        });

        txtYule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShow("娱乐");
            }
        });

    }

    private void getOkHttp() {
        if (neWsLists == null){
            neWsLists = new ArrayList<>();
        }else {
            neWsLists.clear();
        }
        new OKHttpTo()
                .setUrl("getNEWsList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        neWsLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNEWsList>>(){}.getType()));

                        getShow("时政");
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getShow(String s) {

        if (list == null){
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        for (int i=0;i<neWsLists.size();i++){
            GetNEWsList neWsList = neWsLists.get(i);
            if (s.equals(neWsList.getNewsType())){
                list.add(neWsList);
            }
        }

        if (adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            myRecyclerView.setLayoutManager(linearLayoutManager);

            adapter = new Xinwen_adapter(list);
        }else {
            adapter.notifyDataSetChanged();
        }
        myRecyclerView.setAdapter(adapter);

    }

    private void initView(View view) {
        txtShizheng = view.findViewById(R.id.txt_shizheng);
        txtYiqing = view.findViewById(R.id.txt_yiqing);
        txtYule = view.findViewById(R.id.txt_yule);
        myRecyclerView = view.findViewById(R.id.listview);
    }
}
