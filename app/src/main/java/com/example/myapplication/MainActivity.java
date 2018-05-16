package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Pankaj on 09/05/18.
 */

public class MainActivity extends AppCompatActivity {

    Button btnNextActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnNextActivity = findViewById(R.id.activity_main_btn_nextactvitiy);
//        btnNextActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "Requesting for next Activity.", Toast.LENGTH_SHORT).show();
//                // Intent for calling next activity
//                Intent intent = new Intent(MainActivity.this, NextActivity.class);
//                startActivity(intent);
//
//            }
//        });

    }
}
