package com.app.workmanagerexample;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {

    public static String KEY_WORKER = "key_worker";

    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {

        //getting data
        Data data = getInputData();
        int countLimit = data.getInt(MainActivity.KEY_COUNT_VALUE, 0);
        for (int i = 0; i < countLimit; i++) {
            if (i == 100000) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Run your task here
                        Toast.makeText(getApplicationContext(), "Work Manager", Toast.LENGTH_LONG).show();
                    }
                }, 1000);

            }
            Log.i("MyTag", "Count is " + i);
        }
        //sending data
        Data dataToSend = new Data.Builder()
                .putString(KEY_WORKER, " Task Done Successfully")
                .build();
        return Result.success(dataToSend);
    }
}
