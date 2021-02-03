package com.example.x_smartcity_2.Fragment.fuwu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Fuwu_menzhen_yisheng_adapter;
import com.example.x_smartcity_2.App;
import com.example.x_smartcity_2.Bean.DoctorList;
import com.example.x_smartcity_2.Bean.HospitalList;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/28  9:03
 */
public class Fragment_menzhen_yisheng extends Fragment {

    private ImageView back;
    private TextView title;
    private TextView txtZanwu;
    private RecyclerView recyclerview;
    private TextView txtPutong;
    private TextView txtZhuanjia;
    private List<DoctorList> doctorLists,doctors;
    private HospitalList hospitalList;
    private Fuwu_menzhen_yisheng_adapter yisheng_adapter;
    private String name;
    private String sex;
    private String sfz;
    private String tel;
    private String csrq;
    private String dizhi;

    public Fragment_menzhen_yisheng(HospitalList hospitalList) {
        this.hospitalList = hospitalList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_yisheng, container, false);
        initView(view);
        title.setText("医生选择");
        back.setVisibility(View.GONE);

        getOkHttp();



        btn();

        return view;
    }

    private void getOkHttp() {
        if (doctorLists == null){
            doctorLists = new ArrayList<>();
        }else {
            doctorLists.clear();
        }
        new OKHttpTo()
                .setUrl("doctorList")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        doctorLists.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<DoctorList>>(){}.getType()));
                        getData();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private String time;
    private void getData() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        time = str+"下午14：00";

        if (doctors == null){
            doctors = new ArrayList<>();
        }else {
            doctors.clear();
        }

        for (int i=0;i<doctorLists.size();i++){
            DoctorList list = doctorLists.get(i);
            if (list.getHospitalId().equals(hospitalList.getHospitalId())){
                doctors.add(list);
            }
        }
        if (yisheng_adapter == null){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(linearLayoutManager);

            yisheng_adapter = new Fuwu_menzhen_yisheng_adapter(doctors,time,getActivity(),getContext());
            recyclerview.setAdapter(yisheng_adapter);
        }else {
            yisheng_adapter.notifyDataSetChanged();
        }

        yisheng_adapter.setItem_btn(new Fuwu_menzhen_yisheng_adapter.Item_btn() {
            @Override
            public void setonClick(DoctorList list) {
                 name = App.getName();
                 sex = App.getSex();
                 sfz = App.getSfz();
                 tel = App.getTel();
                 csrq = App.getCsrq();
                 dizhi = App.getDizhi();
                /**
                 * {"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
                 */
                getcreateCase(list);        //就诊卡


            }
        });


    }

    private void getcreateCase(DoctorList list) {
        /**
         * {name:"杨仪涵",sex:"女",ID:"371402201002078824",birthday:"2010-2-7",tel:"15905346666",address:"八一小区"}
         */
        new OKHttpTo()
                .setUrl("createCase")
                .setJSONObject("name",name)
                .setJSONObject("sex",sex)
                .setJSONObject("ID",sfz)
                .setJSONObject("birthday",csrq)
                .setJSONObject("tel",tel)
                .setJSONObject("address",dizhi)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getappointment(list);   //预约挂号
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getappointment(DoctorList list) {
        /**
         * {"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
         */
        new OKHttpTo()
                .setUrl("appointment")
                .setJSONObject("pid",sfz)
                .setJSONObject("name",name)
                .setJSONObject("phone",tel)
                .setJSONObject("doctorId",list.getDoctorId())
                .setJSONObject("appTime",time)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("DDDDD", "onResponse: "+time);
                        Log.d("DDDDD", "onResponse: "+list.getDoctorId());
                        Toast.makeText(getContext(),"预约成功",Toast.LENGTH_SHORT).show();
                        getFragment(new Fragment_menzhen_xiangqing(hospitalList));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void btn() {
        txtPutong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtZanwu.setVisibility(View.GONE);
                recyclerview.setVisibility(View.VISIBLE);
            }
        });

        txtZhuanjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerview.setVisibility(View.GONE);
                txtZanwu.setVisibility(View.VISIBLE);
            }
        });

    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtZanwu = view.findViewById(R.id.txt_zanwu);
        recyclerview = view.findViewById(R.id.recyclerview);
        txtPutong = view.findViewById(R.id.txt_putong);
        txtZhuanjia = view.findViewById(R.id.txt_zhuanjia);
    }
}
