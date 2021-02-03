package com.example.x_smartcity_2.Fragment.zhuye;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Adapter.Zhuye_huodong_pinglun_adapter;
import com.example.x_smartcity_2.Adapter.Zhuye_huodong_tuijian_adapter;
import com.example.x_smartcity_2.App;
import com.example.x_smartcity_2.Bean.GetActionById;
import com.example.x_smartcity_2.Bean.GetActionCommitById;
import com.example.x_smartcity_2.Bean.GetRecommandAction;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/26  14:41
 */
public class Fragment_S_huodong_xiangqing extends Fragment {

    private String id;
    private ImageView back;
    private TextView title;
    private TextView txtTitle;
    private ImageView img;
    private TextView txtMsg;
    private RecyclerView recyclerviewTuijian;
    private EditText edPinglun;
    private TextView txtTijiao;
    private MyRecyclerView recyclerviewPinglun;
    private List<GetActionById> actionByIds;
    private List<GetRecommandAction> actions;
    private Zhuye_huodong_tuijian_adapter tuijian_adapter;
    private List<GetActionCommitById> commitByIds;
    private Zhuye_huodong_pinglun_adapter pinglun_adapter;
    private String user;
    private TextView txtJiaru;

    public Fragment_S_huodong_xiangqing(String id) {
        this.id = id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_s_huodong_xiqngqing, null);
        initView(view);
        title.setText("活动详情");
        user = App.getUserid();


        getOkHttp();

        getOkHttptuijian(); //推荐活动

        getActionCommitById();      //获取评论

        btn();


        return view;
    }

    private void btn() {
        txtJiaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"您已成功报名\""+actionByIds.get(0).getName()+"\"",Toast.LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_S_huodong());
            }
        });


        txtTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user == null) {
                    Toast.makeText(getContext(), "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    String s = edPinglun.getText().toString();
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "评论内容不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
                        Date date = new Date(System.currentTimeMillis());
                        String str = simpleDateFormat.format(date);
                        getOkHttppinglun(s, str);
                    }
                }

            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void getOkHttppinglun(String s, String str) {
        /**
         * {"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"}
         */
        new OKHttpTo()
                .setUrl("publicActionCommit")
                .setJSONObject("id", id)
                .setJSONObject("userid", user)
                .setJSONObject("commitTime", str)
                .setJSONObject("commitContent", s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        edPinglun.setText("");
                        Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                        getActionCommitById();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getActionCommitById() {
        if (commitByIds == null) {
            commitByIds = new ArrayList<>();
        } else {
            commitByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getActionCommitById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionCommitById>>() {
                                }.getType()));

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewPinglun.setLayoutManager(linearLayoutManager);

                        if (pinglun_adapter == null) {
                            pinglun_adapter = new Zhuye_huodong_pinglun_adapter(commitByIds);
                            recyclerviewPinglun.setAdapter(pinglun_adapter);
                        } else {
                            pinglun_adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttptuijian() {
        if (actions == null) {
            actions = new ArrayList<>();
        } else {
            actions.clear();
        }
        new OKHttpTo()
                .setUrl("getRecommandAction")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actions.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetRecommandAction>>() {
                                }.getType()));
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerviewTuijian.setLayoutManager(linearLayoutManager);

                        tuijian_adapter = new Zhuye_huodong_tuijian_adapter(actions, getActivity());
                        recyclerviewTuijian.setAdapter(tuijian_adapter);
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getOkHttp() {
        if (actionByIds == null) {
            actionByIds = new ArrayList<>();
        } else {
            actionByIds.clear();
        }
        new OKHttpTo()
                .setUrl("getActionById")
                .setJSONObject("id", id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        actionByIds.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetActionById>>() {
                                }.getType()));
                        txtTitle.setText(actionByIds.get(0).getName());
                        txtMsg.setText(actionByIds.get(0).getDescription());
                        getImage(actionByIds.get(0).getImage());
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    private void getImage(String image) {
        new OkHttpToImage()
                .setUrl(image)
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        txtTitle = view.findViewById(R.id.txt_title);
        img = view.findViewById(R.id.img);
        txtMsg = view.findViewById(R.id.txt_msg);
        recyclerviewTuijian = view.findViewById(R.id.recyclerview_tuijian);
        edPinglun = view.findViewById(R.id.ed_pinglun);
        txtTijiao = view.findViewById(R.id.txt_tijiao);
        recyclerviewPinglun = view.findViewById(R.id.recyclerview_pinglun);
        txtJiaru = view.findViewById(R.id.txt_jiaru);
    }
}
