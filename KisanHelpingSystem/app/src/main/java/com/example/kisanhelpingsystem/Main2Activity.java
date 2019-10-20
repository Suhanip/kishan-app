package com.example.kisanhelpingsystem;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;

public class Main2Activity extends AppCompatActivity {

    public DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final ToggleButton toggleButton = findViewById(R.id.tb1);
        final Handler handler = new Handler();

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep);

        SharedPreferences sharedPrefs = getSharedPreferences("com.example.vishal.togglebutton", MODE_PRIVATE);
        toggleButton.setChecked(sharedPrefs.getBoolean("toggleButton", true));

        toggleButton.setChecked(false);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton.isChecked()) {
                    mDatabase.child("Status").setValue("On");
                    toggleButton.setBackgroundColor(GREEN);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toggleButton.setChecked(false);
                            mp.start();
                            toggleButton.setBackgroundColor(RED);
                            mDatabase.child("Status").setValue("Off");
                        }
                    }, 10000);

                } else {
                    Toast.makeText(Main2Activity.this, "Uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final SwitchCompat toggleButton2 = findViewById(R.id.switchButton);
        final Handler handler2 = new Handler();

        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggleButton2.isChecked()) {
                    mDatabase.child("Status2").setValue("On");
                    final SharedPreferences.Editor editor = getSharedPreferences("com.example.vishal.togglebutton", MODE_PRIVATE).edit();
                    editor.putBoolean("toggleButton", true);
                    handler2.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toggleButton2.setChecked(false);
                            mp.start();
                            mDatabase.child("Status2").setValue("Off");
                        }
                    }, 10000);

                } else {
                    Toast.makeText(Main2Activity.this, "Uncheck", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

