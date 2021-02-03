package com.example.x_smartcity_2.Fragment.fuwu;

import android.content.Intent;
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

import com.example.x_smartcity_2.Activity.Fuwu_weizhangActivity;
import com.example.x_smartcity_2.Adapter.Fuwu_weizhang_xiangqing_adapter;
import com.example.x_smartcity_2.Bean.GetViolationsByCarId;
import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  9:51
 */
public class Fragment_fuwu_weizhang1 extends Fragment {
    private ImageView back;
    private TextView title;
    private RecyclerView Recyclerview;
    private List<GetViolationsByCarId> byCarIds;
    private Fuwu_weizhang_xiangqing_adapter xiangqing_adapter;
    private TextView gengduo;

    public Fragment_fuwu_weizhang1(List<GetViolationsByCarId> byCarIds) {
        this.byCarIds = byCarIds;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fuwu_weizhang1, container, false);
        initView(view);
        title.setText("违法详情");

        getShow();

        btn();

        return view;
    }

    private void getShow() {
        if (xiangqing_adapter == null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            Recyclerview.setLayoutManager(linearLayoutManager);
            xiangqing_adapter = new Fuwu_weizhang_xiangqing_adapter(byCarIds, 6,getContext());
        } else {
            xiangqing_adapter = new Fuwu_weizhang_xiangqing_adapter(byCarIds, byCarIds.size(),getContext());
        }
        xiangqing_adapter.setItem_fuwu_weizhang_btn(new Fuwu_weizhang_xiangqing_adapter.Item_fuwu_weizhang_btn() {
            @Override
            public void setonClick(GetViolationsByCarId carId) {
                Intent intent = new Intent(getContext(), Fuwu_weizhangActivity.class)
                        .putExtra("1",carId);
                startActivity(intent);
            }
        });

        Recyclerview.setAdapter(xiangqing_adapter);
    }


    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_fuwu_weizhangchaxun());
            }
        });

        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gengduo.setVisibility(View.GONE);
                getShow();
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home, fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        Recyclerview = view.findViewById(R.id.Recyclerview);
        gengduo = view.findViewById(R.id.gengduo);
    }
}
