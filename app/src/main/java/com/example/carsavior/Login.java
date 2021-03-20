package com.example.carsavior;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Login extends AppCompatActivity {
    private String text ;
    private String document;
    private Button submit;
    private String question;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submit = findViewById(R.id.submit);
        question = findViewById(R.id.question).toString();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            document = extras.getString("manufacturer")+'_'+extras.getString("model");
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Task<Void> result  = db.collection("questions").document(document).update("questions",FieldValue.arrayUnion(question));
                Log.i("RESULT",result.toString());
                Toast.makeText(getApplicationContext(),"Thanks for helping! Question Submitted",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}