package com.sking.aidltest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sking.service.IMyServiceBinder;


public class MainActivity extends Activity implements View.OnClickListener,ServiceConnection {

    private Button startButon;
    private Button stopButon;
    private Button bindButon;
    private Button unbindButon;
    private Button synButon;
    private EditText et;
    private Intent intent;
    private IMyServiceBinder binder;


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        binder = IMyServiceBinder.Stub.asInterface(service);
        Log.w("++++++++++", "onServiceConnected");

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.w("++++++++++", "onServiceDisconnected");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case  R.id.startService:
                Log.w("++++++++++", "startService");
                intent = new Intent();
                intent.setClassName("com.sking.service", "com.sking.service.MyService");
                startService(intent);
                break;

            case  R.id.stopService:
                Log.w("++++++++++","stopService");
                intent = new Intent();
                intent.setClassName("com.sking.service","com.sking.service.MyService");
                stopService(intent);
                break;
            case  R.id.bindService:
                Log.w("++++++++++","bindService");
                intent = new Intent();
                intent.putExtra("sking","sking");
                intent.setClassName("com.sking.service", "com.sking.service.MyService");
                Log.w("++++++++++intent", intent.toString());
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case  R.id.unbindService:
                unbindService(this);
                break;
            case  R.id.SynService:
                try{
                    binder.setData(et.getText().toString());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButon = (Button)findViewById(R.id.startService);
        stopButon = (Button)findViewById(R.id.stopService);
        bindButon = (Button)findViewById(R.id.bindService);
        unbindButon = (Button)findViewById(R.id.unbindService);
        synButon = (Button)findViewById(R.id.SynService);

        et = (EditText)findViewById(R.id.editView);

        startButon.setOnClickListener(this);
        stopButon.setOnClickListener(this);
        bindButon.setOnClickListener(this);
        unbindButon.setOnClickListener(this);
        synButon.setOnClickListener(this);


    }


}
