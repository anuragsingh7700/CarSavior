package com.example.carsavior;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Login extends AppCompatActivity {
    private String text ;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner username = findViewById(R.id.username);
        String text ;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        text = username.getSelectedItem().toString();
        String[] split_str = text.split(" ");
        text = Arrays.stream(split_str).collect(Collectors.joining("_"));
        EditText password = findViewById(R.id.password);

//        if (text.equals(password.toString())){
//
//        }

    }
}