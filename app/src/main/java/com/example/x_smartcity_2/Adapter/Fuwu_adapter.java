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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.GetServiceByType;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OkHttpLoImage;
import com.example.x_smartcity_2.net.OkHttpToImage;

import java.io.IOException;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/26  11:12
 */
public class Fuwu_adapter extends RecyclerView.Adapter<Fuwu_adapter.ViewHolder> {
    private List<GetServiceByType> serviceByTypes;
    private FragmentActivity activity;
    public Item_fuwu_btn item_fuwu_btn;

    public Item_fuwu_btn getItem_fuwu_btn() {
        return item_fuwu_btn;
    }

    public void setItem_fuwu_btn(Item_fuwu_btn item_fuwu_btn) {
        this.item_fuwu_btn = item_fuwu_btn;
    }

    public interface Item_fuwu_btn{

        void setonClick(String serviceName);
    }

    public Fuwu_adapter(List<GetServiceByType> typeList, FragmentActivity activity) {
        this.serviceByTypes = typeList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fuwu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetServiceByType byType = serviceByTypes.get(position);
        holder.fuwuTxt.setText(byType.getServiceName());

        new OkHttpToImage()
                .setUrl(byType.getIcon())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.fuwuImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item_fuwu_btn.setonClick(byType.getServiceName());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (serviceByTypes.size() == 0) return 0;
        return serviceByTypes.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView fuwuImg;
        private TextView fuwuTxt;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            fuwuImg = view.findViewById(R.id.fuwu_img);
            fuwuTxt = view.findViewById(R.id.fuwu_txt);
        }
    }
}
