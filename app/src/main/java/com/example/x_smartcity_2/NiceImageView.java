package com.example.x_smartcity_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/25  10:08
 */
public class NiceImageView extends AppCompatImageView {

    public NiceImageView(@NonNull Context context) {
        super(context);
    }
    private int width = 0,height = 0;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        path.addOval(0,0,width,height,Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
