package com.example.myhostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginWarden extends AppCompatActivity {

    EditText email, password;
    Button login;
    TextView t1;
    int still;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_warden);

        email = (EditText) findViewById(R.id.email121);
        password = (EditText) findViewById(R.id.pass121);
        login = (Button) findViewById(R.id.loginfg1);
        t1 = (TextView) findViewById(R.id.remai);

        still = 4;
        t1.setText("" + still);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = email.getText().toString().trim();
                String b = password.getText().toString().trim();
                String emaildata = "h";
                String passdata = "1";


                if (((email.getText().toString().equals(emaildata)) && (password.getText().toString().equals(passdata)))) {
                    Toast.makeText(LoginWarden.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginWarden.this, MainWardenDashboard.class);
                    startActivity(intent);
                } else if ((email.getText().toString().equals("")) || (password.getText().toString().equals(""))) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant!", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(LoginWarden.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    still--;
                    if (still == 0) {
                        t1.setText("" + still);
                        login.setEnabled(false);

                    } else {
                        String a234 = String.valueOf(still);

                        t1.setText(a234);
                    }

                }
            }
        });

    }
}

