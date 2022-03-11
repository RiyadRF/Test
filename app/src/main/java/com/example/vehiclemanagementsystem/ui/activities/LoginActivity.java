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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText edEmail, edPassword;
    TextView tvSignUp;
    Button btnLogin;

    private ProgressDialog progressDialog;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        SpannableString ss = new SpannableString(this.getResources().getString(R.string.do_not_have_acc));

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
            }
        };
        ss.setSpan(clickableSpan, 14, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvSignUp.setText(ss);
        tvSignUp.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void init() {
        edEmail = findViewById(R.id.ed_login_email);
        edPassword = findViewById(R.id.ed_login_password);
        tvSignUp = findViewById(R.id.tv_login_sign);
        btnLogin = findViewById(R.id.btn_login_login);
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();


    }

    private void startLogin() {
        String strEmail = edEmail.getText().toString();
        String strPassword = edPassword.getText().toString();

        if (!TextUtils.isEmpty(strEmail) && !TextUtils.isEmpty(strPassword)) {
            progressDialog.setMessage(getResources().getString(R.string.login) + "...");
            progressDialog.show();

            auth.signInWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, "البريد الإلكتروني أو كلمة السر غير صحيحه", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });
        } else {
            progressDialog.dismiss();
            Toast.makeText(this, "جميع الحقول مطلوبة", Toast.LENGTH_SHORT).show();
        }
    }
}