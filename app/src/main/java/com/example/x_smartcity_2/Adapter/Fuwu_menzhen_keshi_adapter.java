package com.example.x_smartcity_2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.DeptList;
import com.example.x_smartcity_2.Bean.HospitalList;
import com.example.x_smartcity_2.Fragment.fuwu.Fragment_menzhen_yisheng;
import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/28  8:38
 */
public class Fuwu_menzhen_keshi_adapter extends RecyclerView.Adapter<Fuwu_menzhen_keshi_adapter.ViewHolder> {

    private List<DeptList> deptLists;
    private FragmentActivity activity;
    private HospitalList hospitalList;

    public Fuwu_menzhen_keshi_adapter(List<DeptList> deptLists, FragmentActivity activity, HospitalList hospitalList) {
        this.deptLists = deptLists;
        this.activity = activity;
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_yiyuan_keshi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DeptList list = deptLists.get(position);
        holder.title.setText(list.getDeptName());
        holder.msg.setText("介绍："+list.getDesc());
        holder.zhuyao.setText("主要："+list.getTag());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragment(new Fragment_menzhen_yisheng(hospitalList));
            }
        });
    }

    private void getFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_home,fragment).commit();
    }

    @Override
    public int getItemCount() {
        return deptLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView title;
        private TextView msg;
        private TextView zhuyao;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            title = view.findViewById(R.id.title);
            msg = view.findViewById(R.id.msg);
            zhuyao = view.findViewById(R.id.zhuyao);
        }
    }
}
