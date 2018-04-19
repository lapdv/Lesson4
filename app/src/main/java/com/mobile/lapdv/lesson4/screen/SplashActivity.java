package com.mobile.lapdv.lesson4.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mobile.lapdv.lesson4.MainActivity;
import com.mobile.lapdv.lesson4.R;

/**
 * Created by lap on 19/04/2018.
 */

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    private Handler mHanler;
    private SplashThreadTask splashThreadTask;
    private SplashRunableTask splashRunableTask;
    private boolean isFinishTask2;
    private boolean isFinishTask1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //cach 1 :
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openActivity();
            }
        }, SPLASH_TIME_OUT);*/

        //cach 2 :
      /*  final Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    openActivity();
                }
            }
        });
        thread.start();*/

        //cach 3 :
       /* SplashThreadTask splashThreadTask = new SplashThreadTask(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("message1" + msg);
                Bundle bundle = msg.getData();
                int data = bundle.getInt(Integer.class.getName());
                System.out.println("data1" + data);
                // openActivity();
            }
        });
        splashThreadTask.start();

        //cach 4
        Thread thread = new Thread(new SplashRunableTask(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("message2" + msg);
                Bundle bundle = msg.getData();
                int data = bundle.getInt(Integer.class.getName());
                System.out.println("data2" + data);
                //openActivity();
            }
        }));
        thread.start();*/


        mHanler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("Message" + Thread.currentThread().getName() + msg.what);
                if (msg != null) {
                    switch (msg.what) {
                        case Config.KEY_THREAD:
                            if (!splashThreadTask.isAlive()) {
                                isFinishTask1 = true;
                                System.out.println("Message1" + msg.what);
                            }
                            //TODO
                            break;
                        case Config.KEY_RUNABLE:
                            if (!splashRunableTask.isAlive()) {
                                isFinishTask2 = true;
                                System.out.println("Message2" + msg.what);
                            }
                            break;
                    }
                    if (isFinishTask1 && isFinishTask2) {
                        openActivity();
                    }
                }
            }
        };

        splashThreadTask = new SplashThreadTask(mHanler, "Thread1");
        splashRunableTask = new SplashRunableTask(mHanler, "Thread2");
        splashThreadTask.startThread();
        splashRunableTask.startThread();
    }

    private void openActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
