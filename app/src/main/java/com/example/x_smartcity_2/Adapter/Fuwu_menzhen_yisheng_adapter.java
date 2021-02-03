package com.example.x_smartcity_2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.DoctorList;
import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/28  9:24
 */
public class Fuwu_menzhen_yisheng_adapter extends RecyclerView.Adapter<Fuwu_menzhen_yisheng_adapter.ViewHolder> {
    private List<DoctorList> lists;
    private String time1;
    public Item_btn item_btn;

    public Item_btn getItem_btn() {
        return item_btn;
    }

    public void setItem_btn(Item_btn item_btn) {
        this.item_btn = item_btn;
    }

    public interface Item_btn{

        void setonClick(DoctorList list);
    }

    public Fuwu_menzhen_yisheng_adapter(List<DoctorList> doctors, String time, FragmentActivity activity, Context context) {
        this.lists = doctors;
        this.time1 = time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_yiyuan_yisheng, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorList list = lists.get(position);
        holder.name.setText("姓名：" + list.getDoctorName());
        holder.msg.setText("简介：" + list.getDesc());
        holder.jianjie.setText("擅长：" + list.getTag());
        holder.time.setText(time1);

        holder.yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_btn.setonClick(list);
            }
        });
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private TextView name;
        private TextView msg;
        private TextView jianjie;
        private TextView time;
        private TextView yuyue;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            name = view.findViewById(R.id.name);
            msg = view.findViewById(R.id.msg);
            jianjie = view.findViewById(R.id.jianjie);
            time = view.findViewById(R.id.time);
            yuyue = view.findViewById(R.id.btn);
        }
    }
}
