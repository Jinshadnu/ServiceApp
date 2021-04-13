package com.example.serviceapp.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.serviceapp.MainActivity;
import com.example.serviceapp.R;
import com.example.serviceapp.databinding.ActivitySplashBinding;
import com.example.serviceapp.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    public ActivitySplashBinding splashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splashBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash);

        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
            //Animation animation1= AnimationUtils.loadAnimation(this,R.anim.left_animation);

        splashBinding.lottieLayerName.startAnimation(animation);
        //splashBinding.imageViewLogo.startAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);

                finish();

            }
        },SPLASH_TIME_OUT);


    }
}