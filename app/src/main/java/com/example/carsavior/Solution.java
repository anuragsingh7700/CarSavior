package com.example.carsavior;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Solution extends AppCompatActivity {
    private String manufacturer;
    private String model;
    private String problem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            manufacturer = extras.getString("manufacturer");
            model = extras.getString("model");
            problem = extras.getString("problem");
        }
        setContentView(R.layout.activity_solution);
        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(manufacturer+"/"+model+"/"+problem);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
                    removeAllViewsFromLinearLayout();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String heading = child.getKey();
                    assert heading != null;
                    String explanation = dataSnapshot.child(heading).getValue(String.class);
                    addSolution(heading,explanation);
                }
                addExpertsMessage();
                }
                else{
                    addSolution("No Data Available","");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error){
            // Failed to read value
            Log.w("Failed Data Load", "Failed to read value.", error.toException());
        }
        });
}

    private void addSolution(String heading, String explanation){
        LinearLayout rootLayout = findViewById(R.id.rootLinearLayout);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundResource(R.drawable.rounded_corners);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 10, 0, 50);
        linearLayout.setLayoutParams(params);

        Typeface typeface = ResourcesCompat.getFont(this, R.font.monda);

        TextView head = new TextView(this);
        head.setText(heading);
        head.setTextSize(28);
        head.setTextColor(Color.BLACK);
        head.setTypeface(typeface);
        head.setGravity(View.TEXT_ALIGNMENT_CENTER);

        TextView body = new TextView(this);
        body.setText(explanation);
        body.setTextColor(Color.BLACK);
        body.setTextSize(22);
        body.setTypeface(typeface);
        body.setPadding(0,0,0,10);
        linearLayout.addView(head);
        linearLayout.addView(body);
        rootLayout.addView(linearLayout);
    }
    private void removeAllViewsFromLinearLayout(){
        LinearLayout linearLayout = findViewById(R.id.rootLinearLayout);
        linearLayout.removeAllViews();
    }
    private void addExpertsMessage(){
        LinearLayout rootLayout = findViewById(R.id.rootLinearLayout);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setBackgroundColor(Color.BLACK);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.monda);
        TextView body = new TextView(this);
        body.setText("Solutions are provided by Experts");
        body.setTextColor(Color.WHITE);
        body.setTextSize(18);
        body.setGravity(View.TEXT_ALIGNMENT_CENTER);
        body.setTypeface(typeface);
        linearLayout.addView(body);
        rootLayout.addView(linearLayout);
    }
}