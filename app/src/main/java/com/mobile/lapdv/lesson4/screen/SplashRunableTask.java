package com.mobile.lapdv.lesson4.screen;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;


/**
 * Created by lap on 19/04/2018.
 */

public class SplashRunableTask implements Runnable {

    private Handler mHandler;
    private Thread mThread;
    private String threadName;

    public SplashRunableTask(Handler handler, String threadName) {
        mHandler = handler;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        randomWait();
        Message message = mHandler.obtainMessage();
        message.what = Config.KEY_RUNABLE;
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), 999999999);
        message.setData(bundle);
        mHandler.sendMessage(message);
    }

    private void randomWait() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(20);
                System.out.println("randomWait Runable" + i);
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

    public boolean isAlive() {
        return mThread.isAlive();
    }
}
