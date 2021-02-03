package com.example.x_smartcity_2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.GetOrderById;
import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  13:05
 */
public class Wode_dingdan_adapter extends RecyclerView.Adapter<Wode_dingdan_adapter.ViewHolder> {
    private List<GetOrderById> orderByIds;

    public Wode_dingdan_adapter(List<GetOrderById> orderByIds) {
        this.orderByIds = orderByIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_wode_dingdan, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetOrderById getOrderById = orderByIds.get(position);
        holder.txtDidian.setText(getOrderById.getBusiness());
        holder.txtName.setText(getOrderById.getCommodity());
        holder.txtShuliang.setText("数量"+getOrderById.getPrice());
        holder.txtJiage.setText("价格"+getOrderById.getCount());
    }

    @Override
    public int getItemCount() {
        return orderByIds.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtDidian;
        private TextView txtName;
        private TextView txtShuliang;
        private TextView txtJiage;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtDidian = view.findViewById(R.id.txt_didian);
            txtName = view.findViewById(R.id.txt_name);
            txtShuliang = view.findViewById(R.id.txt_shuliang);
            txtJiage = view.findViewById(R.id.txt_jiage);
        }
    }
}