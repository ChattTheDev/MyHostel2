package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jsibbold.zoomage.ZoomageView;

public class Messmenu extends AppCompatActivity {
    ZoomageView zoomageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messmenu);

        zoomageView = (ZoomageView)findViewById(R.id.myZoomageView);
    }
}
