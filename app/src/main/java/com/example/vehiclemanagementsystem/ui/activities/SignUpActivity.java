package com.example.vehiclemanagementsystem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.AESCrypt;
import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    String stEmailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    EditText edName, edEmail, edPhoneNum, edID, edPassword;
    TextView tvLogin;
    Button btnRegister;


    private FirebaseAuth auth;
    private DatabaseReference dbRefUser;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        SpannableString ss = new SpannableString(this.getResources().getString(R.string.already_have_acc));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
            }
        };
        ss.setSpan(clickableSpan, 14, 26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvLogin.setText(ss);
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance());


    }

    private void init() {
        edName = findViewById(R.id.ed_sign_name);
        edEmail = findViewById(R.id.ed_sign_email);
        edPhoneNum = findViewById(R.id.ed_sign_phone);
        edID = findViewById(R.id.ed_sign_ID);
        edPassword = findViewById(R.id.ed_sign_password);
        btnRegister = findViewById(R.id.btn_sign_register);
        tvLogin = findViewById(R.id.tv_sign_login);
        progressDialog = new ProgressDialog(this);


        auth = FirebaseAuth.getInstance();
        dbRefUser = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    private void register() {
        String stName = edName.getText().toString();
        String stPhoneNum = edPhoneNum.getText().toString().trim();
        String stEmail = edEmail.getText().toString();
        String stID = edID.getText().toString();
        String stPassword = edPassword.getText().toString();

        if (!TextUtils.isEmpty(stName) && !TextUtils.isEmpty(stPhoneNum) && !TextUtils.isEmpty(stEmail) &&
                !TextUtils.isEmpty(stPassword)) {

            if (stEmail.matches(stEmailPattern)) {

                if (stPhoneNum.length() == 10) {

                    if (stPassword.length() >= 8) {


                        if (stID.length() == 10) {

                            progressDialog.setMessage("جاري انشاء حساب...");
                            progressDialog.show();


                            Query queryCheckPhone = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phone").equalTo(stPhoneNum);
                            Query queryCheckID = FirebaseDatabase.getInstance().getReference("Users").orderByChild("id").equalTo(stID);

                            queryCheckPhone.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        Toast.makeText(SignUpActivity.this, "Phone number is already used", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    } else if (!snapshot.exists()) {
                                        queryCheckID.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if (snapshot.exists()) {
                                                    Toast.makeText(SignUpActivity.this, "ID Number is already used", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();

                                                } else {
                                                    auth.createUserWithEmailAndPassword(stEmail, stPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                                            String encryptPassword = stPassword;
                                                            if (task.isSuccessful()) {
                                                                try {
                                                                     encryptPassword = AESCrypt.encrypt(stPassword);
                                                                } catch (Exception e) {
                                                                    e.printStackTrace();
                                                                }

                                                                String stUserID = auth.getCurrentUser().getUid();
                                                                UserModel userModel = new UserModel(stName, stEmail, stPhoneNum, stID, encryptPassword);

                                                                DatabaseReference dbRefCurrentUserId = dbRefUser.child(stUserID);
                                                                dbRefCurrentUserId.setValue(userModel);
                                                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                                                finish();

                                                                progressDialog.dismiss();
                                                            }
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(SignUpActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                                }

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();

                                            }
                                        });

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(SignUpActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                }
                            });
                        } else {
                            Toast.makeText(SignUpActivity.this, "Please correct your ID", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Password must be 8 characters or more", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(SignUpActivity.this, "Please correct your Phone number", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(SignUpActivity.this, getResources().getString(R.string.correct_email), Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(SignUpActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();


        }
    }
}