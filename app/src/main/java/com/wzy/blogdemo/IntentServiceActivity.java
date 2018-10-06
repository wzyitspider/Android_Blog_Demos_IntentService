package com.wzy.blogdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ProgressBar;
import android.widget.TextView;


public class IntentServiceActivity extends Activity {
    ProgressBar progressbar;
    TextView tv_progress;
    public static String ACTION_TYPE_THREAD = "ACTION_TYPE_THREAD";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        progressbar = findViewById(R.id.progressbar);
        tv_progress = findViewById(R.id.tv_progress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_TYPE_THREAD);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(receiver,filter);
        Intent i = new Intent(IntentServiceActivity.this, MyIntentService.class);
        i.putExtra("name","name");
        i.putExtra("params","params");
        startService(i);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(ACTION_TYPE_THREAD.equals(intent.getAction())){

                //更改UI
                int progress = intent.getIntExtra("progress", 0);
                progressbar.setProgress(progress);
                tv_progress.setText(progress + "%");


            }
        }
    }


}
