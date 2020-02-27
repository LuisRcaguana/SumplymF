package com.example.sumplym;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sumplym.R;


public class SplashScreenFragment extends Fragment {

    private View v;

    private ImageView ivLOGOSplash;



    public SplashScreenFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_splash_screen, container, false);

        ivLOGOSplash = v.findViewById(R.id.ivLgo);

        Animation aparecer = AnimationUtils.loadAnimation(v.getContext(), R.anim.anim_pantalla_inicio);
        ivLOGOSplash.startAnimation(aparecer);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation despaparecer = AnimationUtils.loadAnimation(v.getContext(), R.anim.anim_app_name);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 2205);

                Animation animTurtle = AnimationUtils.loadAnimation(v.getContext(), R.anim.anim_bienvenida);
                ivLOGOSplash.startAnimation(animTurtle);
            }
        }, 2500);
        return v;

    }

}
