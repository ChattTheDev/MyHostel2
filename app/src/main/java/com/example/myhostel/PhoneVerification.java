package com.example.myhostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerification extends AppCompatActivity {

    Button getcode, verifynum;
    EditText numget, entercode;
    String codesent;
    PhoneAuthProvider.ForceResendingToken mtoken;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        getcode = (Button) findViewById(R.id.getcode);
        verifynum = (Button) findViewById(R.id.verifynum);

        numget = (EditText) findViewById(R.id.gettingphonenumber);
        entercode = (EditText) findViewById(R.id.entercode);

        mAuth = FirebaseAuth.getInstance();

        getcode.setOnClickListener(new View.OnClickListener() {
            PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


                @Override
                public void onVerificationCompleted(PhoneAuthCredential credential) {

                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(PhoneVerification.this, e.getMessage(), Toast.LENGTH_LONG).show();


                }

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken token) {

                    codesent = s;
                    mtoken = token;
                }
            };

            @Override
            public void onClick(View view) {

                String gettingcode = numget.getText().toString();

                if (gettingcode.isEmpty()) {
                    numget.setError("Phone Number is Empty");
                    numget.requestFocus();
                    return;
                }
                if (gettingcode.length() < 10) {

                    numget.setError("Enter a valid Number");
                    numget.requestFocus();
                    return;

                }

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        gettingcode,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        PhoneVerification.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacksPhoneAuthActivity.java


            }
        });

        verifynum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                verifyphone();

            }
        });
    }

    private void verifyphone() {
        String code = entercode.getText().toString();


        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesent, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            Intent intent = new Intent(getApplicationContext(), Main_DashBoard.class);
                            startActivity(intent);
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(getApplicationContext(), "Verification Successfull", Toast.LENGTH_SHORT).show();


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(getApplicationContext(), "Verification Failed, Try again..", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

    }
    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, Main_DashBoard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
}
