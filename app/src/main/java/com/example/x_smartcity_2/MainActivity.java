package com.example.x_smartcity_2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.x_smartcity_2.Fragment.aPage.Fragment1;
import com.example.x_smartcity_2.net.OKHttpTo;
import com.example.x_smartcity_2.net.OkHttpLo;
import com.example.x_smartcity_2.use.Adapter_Read;
import com.example.x_smartcity_2.use.GetNewsByKeys;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView RecyclerView;
    private List<GetNewsByKeys> newsByKeys;
    public Adapter_Read.ItemOnclick itemOnclick;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getFragment(new Fragment1());


    }

    private void getFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment).commit();
    }

    private void getOkHttp() {
        if (newsByKeys == null){
            newsByKeys = new ArrayList<>();
        }else {
            newsByKeys.clear();
        }
        new OKHttpTo()
                .setUrl("getNewsByKeys")
                .setJSONObject("keys","")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsByKeys.addAll(new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<GetNewsByKeys>>(){}.getType()));

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
                        RecyclerView.setLayoutManager(gridLayoutManager);
                        Adapter_Read adapter = new Adapter_Read(newsByKeys);

                        adapter.setItemOnclick(new Adapter_Read.ItemOnclick() {
                            @Override
                            public void onClick(int pos, String s) {
                                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                            }
                        });

                        RecyclerView.scrollToPosition(5);
                        RecyclerView.setAdapter(adapter);


                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

}