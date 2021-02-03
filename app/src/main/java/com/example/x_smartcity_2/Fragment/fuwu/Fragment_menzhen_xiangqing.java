package com.example.x_smartcity_2.Fragment.fuwu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_2.App;
import com.example.x_smartcity_2.Bean.GetUserInfo;
import com.example.x_smartcity_2.Bean.HospitalList;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.example.x_smartcity_2.net.OkHttpLoImage;
import com.example.x_smartcity_2.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  14:50
 */
public class Fragment_menzhen_xiangqing extends Fragment {

    private HospitalList list;
    private ImageView image;
    private TextView txtmsg;
    private LinearLayout layout;
    private ImageView back;
    private TextView title;
    private String user;
    private List<GetUserInfo> infos;
    private LinearLayout yuyue;

    public Fragment_menzhen_xiangqing(HospitalList list) {
        this.list = list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menzhen_xiangqing, container, false);
        initView(view);
        title.setText(list.getHospitalName());

        user = App.getUserid();

        getgeren();

        getData();
        btn();

        return view;
    }

    private void getgeren() {
        if (infos == null) {
            infos = new ArrayList<>();
        } else {
            infos.clear();
        }
        new OKHttpTo()
                .setUrl("getUserInfo")
                .setJSONObject("userid", user)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        infos.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetUserInfo>>() {
                                }.getType()));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void btn() {
        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_menzhen_yuyuechaxun(list));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_menzhen());
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    getFragment(new Fragment_menzhen_yuyue(list));
                }

            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void getData() {
        txtmsg.setText(list.getDesc());
        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        image.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

    }

    private void initView(View view) {
        image = view.findViewById(R.id.image);
        txtmsg = view.findViewById(R.id.txtmsg);
        layout = view.findViewById(R.id.layout);
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        yuyue = view.findViewById(R.id.yuyue);
    }
}
