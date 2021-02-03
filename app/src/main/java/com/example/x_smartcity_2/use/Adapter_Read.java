package com.example.x_smartcity_2.use;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/24  15:23
 */
public class Adapter_Read extends RecyclerView.Adapter<Adapter_Read.ViewHolder> {
    private List<GetNewsByKeys> newsByKeys;
    public ItemOnclick itemOnclick;

    public ItemOnclick getItemOnclick() {
        return itemOnclick;
    }

    public void setItemOnclick(ItemOnclick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }


    public Adapter_Read(List<GetNewsByKeys> newsByKeys) {
        this.newsByKeys = newsByKeys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNewsByKeys byKeys = newsByKeys.get(position);
        holder.txt.setText(byKeys.getTitle());
        holder.img.setImageResource(R.drawable.ic_launcher_foreground);
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnclick.onClick(position,byKeys.getNewsid());
            }
        });
    }


    @Override
    public int getItemCount() {
        return newsByKeys.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    public interface ItemOnclick{
        public void onClick(int pos,String s);
    }

}
