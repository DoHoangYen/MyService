package com.myservice.admin.myservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.play_btn)
    void playSong() {
        Intent intent = new Intent(this, MyService.class);
        intent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        this.startService(intent);
    }

    @OnClick(R.id.stop_btn)
    void stopSong() {
        Intent intent = new Intent(this, MyService.class);
        this.stopService(intent);
    }
}
