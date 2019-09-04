package com.example.myhostel;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainWardenDashboard extends AppCompatActivity {
    Button noticeupload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_warden_dashboard);
        noticeupload = (Button)findViewById(R.id.noticeuploading);

        noticeupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainWardenDashboard.this, WardenNoticeUpload.class);
                startActivity(i);
            }
        });
    }
}
