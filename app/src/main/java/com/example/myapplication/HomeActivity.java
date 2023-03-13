package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView spa, facial, hairstyle, makeup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        spa =  findViewById((R.id.spa));
        facial =  findViewById((R.id.facial));
        hairstyle =  findViewById((R.id.hairstyle));
        makeup =  findViewById((R.id.makeup));

        spa.setOnClickListener(v -> sendToNextPage("spa"));
        facial.setOnClickListener(v -> sendToNextPage("facial"));
        hairstyle.setOnClickListener(v -> sendToNextPage("hairstyle"));
        makeup.setOnClickListener(v -> sendToNextPage("makeup"));
    }

    private void sendToNextPage(String service) {
        Intent intent = new Intent(this,serviceActivity.class);
        intent.putExtra("service", service);
        startActivity(intent);
    }
}