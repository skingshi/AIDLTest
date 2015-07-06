package com.sking.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    private String data = "default";
    private boolean flag = false;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.w("++++++++++","onBind");
       return new IMyServiceBinder.Stub()
       {
           @Override
           public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

           }

           @Override
           public void setData(String data) throws RemoteException {
               MyService.this.data = data;
           }
       };
    }

    @Override
    public void onCreate() {
        Log.w("++++++++++","onCreate");
        flag = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag)
                {

                    Log.w("++++++++", MyService.this.data);
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }


            }
        }).start();



        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("++++++++++","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        flag = false;
        Log.w("++++++++++","onDestroy");
        super.onDestroy();
    }
}
