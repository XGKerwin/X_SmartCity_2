package com.example.x_smartcity_2.Adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.x_smartcity_2.Activity.Fuwu_weizhangActivity;
import com.example.x_smartcity_2.Bean.GetViolationsByCarId;
import com.example.x_smartcity_2.Fragment.fuwu.Fragment_fuwu_weizhang2;
import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  10:27
 */
public class Fuwu_weizhang_xiangqing_adapter extends RecyclerView.Adapter<Fuwu_weizhang_xiangqing_adapter.ViewHolder> {

    private List<GetViolationsByCarId> byCarIds;
    private int i;
    private Context context;
    public Item_fuwu_weizhang_btn item_fuwu_weizhang_btn;

    public Item_fuwu_weizhang_btn getItem_fuwu_weizhang_btn() {
        return item_fuwu_weizhang_btn;
    }

    public void setItem_fuwu_weizhang_btn(Item_fuwu_weizhang_btn item_fuwu_weizhang_btn) {
        this.item_fuwu_weizhang_btn = item_fuwu_weizhang_btn;
    }

    public interface Item_fuwu_weizhang_btn{

        void setonClick(GetViolationsByCarId carId);
    }

    public Fuwu_weizhang_xiangqing_adapter(List<GetViolationsByCarId> byCarIds, int size, Context context) {
        this.byCarIds = byCarIds;
        this.i = size;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_weizhang_xiangqing, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetViolationsByCarId carId = byCarIds.get(position);
        holder.time.setText("时间：" + carId.getTime());
        holder.didian.setText(carId.getPlace());
        holder.fakuan.setText("罚款" + carId.getCost());
        holder.jifen.setText("扣分：" + carId.getDeductPoints());
        switch (carId.getStatus()) {
            case "1":
                holder.zhuangtai.setText("状态：已处理");
                break;
            case "2":
                holder.zhuangtai.setText("状态：未处理");
                break;
        }

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_fuwu_weizhang_btn.setonClick(carId);
//                new Fragment_fuwu_weizhang2()
            }
        });


    }


    @Override
    public int getItemCount() {
        return i;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView didian;
        private TextView zhuangtai;
        private TextView fakuan;
        private TextView jifen;
        private TextView time;
        private LinearLayout layout;

        public ViewHolder(@NonNull View view) {
            super(view);
            didian = view.findViewById(R.id.didian);
            zhuangtai = view.findViewById(R.id.zhuangtai);
            fakuan = view.findViewById(R.id.fakuan);
            jifen = view.findViewById(R.id.jifen);
            time = view.findViewById(R.id.time);
            layout = view.findViewById(R.id.layout);
        }
    }
}
