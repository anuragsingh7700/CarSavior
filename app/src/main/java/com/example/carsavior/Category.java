package com.example.carsavior;

import androidx.appcompat.app.AppCompatActivity;

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

        // Read from the database
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("maruti_suzuki/wagonr_petrol/ac");



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("Key", child.getKey());
                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.child("method2").getValue(String.class);
                Log.i("Unique data", value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed Data Load", "Failed to read value.", error.toException());
            }
        });
        ignitionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}