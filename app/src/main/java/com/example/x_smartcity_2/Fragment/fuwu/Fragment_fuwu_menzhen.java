package com.example.x_smartcity_2.Fragment.fuwu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Fuwu_menzhen_adapter;
import com.example.x_smartcity_2.Bean.GetRankByHospitalId;
import com.example.x_smartcity_2.Bean.HospitalList;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  13:26
 */
public class Fragment_fuwu_menzhen extends Fragment {

    private ImageView back;
    private EditText edSeek;
    private LinearLayout btnSousuo;
    private RecyclerView recyclerview;
    private List<HospitalList> hospitalLists;
    private List<GetRankByHospitalId> rankByHospitalIds;
    private Fuwu_menzhen_adapter yiyuan_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_huodong_menzhen, container, false);
        initView(view);

        gethospitalList();  //获取所有医院信息

        btn();
        return view;
    }

    private void gethospitalList() {
        if (hospitalLists == null){
            hospitalLists = new ArrayList<>();
        }else {
            hospitalLists.clear();
        }
        new OKHttpTo()
                .setUrl("hospitalList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hospitalLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<HospitalList>>(){}.getType()));
                        Log.d("fffff", "onResponse: "+hospitalLists.size());
                        getxingji();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getxingji() {
        if (rankByHospitalIds == null){
            rankByHospitalIds = new ArrayList<>();
        }else {
            rankByHospitalIds.clear();
        }
        for (int i=1;i<=4;i++){
            new OKHttpTo()
                    .setUrl("getRankByHospitalId")
                    .setJSONObject("hospitalId",i)
                    .setOkHttpLo(new OkHttpLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            GetRankByHospitalId getRankByHospitalId = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString(),
                                    GetRankByHospitalId.class);
                            rankByHospitalIds.add(getRankByHospitalId);

                            if (rankByHospitalIds.size()==4){

                                Collections.sort(rankByHospitalIds, new Comparator<GetRankByHospitalId>() {
                                    @Override
                                    public int compare(GetRankByHospitalId o1, GetRankByHospitalId o2) {
                                        return o1.getHospitalId() - o2.getHospitalId();
                                    }
                                });

                                if (yiyuan_adapter == null){
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerview.setLayoutManager(linearLayoutManager);
                                    yiyuan_adapter = new Fuwu_menzhen_adapter(hospitalLists,rankByHospitalIds,getActivity());
                                }else {
                                    yiyuan_adapter.notifyDataSetChanged();
                                }
                                recyclerview.setAdapter(yiyuan_adapter);
                            }
                        }

                        @Override
                        public void onFailure(IOException obj) {

                        }
                    }).start();
        }



    }

    private void btn() {



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu());
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        edSeek = view.findViewById(R.id.ed_seek);
        btnSousuo = view.findViewById(R.id.btn_sousuo);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
