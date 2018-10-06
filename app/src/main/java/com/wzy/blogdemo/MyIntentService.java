package com.wzy.blogdemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;


public class MyIntentService extends IntentService {
    private boolean isRunning;
    LocalBroadcastManager mLocalBroadcastManager ;

    @Override
    public void onCreate() {
        super.onCreate();
        //注册广播
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            isRunning = true;
            int progress = 0;
            while (isRunning){
                Thread.sleep(100);
                progress++;
                if(progress==100){
                    isRunning = false;
                }
                sendThreadStatus("Runing........",progress);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送进度消息
     */
    private void sendThreadStatus(String status, int progress) {
        Intent intent = new Intent(IntentServiceActivity.ACTION_TYPE_THREAD);
        intent.putExtra("status", status);
        intent.putExtra("progress", progress);
        mLocalBroadcastManager.sendBroadcast(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
