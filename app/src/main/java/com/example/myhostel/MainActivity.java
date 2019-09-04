package com.example.myhostel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    Button reg;
    EditText e1, e2, e3;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        reg = (Button) findViewById(R.id.loginf);
        e1 = (EditText) findViewById(R.id.email1);
        e2 = (EditText) findViewById(R.id.pass1);
        e3 = (EditText) findViewById(R.id.editname);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = e1.getText().toString().trim();
                String password = e2.getText().toString().trim();
                final String name = e3.getText().toString().trim();

                if (email.equals("") || password.equals("") || name.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.length() < 5) {

                    Toast.makeText(MainActivity.this, "Password Should Be Minimum 6 Digits", Toast.LENGTH_LONG).show();
                    return;

                }

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("Registering!!!");
                progressDialog.setMessage("Processing.....");
                progressDialog.setCancelable(false);
                progressDialog.show();

                mAuth = FirebaseAuth.getInstance();


                mAuth.fetchProvidersForEmail(e1.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                            @Override
                            public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                                boolean check = !task.getResult().getProviders().isEmpty();

                                if (!check) {

                                } else {
                                    progressDialog.hide();
                                    Toast.makeText(MainActivity.this, "Email Already Present", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            UserAdapter user = new UserAdapter(
                                    name,
                                    email
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressDialog.hide();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(MainActivity.this, Main_DashBoard.class);
                                        startActivity(i);

                                        finish();

                                    } else {
                                        //display a failure message
                                        Toast.makeText(MainActivity.this, "Something Went Wrong! Try Again Later", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");


                        }
                    }

                });

            }
        });



    }


}