package com.example.myhostel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText e1, e2;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        e1 = (EditText) findViewById(R.id.email12);
        e2 = (EditText) findViewById(R.id.pass12);
        login = (Button) findViewById(R.id.loginfg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                if (email.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 5) {

                    Toast.makeText(LoginPage.this, "Password Should Be Minimum 6 Digits", Toast.LENGTH_LONG).show();
                    return;

                }
                final ProgressDialog progressDialog = new ProgressDialog(LoginPage.this);
                progressDialog.setTitle("Logging You In!!!");
                progressDialog.setMessage("Processing.....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                mAuth = FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.hide();
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginPage.this, "Login Successfull!!", Toast.LENGTH_LONG).show();

                                    Intent i = new Intent(LoginPage.this, Main_DashBoard.class);
                                    startActivity(i);
                                    finish();

                                } else {
                                    Toast.makeText(LoginPage.this, "Something Went Wrong!", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                e1.setText("");
                e2.setText("");

            }
        });


    }
}
