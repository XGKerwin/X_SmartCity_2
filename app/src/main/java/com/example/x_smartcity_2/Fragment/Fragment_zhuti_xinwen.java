package com.example.x_smartcity_2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Zhuye_zhuti_show_adapter;
import com.example.x_smartcity_2.Bean.GetNEWsList;
import com.example.x_smartcity_2.Bean.GetNewsInfoBySubject;
import com.example.x_smartcity_2.Fragment.zhuye.Fragment_zhuye;
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
 * date   : 2021/1/26  9:49
 */
public class Fragment_zhuti_xinwen extends Fragment {
    private String string;
    private ImageView back;
    private RecyclerView recyclerview;
    private List<GetNewsInfoBySubject> infoBySubjects;
    private List<GetNEWsList> neWsLists,list;
    private Zhuye_zhuti_show_adapter show_adapter;

    public Fragment_zhuti_xinwen(String s) {
        this.string = s;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_zhuti_xinwen, null);
        initView(view);

        getOkHttp();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye());
            }
        });

        return view;
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void getOkHttp() {
        if (infoBySubjects == null){
            infoBySubjects = new ArrayList<>();
        }else {
            infoBySubjects.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsInfoBySubject")
                .setJSONObject("subject",string)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        infoBySubjects.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNewsInfoBySubject>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
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
                        getshow();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getshow() {
        if (list == null){
            list = new ArrayList<>();
        }else {
            list.clear();
        }
        for (int i=0;i<infoBySubjects.size();i++){
            GetNewsInfoBySubject info = infoBySubjects.get(i);
            for (int j=0;j<neWsLists.size();j++){
                GetNEWsList n = neWsLists.get(j);
                if (info.getNewsid().equals(n.getNewsid())){
                    list.add(n);
                    if (list.size()==infoBySubjects.size()){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);
                        show_adapter = new Zhuye_zhuti_show_adapter(list,getActivity());
                        recyclerview.setAdapter(show_adapter);
                    }
                }
            }
        }

    }


    private void initView(View view) {
        back = view.findViewById(R.id.back);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
