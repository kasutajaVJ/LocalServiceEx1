package com.example.vanessa.localserviceex1;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView connectionInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionInfo = findViewById(R.id.text);
        connectionInfo.setText(R.string.disconnected);
    }

    public void onShowDate(View view) {
        if (isServiceRunning(DateTimeService.class))  {
            connectionInfo.setText(DateTimeService.getDate());
        }
    }

    public void onShowTime(View view) {
        if (isServiceRunning(DateTimeService.class)) {
            connectionInfo.setText(DateTimeService.getTime());
        }
    }

    public void onConnectService(View view) {
        Intent intent = new Intent(this, DateTimeService.class);
        startService(intent);

        connectionInfo.setText(R.string.connected);
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
