package com.example.carsavior;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Homepage extends AppCompatActivity {
    public String manufacturer;
    ArrayList<String> models = new ArrayList<String>();
    public String fueltype;
    public String model;
    public String value;
    Button passButton;

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
//                if (pos == 0) {
//                    Toast.makeText
//                            (getApplicationContext(), "Select a Manufacturer", Toast.LENGTH_SHORT)
//                            .show();
//                }
                if (pos == 1) {
                    manufacturer = "tata_motors";
                    fueltype = "Diesel";
                    /*models.clear();
                    models.add("Select Model...");
                    models.add("Sumo");*/

                }
                if (pos == 2) {
                    manufacturer = "maruti_suzuki";
                    fueltype = "Petrol";
                    /*models.clear();
                    models.add("Select Model...");
                    models.add("Dzire");
                    models.add("Wagonr");
                    *//*DatabaseReference myRef = database.getReference(manufacturer);

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot child : dataSnapshot.getChildren()) {
                                models.add(String.valueOf(child.getKey()));
                                Log.i("Key", child.getKey());
                            }
                            //Object[] objects = models.toArray();
                            Log.i("Models", String.valueOf(models));
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value
                            Log.w("Failed Data Load", "Failed to read value.", error.toException());
                        }

                    });*/
                }
                if (pos == 3) {
                    manufacturer = "honda";
                    /*models.clear();
                    models.add("No Model Yet");*/
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //SPINNER 2
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
//                if (pos == 0) {
//                    Toast.makeText
//                            (getApplicationContext(), "Select a Model", Toast.LENGTH_SHORT)
//                            .show();
//                }
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
        SharedPreferences sharedpreferences = getSharedPreferences("car-savior", Context.MODE_PRIVATE);

        String setting1 = sharedpreferences.getString("car1", "null");
        if(setting1 != "null"){
            String[] splitted = setting1.split("\\s+");
            manufacturer = splitted[0].toLowerCase();
            value = (splitted[1]+" "+splitted[2]).toLowerCase();
        }
        Button scar1 = (Button) findViewById(R.id.button2);
        scar1.setText(setting1);
        scar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("Manufacturer", manufacturer);
                intent.putExtra("Model", value);
                Log.i("Done", manufacturer + value);

                startActivity(intent);
            }
        });
        String setting2 = sharedpreferences.getString("car2", "");
        if(setting2 != "null"){
            String[] splitted2 = setting2.split("\\s+");
            manufacturer = splitted2[0].toLowerCase();
            value = (splitted2[1]+"_"+splitted2[2]).toLowerCase();
        }
        Button scar2 = (Button) findViewById(R.id.button3);
        scar2.setText(setting2);
        scar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("car-savior", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                //String value = model+"_"+fueltype;
                String temp1 = sharedpreferences.getString("car1", "");
                String temp2 = sharedpreferences.getString("car2", "");
                editor.putString("car1", temp2);
                editor.putString("car2", temp1);
                Button scar2 = (Button) findViewById(R.id.button3);
                scar2.setText(temp1);
                editor.putString("car2", temp1);
                Button scar1 = (Button) findViewById(R.id.button2);
                scar1.setText(temp2);
                editor.apply();
                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("Manufacturer", manufacturer);

                intent.putExtra("Model", value);
                Log.i("Done", manufacturer + value);

                startActivity(intent);
            }
        });

        //manufacturer + " " + model

        passButton = (Button)findViewById(R.id.search_button);

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences("car-savior", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String value = model+"_"+fueltype;
                String temp = sharedpreferences.getString("car1", "");
                editor.putString("car1", manufacturer + " " + model + " " + fueltype);
                editor.putString("car2", temp);
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), Category.class);
                intent.putExtra("Manufacturer", manufacturer);

                intent.putExtra("Model", value);
                Log.i("Done", manufacturer + value);


                startActivity(intent);
            }
        });
        /*ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, models) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        mspinner.setAdapter(adp);

        //Set listener Called when the item is selected in spinner
        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                //String text = mspinner.getSelectedItem().toString();
                //Log.i("Model", text);
                if (pos > 0) {
                    Log.i("Model", item.toString());
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("Default", "default");
            }

        });*/

        /*mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "This is " +
                        adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();

                try {
                    //Your task here
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

    }
}

