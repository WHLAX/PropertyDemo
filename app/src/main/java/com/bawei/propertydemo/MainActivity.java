package com.bawei.propertydemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv;
    private Button animation_alpha;
    private Button animation_scale;
    private Button animation_rotate;
    private Button animation_translate;
    private Button animation_group1;
    private Button animation_group2;
    private Button animation_group3;
    private Button animation_group4;
    private Button animation_frame;
    private Button animation_layout;
    private Button animation_activity;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        animation_alpha = (Button) findViewById(R.id.animation_alpha);
        animation_scale = (Button) findViewById(R.id.animation_scale);
        animation_rotate = (Button) findViewById(R.id.animation_rotate);
        animation_translate = (Button) findViewById(R.id.animation_translate);
        animation_group1 = (Button) findViewById(R.id.animation_group1);
        animation_group2 = (Button) findViewById(R.id.animation_group2);
        animation_group3 = (Button) findViewById(R.id.animation_group3);
        animation_group4 = (Button) findViewById(R.id.animation_group4);
        animation_frame = (Button) findViewById(R.id.animation_frame);
        animation_layout = (Button) findViewById(R.id.animation_layout);
        animation_activity = (Button) findViewById(R.id.animation_activity);

        animation_alpha.setOnClickListener(this);
        animation_scale.setOnClickListener(this);
        animation_rotate.setOnClickListener(this);
        animation_translate.setOnClickListener(this);
        animation_group1.setOnClickListener(this);
        animation_group2.setOnClickListener(this);
        animation_group3.setOnClickListener(this);
        animation_group4.setOnClickListener(this);
        animation_frame.setOnClickListener(this);
        animation_layout.setOnClickListener(this);
        animation_activity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        iv.setBackgroundColor(Color.TRANSPARENT);
        switch (v.getId()) {
            case R.id.animation_alpha:
                animator = ObjectAnimator.ofFloat(iv, "p", 0f, 1f);
                animator.setDuration(3000);
                animator.start();
                break;
            case R.id.animation_scale:
                PropertyValuesHolder scx = PropertyValuesHolder.ofFloat("scx", 0f, 1f);
                PropertyValuesHolder scy = PropertyValuesHolder.ofFloat("scy", 0f, 1f);
                animator.ofPropertyValuesHolder(iv,scx,scy).setDuration(2000).start();
                break;
            case R.id.animation_rotate:
                ObjectAnimator objectAnimatorScale = ObjectAnimator.ofFloat(iv, "rotation", 0f, 360f);
                objectAnimatorScale.setDuration(1000);
                objectAnimatorScale.start();
                break;
            case R.id.animation_translate:
                ObjectAnimator objectAnimatorTranslate = ObjectAnimator.ofFloat(iv, "translationX", 0f, 500f);
                objectAnimatorTranslate.setDuration(1000);
                objectAnimatorTranslate.start();
                break;
            case R.id.animation_group1:

                AnimatorSet Set = new AnimatorSet();
                ObjectAnimator objectAnimatorScaleX1 = ObjectAnimator.ofFloat(iv, "scX", 0f, 1f);
                ObjectAnimator objectAnimatorScaleY1 = ObjectAnimator.ofFloat(iv, "scY", 0f, 1f);
                ObjectAnimator objectAnimatorRotateX1 = ObjectAnimator.ofFloat(iv, "rotX", 0f, 360f);
                ObjectAnimator objectAnimatorRotateY1 = ObjectAnimator.ofFloat(iv, "rotY", 0f, 360f);
                Set.setDuration(1000);
                Set.play(objectAnimatorScaleX1).with(objectAnimatorScaleY1)
                        .before(objectAnimatorRotateX1).before(objectAnimatorRotateY1);
                Set.start();
                break;
            case R.id.animation_group2:
                AnimatorSet Set2 = new AnimatorSet();
                ObjectAnimator objectAnimatorTranslate2 = ObjectAnimator.ofFloat(iv, "tX", 0f, 500f);
                ObjectAnimator objectAnimatorRotateX2 = ObjectAnimator.ofFloat(iv, "rX", 0f, 360f);
                ObjectAnimator objectAnimatorRotateY2 = ObjectAnimator.ofFloat(iv, "rY", 0f, 360f);
                Set2.setDuration(1000);
                Set2.play(objectAnimatorTranslate2).after(objectAnimatorRotateX2)
                        .after(objectAnimatorRotateY2);
                Set2.start();
                break;
            case R.id.animation_group3:
                ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(iv, "alpha", 0f, 1f);
                objectAnimator2.setDuration(500);
                objectAnimator2.setRepeatCount(3);
                objectAnimator2.start();
                break;
            case R.id.animation_group4:
                ObjectAnimator objectAnimatorTranslate3 = ObjectAnimator.ofFloat(iv, "translationX", -50f, 50f);
                objectAnimatorTranslate3.setDuration(500);
                objectAnimatorTranslate3.setRepeatCount(3);
                objectAnimatorTranslate3.start();
                break;
            case R.id.animation_frame:
                ObjectAnimator objectAnimatorBg = ObjectAnimator.ofInt(iv, "backgroundColor", Color.BLUE, Color.YELLOW, Color.RED);
                objectAnimatorBg.setDuration(3000);
                objectAnimatorBg.start();
                break;
            case R.id.animation_layout:

                break;
            case R.id.animation_activity:
                playWithAfter();
                break;
        }
    }
    public void playWithAfter() {
        float cx = iv.getX();

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(iv, "scaleX",
                1.0f, 2f);
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(iv, "scaleY",
                1.0f, 2f);
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(iv,
                "x", cx, 0f);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(iv,
                "x", cx);

        /**
         * anim1，anim2,anim3同时执行
         * anim4接着执行
         */
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.play(anim2).with(anim3);
        animSet.play(anim4).after(anim3);
        animSet.setDuration(1000);
        animSet.start();
    }
}
