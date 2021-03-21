package com.example.carsavior;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class VideoLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore database;
    private Button btnLogin;
    private EditText etPhoneNumber;
    private EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_login);
        btnLogin = findViewById(R.id.OTPbtn);
        etPhoneNumber = findViewById(R.id.phone);
        etName = findViewById(R.id.etName);
        database = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phoneNumber = "+91"+etPhoneNumber.getText().toString();
                if (phoneNumber.isEmpty())
                    Toast.makeText(VideoLogin.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                else {
                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(phoneNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(VideoLogin.this)                 // Activity (for callback binding)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        Toast.makeText(getApplicationContext(),"Verifying Code Automatically", Toast.LENGTH_SHORT).show();
//                                        mAuth.signInWithCredential(phoneAuthCredential);
                                        User user = new User();
                                        user.setPhoneNumber(phoneNumber);
                                        user.setName(etName.getText().toString());
                                        signInUser(phoneAuthCredential,user);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Log.d("TAG", "onVerificationFailed:"+e.getLocalizedMessage());
                                    }
                                    @Override
                                    public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(verificationId, forceResendingToken);
                                        //
                                        final Dialog dialog = new Dialog(VideoLogin.this);
                                        dialog.setContentView(R.layout.verify_popup);
                                        dialog.show();

                                        final EditText etVerifyCode = dialog.findViewById(R.id.etVerifyCode);
                                        Button btnVerifyCode = dialog.findViewById(R.id.btnVerifyOTP);
                                        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                dialog.hide();
                                                String verificationCode = etVerifyCode.getText().toString();
                                                User user = new User();
                                                user.setPhoneNumber(phoneNumber);
                                                user.setName(etName.getText().toString());
                                                if(verificationId.isEmpty()) return;
                                                //create a credential
                                                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                                signInUser(credential,user);
                                            }
                                        });
                                    }
                                })          // OnVerificationStateChangedCallbacks
.build();
                    PhoneAuthProvider.verifyPhoneNumber(options);
                }
            }
        });
    }

    private void signInUser(PhoneAuthCredential credential, final User user) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            database.collection("Users").document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent intent = new Intent(VideoLogin.this, VideoCall.class);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Login Failed, Try Again!",Toast.LENGTH_SHORT).show();
                            VideoLogin.this.recreate();
                        }
                    }
                });

    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}