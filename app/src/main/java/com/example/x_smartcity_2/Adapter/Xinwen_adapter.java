package com.example.x_smartcity_2.Adapter;

import android.graphics.Bitmap;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Bean.GetCommitById;
import com.example.x_smartcity_2.Bean.GetNEWsList;
import com.example.x_smartcity_2.R;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.example.x_smartcity_2.net.OkHttpLoImage;
import com.example.x_smartcity_2.net.OkHttpToImage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/28  10:57
 */
public class Xinwen_adapter extends RecyclerView.Adapter<Xinwen_adapter.ViewHolder> {
    private List<GetNEWsList> neWsLists;
    private List<GetCommitById> commit;

    public Xinwen_adapter(List<GetNEWsList> list) {
        this.neWsLists = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_xinwen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetNEWsList list = neWsLists.get(position);
        Log.d("vvvvvvvvvvvvvvvvvv", "onBindViewHolder: ssssssssssssssss"+list.getTitle());
        holder.txtTitle.setText(list.getTitle());
        holder.txt1.setText(list.getAbstractX());
        new OkHttpToImage()
                .setUrl(list.getPicture())
                .setOkHttpLoImage(new OkHttpLoImage() {
                    @Override
                    public void onResponse(Call call, Bitmap bitmap) {
                        holder.img.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Call call, IOException obj) {

                    }
                }).start();

        if (commit == null){
            commit = new ArrayList<>();
        }else {
            commit.clear();
        }
        new OKHttpTo().setUrl("getCommitById")
                .setJSONObject("id",list.getNewsid())
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commit.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetCommitById>>(){}.getType()));
                        if (commit.size()==0){
                            holder.txt2.setText("");
                        }else {
                            holder.txt2.setText("评论："+commit.size()+"      时间"+commit.get(0).getCommitTime());
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();

    }

    @Override
    public int getItemCount() {
        return neWsLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout;
        private ImageView img;
        private TextView txtTitle;
        private TextView txt1;
        private TextView txt2;

        public ViewHolder(@NonNull View view) {
            super(view);
            layout = view.findViewById(R.id.layout);
            img = view.findViewById(R.id.img);
            txtTitle = view.findViewById(R.id.txt_title);
            txt1 = view.findViewById(R.id.txt1);
            txt2 = view.findViewById(R.id.txt2);
        }
    }
}
