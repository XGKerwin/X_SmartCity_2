package com.example.x_smartcity_2.Fragment.aPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_smartcity_2.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  9:23
 */
public class Fragment_wlsz extends Fragment {
    private EditText edIp;
    private EditText edDuankou;
    private Button btnXiugai;
    private Button btnBaocun;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_wlsz, null);
        initView(view);

        edIp.setFocusable(false);
        edDuankou.setFocusable(false);
        edIp.setFocusableInTouchMode(false);
        edDuankou.setFocusableInTouchMode(false);

        btnXiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edIp.setFocusableInTouchMode(true);
                edDuankou.setFocusableInTouchMode(true);
                edIp.setFocusable(true);
                edDuankou.setFocusable(true);
                edIp.requestFocus();
            }
        });

        btnBaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = edIp.getText().toString();
                String duankou = edDuankou.getText().toString();
                if (ip.equals("")){
                    Toast.makeText(getContext(),"ip地址不能为空",Toast.LENGTH_SHORT).show();
                }else if (duankou.equals("")){
                    Toast.makeText(getContext(),"端口号不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    edIp.setFocusableInTouchMode(false);
                    edDuankou.setFocusableInTouchMode(false);
                    edIp.setFocusable(false);
                    edDuankou.setFocusable(false);
                    Toast.makeText(getContext(),"保存成功",Toast.LENGTH_SHORT).show();
                    getFragment(new Fragment_5());
                }
            }
        });


        return view;
    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment).commit();
    }

    private void initView(View view) {
        edIp = view.findViewById(R.id.ed_ip);
        edDuankou = view.findViewById(R.id.ed_duankou);
        btnXiugai = view.findViewById(R.id.btn_xiugai);
        btnBaocun = view.findViewById(R.id.btn_baocun);
    }
}
