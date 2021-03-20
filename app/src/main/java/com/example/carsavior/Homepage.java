package com.example.carsavior;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
    public String manufacturer;
    public String fueltype;
    public String model;
    Button passButton;
    private List<String> models = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        final Spinner mspinner = (Spinner) findViewById(R.id.spinner2);
        final Spinner fspinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if (pos == 0) {
                    Toast.makeText
                            (getApplicationContext(), "Select a Manufacturer", Toast.LENGTH_SHORT)
                            .show();
                }
                if (pos == 1) {
                    manufacturer = "tata_motors";
                    models.clear();
                    models.add("Select Model");

                    DatabaseReference myRef = database.getReference(manufacturer);

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() > 0) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    model = String.valueOf(child.getKey()).split("_")[0];
                                    model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
                                    models.add(model);
                                }
                            }else{
                                models.clear();
                                models.add("No Model Found");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Failed to read value
                            Log.w("Failed Data Load", "Failed to read value.", error.toException());
                        }

                    });

                }
                if (pos == 2) {
                    manufacturer = "maruti_suzuki";
                    models.clear();
                    models.add("Select Model");

                    DatabaseReference myRef = database.getReference(manufacturer);

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() > 0) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    model = String.valueOf(child.getKey()).split("_")[0];
                                    model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
                                    models.add(model);
                                }
                            }else{
                                models.clear();
                                models.add("No Model Found");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Failed to read value
                            Log.w("Failed Data Load", "Failed to read value.", error.toException());
                        }

                    });
                }
                if (pos == 3) {
                    manufacturer = "honda";
                    DatabaseReference myRef = database.getReference(manufacturer);

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getChildrenCount() > 0) {
                                for (DataSnapshot child : dataSnapshot.getChildren()) {
                                    model = String.valueOf(child.getKey()).split("_")[0];
                                    model = model.substring(0, 1).toUpperCase() + model.substring(1).toLowerCase();
                                    models.add(model);
                                }
                            }else{
                                models.clear();
                                models.add("No Model Found");
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Failed to read value
                            Log.w("Failed Data Load", "Failed to read value.", error.toException());
                        }

                    });
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Homepage.this, android.R.layout.simple_spinner_item, models);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mspinner.setAdapter(dataAdapter);

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //SPINNER 2
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if (pos == 0) {
                    Toast.makeText
                            (getApplicationContext(), "Select a Model", Toast.LENGTH_SHORT)
                            .show();
                }
                if (pos == 1) {
                    model = "sumo";
                }
                if (pos == 2) {
                    model = "dzire";
                }
                if (pos == 3) {
                   model = "wagonr";
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //SPINNER 3
        fspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//                if (pos == 0) {
//                    Toast.makeText
//                            (getApplicationContext(), "Select a Model", Toast.LENGTH_SHORT)
//                            .show();
//                }
                if (pos == 1) {
                    fueltype = "petrol";
                }
                if (pos == 2) {
                    fueltype = "diesel";
                }
                if (pos == 3) {
                    fueltype = "cng";
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        passButton = (Button)findViewById(R.id.search_button);

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("manufacturer", manufacturer);
                String value = model+"_"+fueltype;
                intent.putExtra("model", value);

                DatabaseReference myRef = database.getReference(manufacturer+'/'+value);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getChildrenCount() > 0) {
                            startActivity(intent); }
                        else{
                            Toast.makeText
                            (getApplicationContext(), "No Data Found", Toast.LENGTH_SHORT)
                            .show();
                        }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                Log.i("Done", manufacturer + value);

            }
        });
    }
}

