package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Wardenviewcomplaints extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardenviewcomplaints);

        t1 = (TextView)findViewById(R.id.showname);
        t2 = (TextView)findViewById(R.id.showroll);
        t3 = (TextView)findViewById(R.id.showroom);
        t4 = (TextView)findViewById(R.id.showhostel);
        t5 = (TextView)findViewById(R.id.showcomplaint);

        /*Intent intent = getIntent();
        String str = intent.getStringExtra("Name");
        String str2 = intent.getStringExtra("Roll");
        String str3 = intent.getStringExtra("Room");
        String str4 = intent.getStringExtra("Hostel");
        String str5 = intent.getStringExtra("Complaint");

        t1.setText(str);
        t2.setText(str2);
        t3.setText(str3);
        t4.setText(str4);
        t5.setText(str5);*/

    }
}
