package com.example.vehiclemanagementsystem.ui.fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.example.vehiclemanagementsystem.ui.activities.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.Month;

public class AddVehicleFragment extends Fragment {
    View view;

    EditText edCarPreNum, edCarNum, edCarRegNum;
    Button btnAddCat;

    private ProgressDialog progressDialog;
    VehicleModel vehicleModel;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_vehicle, container, false);
        init();

        btnAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVehicle();
            }
        });

        return view;
    }

    private void init() {
        vehicleModel = new VehicleModel();
        edCarPreNum = view.findViewById(R.id.ed_addcar_pre_num);
        edCarNum = view.findViewById(R.id.ed_addcar_car_num);
        edCarRegNum = view.findViewById(R.id.ed_addcar_registration_num);
        btnAddCat = view.findViewById(R.id.btn_addcar_add);

        progressDialog = new ProgressDialog(getContext());

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("Vehicles");


    }

    private void addVehicle() {
        String strCarPreNum = edCarPreNum.getText().toString();
        String strCarNum = edCarNum.getText().toString();
        String strCarRegNum = edCarRegNum.getText().toString();

        if (!TextUtils.isEmpty(strCarPreNum)) {
            if (!TextUtils.isEmpty(strCarNum)) {
                if (!TextUtils.isEmpty(strCarRegNum) && strCarRegNum.length() == 10) {


                    Query queryCheckRegNum = FirebaseDatabase.getInstance().getReference("Vehicles").orderByChild("registrationNumber").equalTo(strCarRegNum);
                    queryCheckRegNum.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                DatabaseReference dbRefCheckData = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strCarRegNum);

                                dbRefCheckData.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        vehicleModel = dataSnapshot.getValue(VehicleModel.class);

                                        String strPreNumberDatabase = vehicleModel.getPrefixNumber();
                                        String strVehicleNumberDatabase = vehicleModel.getVehicleNumber();
                                        String strOwnerName = vehicleModel.getVehicleOwnerName();
                                        if (strCarPreNum.equals(strPreNumberDatabase) && strCarNum.equals(strVehicleNumberDatabase)) {

                                            Query queryCheckExistVehicle = FirebaseDatabase.getInstance().getReference("Users").child(auth.getCurrentUser().getUid()).child("Vehicles")
                                                    .orderByChild("registrationNumber").equalTo(strCarRegNum);

                                            queryCheckExistVehicle.addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    if (snapshot.exists()) {
                                                        Toast.makeText(getContext(), "المركبة مضافة مسبقاً", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        progressDialog.setMessage("جاري اضافة المركبة...");
                                                        VehicleModel vehicleModel = new VehicleModel(strCarPreNum, strCarNum, strCarRegNum, strOwnerName);

                                                        DatabaseReference dbRefRegisterNumber = dbRef.child(strCarRegNum);

                                                        dbRefRegisterNumber.setValue(vehicleModel);

                                                        edCarPreNum.setText("");
                                                        edCarNum.setText("");
                                                        edCarRegNum.setText("");

                                                        Toast.makeText(getContext(), "تم اضافة المركبة بنجاح", Toast.LENGTH_SHORT).show();

                                                    }
                                                    progressDialog.dismiss();
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });


                                        } else {
                                            Toast.makeText(getContext(), "رقم التسجيل غير تابع لرقم اللوحه", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            } else {
                                Toast.makeText(getContext(), "هذه المركبة غير مسجله لدى دائرة السير", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                } else {
                    Toast.makeText(getContext(), "يرجى تعبئة خانة رقم التسجيل", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "يرجى تعبئة خانة رقم اللوحه", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "يرجى تعبئة خانة رقم الترميز", Toast.LENGTH_SHORT).show();

        }

    }

}