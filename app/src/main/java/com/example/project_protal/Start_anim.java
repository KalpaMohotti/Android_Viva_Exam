package com.example.project_protal;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Start_anim extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_anim);
        startanim();

            runnable=new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Start_anim.this.finish();

                }
            };

            handler=new Handler();
            handler.postDelayed(runnable,2000);

    }
    public  void  startanim(){
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.animation_flash_scrren);
        animation.setDuration(1000);
        animation.setFillAfter(true); //animation eka nawathina thana

        LinearLayout l1=findViewById(R.id.liniyer1);
        l1.startAnimation(animation);
    }

}


