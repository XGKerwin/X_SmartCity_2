package com.example.x_smartcity_2.Fragment.fuwu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Fuwu_menzhen_keshi_adapter;
import com.example.x_smartcity_2.Bean.DeptList;
import com.example.x_smartcity_2.Bean.HospitalList;
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
 * date   : 2021/1/27  17:26
 */
public class Fragment_menzhen_keshi extends Fragment {
    private HospitalList hospitalList;
    private ImageView back;
    private TextView title;
    private RecyclerView recyclerview;
    private List<DeptList> deptLists;
    private Fuwu_menzhen_keshi_adapter keshi_adapter;

    public Fragment_menzhen_keshi(HospitalList hospitalId) {
        this.hospitalList = hospitalId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_keshi, container, false);
        initView(view);
        back.setVisibility(View.GONE);
        title.setText("科室选择");

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (deptLists == null){
            deptLists = new ArrayList<>();
        }else {
            deptLists.clear();
        }
        new OKHttpTo()
                .setUrl("deptList")
                .setJSONObject("hospitalId",hospitalList.getHospitalId())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        deptLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DeptList>>(){}.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        keshi_adapter = new Fuwu_menzhen_keshi_adapter(deptLists,getActivity(),hospitalList);
                        recyclerview.setAdapter(keshi_adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        recyclerview = view.findViewById(R.id.recyclerview);
    }
}
