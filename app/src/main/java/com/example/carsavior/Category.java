package com.example.carsavior;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Category extends AppCompatActivity {
    private String manufacturer;
    private String model;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Homepage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            manufacturer = extras.getString("manufacturer");
            model = extras.getString("model");
        }
        setContentView(R.layout.activity_category);

        ImageButton ignitionBtn = findViewById(R.id.ignitionBtn);
        ImageButton acBtn = findViewById(R.id.acBtn);
        ImageButton batteryBtn = findViewById(R.id.batteryBtn);
        ImageButton engineBtn = findViewById(R.id.engineBtn);
        ImageButton oilBtn = findViewById(R.id.oilBtn);
        ImageButton brakeBtn = findViewById(R.id.brakeBtn);
        ImageButton tyreBtn = findViewById(R.id.tyreBtn);
        ImageButton overheatingBtn = findViewById(R.id.overheatingBtn);
        ImageButton smokeBtn = findViewById(R.id.smokeBtn);
        ImageButton submitBtn = findViewById(R.id.submitBtn);
        ImageButton voiceBtn = findViewById(R.id.voiceBtn);
        ImageButton videoBtn = findViewById(R.id.videoBtn);

        ignitionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "ignition");
                startActivity(intent);

            }
        });

        acBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "ac");
                startActivity(intent);

            }
        });

        brakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "brake");
                startActivity(intent);

            }
        });

        oilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "oil");
                startActivity(intent);

            }
        });

        batteryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "battery");
                startActivity(intent);

            }
        });

        engineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "engine");
                startActivity(intent);

            }
        });

        tyreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "tyre");
                startActivity(intent);
            }
        });

        overheatingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "overheating");
                startActivity(intent);
            }
        });

        smokeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                intent.putExtra("problem", "smoke");
                startActivity(intent);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, Login.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                startActivity(intent);
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Category.this, VideoLogin.class);
                intent.putExtra("manufacturer", manufacturer);
                intent.putExtra("model", model);
                startActivity(intent);
            }
        });

        voiceBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + 1231231231));
                if (ActivityCompat.checkSelfPermission(Category.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"Grant Call Permissions to dial!",Toast.LENGTH_SHORT);
                    return;
                }
                startActivity(callIntent);
            }
        });
    }
}