package com.example.paul.drawview;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    RoundProgressBar progressBar;
    ImageView ivEyeLeft;
    ImageView ivEyeRight;
    Animation animLeft, animRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivEyeLeft = (ImageView) findViewById(R.id.eye_left);
        ivEyeRight = (ImageView) findViewById(R.id.eye_right);

        animLeft = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animLeft.setRepeatCount(Animation.INFINITE);
        animLeft.setDuration(2000);

        animRight = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animRight.setRepeatCount(Animation.INFINITE);
        animRight.setDuration(2000);
        progressBar = (RoundProgressBar) findViewById(R.id.progress);
        ivEyeLeft.setAnimation(animLeft);
        ivEyeRight.setAnimation(animRight);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 101) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = new Message();
                    message.arg1 = i;
                    handler.sendMessage(message);
                    i++;
                }
            }
        }).start();

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            progressBar.setProgress(msg.arg1);
//            animRight = new RotateAnimation((float) (360 * msg.arg1 / 100), (float) (360 * msg.arg1 / 100), Animation.RELATIVE_TO_SELF, 0.5f,
//                    Animation.RELATIVE_TO_SELF, 0.5f);
//            animLeft.setDuration(0);
//            ivEyeLeft.setAnimation(animLeft);

        }
    };
}
