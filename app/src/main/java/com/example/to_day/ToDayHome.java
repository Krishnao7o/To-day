package com.example.to_day;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ToDayHome extends AppCompatActivity {

    TextView vName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_day_home);
        vName = findViewById(R.id.name);
        String name = getIntent().getStringExtra("name");
        vName.setText(name);
    }
}