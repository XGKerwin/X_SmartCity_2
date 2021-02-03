package com.example.x_smartcity_2.Fragment.fuwu;

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

import com.example.x_smartcity_2.Bean.GetViolationsByCarId;
import com.example.x_smartcity_2.R;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  11:00
 */
public class Fragment_fuwu_weizhang2 extends Fragment {
    private GetViolationsByCarId carId;
    private ImageView back;
    private TextView title;
    private TextView didian;
    private TextView txtXingwei;
    private TextView txtShuhao;
    private TextView txtJifen;
    private TextView txt罚款金额;
    private TextView txtTime;

    public Fragment_fuwu_weizhang2(GetViolationsByCarId carId) {
        this.carId = carId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_fuwu_weizhang2, container, false);
        initView(view);
        btn();


        return view;
    }

    private void btn() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    private void initView(View view) {
        back = view.findViewById(R.id.back);
        title = view.findViewById(R.id.title);
        didian = view.findViewById(R.id.didian);
        txtXingwei = view.findViewById(R.id.txt_xingwei);
        txtShuhao = view.findViewById(R.id.txt_shuhao);
        txtJifen = view.findViewById(R.id.txt_jifen);
        txt罚款金额 = view.findViewById(R.id.txt_罚款金额);
        txtTime = view.findViewById(R.id.txt_time);
    }
}
