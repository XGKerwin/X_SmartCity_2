package com.example.x_smartcity_2.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.GetActionsByType;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OkHttpLoImage;
import com.example.x_smartcity_2.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/27  8:46
 */
public class Zhuye_huodong_adapter extends RecyclerView.Adapter<Zhuye_huodong_adapter.ViewHolder> {
    private List<GetActionsByType> byTypes;


    public Zhuye_huodong_adapter(List<GetActionsByType> byTypes) {
        this.byTypes = byTypes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_huodong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetActionsByType byType = byTypes.get(position);
        holder.txtTitle.setText(byType.getName());
        holder.txt1.setText(byType.getDescription());
        new OkHttpToImage()
                .setUrl(byType.getImage())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

    }

    @Override
    public int getItemCount() {
        return byTypes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txtTitle;
        private TextView txt1;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txt1 = view.findViewById(R.id.txt1);
        }
    }
}
