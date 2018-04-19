package com.mobile.lapdv.lesson4.screen;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by lap on 19/04/2018.
 */

public class SplashThreadTask extends Thread {

    private static int SPLASH_TIME_OUT = 2000;
    private Handler mHandler;
    private Thread mThread;
    private String threadName;

    public SplashThreadTask(Handler handler, String threadName) {
        this.mHandler = handler;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        super.run();
        randomWait();
        Message message = mHandler.obtainMessage();
        message.what = Config.KEY_THREAD;
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), 999999999);
        message.setData(bundle);
        mHandler.sendMessage(message);// gui tra lai cho main thread
    }

    private void randomWait() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(20);
                System.out.println("randomWait Thread" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startThread() {
        if (mThread == null) {
            mThread = new Thread(this, threadName);
            mThread.start();
        }
    }
}
