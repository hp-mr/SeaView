package com.cl.kira.waveview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by kira on 2017/7/28.
 */
public class WaveView extends View {

    private Path mAbovePath, mBelowWavePath;
    private Paint mAboveWavePaint, mBelowWavePaint;

    private DrawFilter mDrawFilter;

    public static float φ;
    List<Board> boards = new ArrayList();
    private OnWaveAnimationListener mWaveAnimationListener;
    static int height;
    static  int width;
    Context context;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width=MeasureSpec.getSize(widthMeasureSpec);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化路径
        this.context=context;
        mAbovePath = new Path();
        mBelowWavePath = new Path();
        //初始化画笔
        mAboveWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAboveWavePaint.setAntiAlias(true);
        mAboveWavePaint.setStyle(Paint.Style.FILL);
        mAboveWavePaint.setColor(Color.parseColor("#0080FF"));
        mBelowWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowWavePaint.setAntiAlias(true);
        mBelowWavePaint.setStyle(Paint.Style.FILL);
        mBelowWavePaint.setColor(Color.parseColor("#0080FF"));
        mBelowWavePaint.setAlpha(80);
        //画布抗锯齿
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    }
    private void addborads(){
        boards.add(new Board(width,context,0));
        boards.add(new Board(width,context,1));
        boards.add(new Board(width,context,2));
        boards.add(new Board(width,context,3));
        boards.add(new Board(width,context,4));
        boards.add(new Board(width,context,5));
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        addborads();
    }

    static double ω;

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(mDrawFilter);
        mAbovePath.reset();
        mBelowWavePath.reset();
        φ -= 0.1f;

        float y, y2;

        ω = 2 * Math.PI / getWidth();

        mAbovePath.moveTo(getLeft(), getBottom());
        mBelowWavePath.moveTo(getLeft(), getBottom());
        for (float x = 0; x <= getWidth(); x += 20) {
            /**
             *  y=Asin(ωx+φ)+k
             *  A—振幅越大，波形在y轴上最大与最小值的差值越大
             *  ω—角速度， 控制正弦周期(单位角度内震动的次数)
             *  φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
             *  k—偏距，反映在坐标系上则为图像的上移或下移。
             */
            y = (float) (height / 8 * Math.cos(ω * x + φ) + height / 8 * 3 + height / 2);
            y2 = (float) (height / 8 * Math.sin(ω * x + φ) + height / 2);
            mAbovePath.lineTo(x, y);
            mBelowWavePath.lineTo(x, y2);
            //回调 把y坐标的值传出去(在activity里面接收让小机器人随波浪一起摇摆)
            mWaveAnimationListener.OnWaveAnimation(y);

        }
        for(int i=0;i<boards.size();i++){
            boards.get(i).drawBoard(canvas);
        }
        mAbovePath.lineTo(getRight(), getBottom());
        mBelowWavePath.lineTo(getRight(), getBottom());
        canvas.drawPath(mAbovePath, mAboveWavePaint);
        canvas.drawPath(mBelowWavePath, mBelowWavePaint);
        postInvalidateDelayed(20);

    }

    public static float getHeight(int left) {
        return (float) (height / 8 * Math.cos(ω * left + φ) + height / 8 * 3 + height / 2);
    }

    public void setOnWaveAnimationListener(OnWaveAnimationListener l) {
        this.mWaveAnimationListener = l;
    }

    Paint paint = new Paint();

    public interface OnWaveAnimationListener {
        void OnWaveAnimation(float y);
    }

}