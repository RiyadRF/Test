package com.example.vehiclemanagementsystem.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.AESCrypt;
import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordFragment extends Fragment {

    View view;

    EditText edCurrentPass, edNewPass, edNewPassAgain;
    Button btnChangePass;

    UserModel userModel;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    DatabaseReference dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_password, container, false);
        init();
        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return view;
    }

    private void init() {
        edCurrentPass = view.findViewById(R.id.ed_change_current_password);
        edNewPass = view.findViewById(R.id.ed_change_new_password);
        edNewPassAgain = view.findViewById(R.id.ed_change_new_password_again);
        btnChangePass = view.findViewById(R.id.btn_change_password);

        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
    }

    private void changePassword() {
        if (!edCurrentPass.getText().toString().isEmpty()
                && !edNewPass.getText().toString().isEmpty()
                && !edNewPassAgain.getText().toString().isEmpty()) {

            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userModel = snapshot.getValue(UserModel.class);
                    String strCurrentPassword = userModel.getPassword();
                    String strCurrentPasswordEncrypt = null;
                    try {
                        strCurrentPasswordEncrypt = AESCrypt.encrypt(edCurrentPass.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (strCurrentPassword.equals(strCurrentPasswordEncrypt)) {
                        if (edNewPass.getText().toString().equals(edNewPassAgain.getText().toString())) {

                            if (edNewPass.getText().toString().length() >= 8) {


                                firebaseUser.updatePassword(edNewPass.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                String encryptNewPassword = edNewPass.getText().toString();
                                                try {
                                                    encryptNewPassword = AESCrypt.encrypt(edNewPass.getText().toString());
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                Map<String, Object> updatePassword = new HashMap<String, Object>();

                                                updatePassword.put("password", encryptNewPassword);
                                                dbRef.updateChildren(updatePassword);
                                                Toast.makeText(getContext(), "تم تغيير كلمة السر بنجاح", Toast.LENGTH_SHORT).show();
                                                edCurrentPass.setText("");
                                                edNewPass.setText("");
                                                edNewPassAgain.setText("");
                                            }
                                        });
                            } else {
                                Toast.makeText(getContext(), "يجب ان تكون كلمة المرور 8 حروف على الاقل", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "كلمة المرور الجديدة غير متطابقة", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "كلمة المرور الحالية غير صحيحة", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        } else {
            Toast.makeText(getContext(), "جميع الحقول مطلوبة", Toast.LENGTH_SHORT).show();
        }
    }
}