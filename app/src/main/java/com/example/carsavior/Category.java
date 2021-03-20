package com.example.carsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Category extends AppCompatActivity {
    private String manufacturer = "maruti_suzuki";
    private String model = "wagonr_petrol";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        ignitionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","ignition");
                startActivity(intent);

            }
        });

        acBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","ac");
                startActivity(intent);

            }
        });

        brakeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","brake");
                startActivity(intent);

            }
        });

        oilBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","oil");
                startActivity(intent);

            }
        });

        batteryBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","battery");
                startActivity(intent);

            }
        });

        engineBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","engine");
                startActivity(intent);

            }
        });

        tyreBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","tyre");
                startActivity(intent);
            }
        });

        overheatingBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","overheating");
                startActivity(intent);
            }
        });

        smokeBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Category.this, Solution.class);
                intent.putExtra("manufacturer",manufacturer);
                intent.putExtra("model",model);
                intent.putExtra("problem","smoke");
                startActivity(intent);
            }
        });
    }
}