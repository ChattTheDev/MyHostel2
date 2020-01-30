package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompliantRegistration extends AppCompatActivity {
    EditText entername, enterroll, enterroom, enterhostel, entercomplaint;
    Button sendcomplaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compliant_registration);
        entername = (EditText)findViewById(R.id.enteryourname);
        enterroll = (EditText)findViewById(R.id.enteryourroll);
        enterroom = (EditText)findViewById(R.id.enteryourroom);
        enterhostel = (EditText)findViewById(R.id.enterhostel);
        entercomplaint = (EditText)findViewById(R.id.writecomplaint);

        sendcomplaint = (Button)findViewById(R.id.buttonsend);

        sendcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = entername.getText().toString();
                String b = enterroll.getText().toString();
                String c = enterroom.getText().toString();
                String d = enterhostel.getText().toString();
                String e = entercomplaint.getText().toString();

                Intent i = new Intent(getApplicationContext(), Wardenviewcomplaints.class);
                i.putExtra("Name", a);
                i.putExtra("Roll", b);
                i.putExtra("Room", c);
                i.putExtra("Hostel", d);
                i.putExtra("Complaint", e);
                startActivity(i);

            }
        });


    }
}
