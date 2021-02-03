package com.example.x_smartcity_2.Fragment.aPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_2.Fragment.Fragment_home;
import com.example.x_smartcity_2.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  9:12
 */
public class Fragment_5 extends Fragment {
    private Button btnWlsz;
    private Button btnStart;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_5, null);
        initView(view);

        //进入首页
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_home());
            }
        });

        //网路设置
        btnWlsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_wlsz());
            }
        });



        return view;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment).commit();
    }

    private void initView(View view) {
        btnWlsz = view.findViewById(R.id.btn_wlsz);
        btnStart = view.findViewById(R.id.btn_start);
    }
}
