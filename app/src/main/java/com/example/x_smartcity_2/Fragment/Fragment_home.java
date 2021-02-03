package com.example.x_smartcity_2.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_2.Fragment.dangjian.Fragment_dangjian;
import com.example.x_smartcity_2.Fragment.fuwu.Fragment_fuwu;
import com.example.x_smartcity_2.Fragment.wode.Fragment_wode;
import com.example.x_smartcity_2.Fragment.xinwen.Fragment_xinwen;
import com.example.x_smartcity_2.Fragment.zhuye.Fragment_S_huodong;
import com.example.x_smartcity_2.Fragment.zhuye.Fragment_huodong;
import com.example.x_smartcity_2.Fragment.zhuye.Fragment_zhuye;
import com.example.x_smartcity_2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  9:23
 */

public class Fragment_home extends Fragment {
    private BottomNavigationView bottomNav;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_home, null);
        initView(view);

        getFragment(new Fragment_zhuye());

        btnnav();

        return view;
    }

    @SuppressLint("WrongConstant")
    private void btnnav() {
        bottomNav.setLabelVisibilityMode(1);    //显示下列文字
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.bom_home:
                        getFragment(new Fragment_zhuye());
                        break;
                    case R.id.bom_fuwu:
                        getFragment(new Fragment_fuwu());
                        break;
                    case R.id.bom_dangjian:
                        getFragment(new Fragment_S_huodong());
                        break;
                    case R.id.bom_xinwen:
                        getFragment(new Fragment_xinwen());
                        break;
                    case R.id.bom_wode:
                        getFragment(new Fragment_wode());
                        break;
                }
                return true;
            }
        });
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        bottomNav = view.findViewById(R.id.bottom_nav);
    }
}
