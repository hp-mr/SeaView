package com.cl.kira.waveview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

/**
 * Created by kira on 2017/7/28.
 */
public class Board {
    int width;Context context;int type;
    int left;
    public Board(int width,Context context, int type) {
        this.width = width;
        this.left=(int)(r.nextInt(100)*width/100);
        this.context = context;
        this.type = type;
    }
    Random r=new Random();
    public void drawBoard(Canvas canvas) {
        Paint paint = new Paint();
        int resource=R.mipmap.board1;
        switch (type){
            case 0:
                resource=R.mipmap.board1;
                break;
            case 1:
                resource=R.mipmap.board2;
                break;
            case 2:
                resource=R.mipmap.board3;
                break;
            case 3:
                resource=R.mipmap.board4;
                break;
            case 4:
                resource=R.mipmap.board5;
                break;
            case 5:
                resource=R.mipmap.board6;
                break;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resource);
        canvas.drawBitmap(bitmap, left, WaveView.getHeight(left)-bitmap.getHeight()/6*5, paint);
        left-=r.nextInt(3)*width/1000;
        if(left<=-bitmap.getWidth()){
            left=width;
        }
    }
}
