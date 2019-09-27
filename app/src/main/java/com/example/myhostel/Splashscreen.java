package com.example.myhostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Splashscreen extends AppCompatActivity {
    ImageView i1, i2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashscreen);

        i1 = (ImageView)findViewById(R.id.pinksplash);
        i2 = (ImageView)findViewById(R.id.home1);

        t1 = (TextView)findViewById(R.id.text1);


        YoYo.with(Techniques.Tada)
                .duration(2500)
                .repeat(0)
                .playOn(t1);
        YoYo.with(Techniques.Landing)
                .duration(2500)
                .repeat(0)
                .playOn(i1);
        YoYo.with(Techniques.Wave)
                .duration(2500)
                .repeat(0)
                .playOn(i2);


        /****** Create Thread that will sleep for 5 seconds****/
        Thread background = new Thread() {


            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(3*1000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getApplicationContext(),LoginPage.class);
                    savePrefsData();
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();


    }

    private boolean restorePrefData() {


        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs1", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend1", false);
        return isIntroActivityOpnendBefore;


    }

    private void savePrefsData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs1", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend1", true);
        editor.commit();


    }
}
