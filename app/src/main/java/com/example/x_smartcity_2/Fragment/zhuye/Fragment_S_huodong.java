package com.example.x_smartcity_2.Fragment.zhuye;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Zhuye_huodong_remen_adapter;
import com.example.x_smartcity_2.Bean.GetActionImages;
import com.example.x_smartcity_2.Bean.GetRecommandAction;
import com.example.x_smartcity_2.Fragment.Fragment_huodongxiangqing;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.Uitl.MyRecyclerView;
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
 * date   : 2021/1/26  13:38
 */
public class Fragment_S_huodong extends Fragment {
    private ImageView back;
    private ViewFlipper viewFlipper;
    private LinearLayout btnYule;
    private LinearLayout btnTiyu;
    private LinearLayout btnXuexi;
    private LinearLayout btnZhengzhi;
    private MyRecyclerView recyclerview;
    private TextView title;

    private List<ImageView> imageViews;
    private List<GetActionImages> imagesList;
    private List<GetRecommandAction> actions;
    private Zhuye_huodong_remen_adapter remen_adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_s_huodong, null);
        initView(view);
        title.setText("活动");
        btn();

        imageViews = new ArrayList<>();

        getImageview();

        getremen();     //热门活动

        return view;
    }

    private void getremen() {
        if (actions == null){
            actions = new ArrayList<>();
        }else {
            actions.clear();
        }
        new OKHttpTo()
                .setUrl("getRecommandAction")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommandAction>>(){}.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerview.setLayoutManager(linearLayoutManager);

                        remen_adapter = new Zhuye_huodong_remen_adapter(actions);
                        recyclerview.setAdapter(remen_adapter);

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getImageview() {
        if (imagesList == null){
            imagesList = new ArrayList<>();
        }else {
            imagesList.clear();
        }
        new OKHttpTo()
                .setUrl("getActionImages")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        imagesList.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionImages>>(){}.getType()));
                        showimg();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showimg() {
        for (int i=0;i<imagesList.size();i++){
            int finalI = i;
            new OkHttpToImage().setUrl(imagesList.get(i).getImage())
                    .setOkHttpLoImage(new OkHttpLoImage() {
                        @Override
                        public void onResponse(Call call, Bitmap bitmap) {
                            try {
                                ImageView imageView = new ImageView(getContext());
                                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
                                imageView.setImageBitmap(bitmap);
                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                                imageViews.add(imageView);
                                imageView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getFragment(new Fragment_S_huodong_xiangqing(imagesList.get(finalI).getId()));
                                    }
                                });
                                if (imageViews.size() == 5){
                                    for (int i = 0; i < imageViews.size(); i++) {
                                        viewFlipper.addView(imageViews.get(i));
                                    }
                                    viewFlipper.startFlipping();
                                }
                            }catch (NullPointerException e){

                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException obj) {

                        }
                    }).start();
        }

    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_zhuye());
            }
        });

        btnYule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong("1"));
            }
        });

        btnTiyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong("2"));
            }
        });

        btnXuexi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong("3"));
            }
        });

        btnZhengzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_huodong("4"));
            }
        });


    }

    public void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        viewFlipper = view.findViewById(R.id.view_flipper);
        btnYule = view.findViewById(R.id.btn_yule);
        btnTiyu = view.findViewById(R.id.btn_tiyu);
        btnXuexi = view.findViewById(R.id.btn_xuexi);
        btnZhengzhi = view.findViewById(R.id.btn_zhengzhi);
        recyclerview = view.findViewById(R.id.recyclerview);
        title = view.findViewById(R.id.title);
    }
}
