package com.cl.kira.waveview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WaveView waveView= (WaveView) findViewById(R.id.wave);
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
            }
        });
        RelativeLayout fishpool= (RelativeLayout) findViewById(R.id.fishpool);
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));
        fishpool.addView(new FishDrawableView(this));


    }
}
