package com.example.vehiclemanagementsystem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class VehicleRenewalActivity extends AppCompatActivity {

    TextView tvRenewalTo,tvInfraction, tvCheck, tvInsurance,tvCost,tvHint;
    Button btnContinue;

    VehicleModel vehicleModel;
    private DatabaseReference dbRef;

    String registerNumber;

    int intYear;
    int intMonth;

    public static String RENEWAL = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_renewal);

        registerNumber = getIntent().getExtras().getString("details_register_number");

        init();


        String strYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        intYear = Integer.parseInt(strYear);

        String strMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        intMonth = Integer.parseInt(strMonth) + 1;

        checkInfo();

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleRenewalActivity.this, PayInsuranceActivity.class);
                intent.putExtra("infraction_register_number", registerNumber);
                RENEWAL = "RENEWAL";
                startActivity(intent);

            }
        });

    }

    private void init() {
        tvRenewalTo = findViewById(R.id.tv_vehicle_renewal_to);
        tvInfraction = findViewById(R.id.tv_vehicle_renewal_infraction);
        tvCheck = findViewById(R.id.tv_vehicle_renewal_check);
        tvInsurance = findViewById(R.id.tv_vehicle_renewal_insurance);
        tvCost = findViewById(R.id.tv_vehicle_renewal_cost);
        tvHint = findViewById(R.id.tv_vehicle_renewal_hint);
        btnContinue = findViewById(R.id.btn_vehicle_renewal_continue);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(registerNumber);
    }


    private void checkInfo() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vehicleModel = snapshot.getValue(VehicleModel.class);

                String strInsuranceYear = vehicleModel.getInsuranceYear();
                String strInsuranceMonth = vehicleModel.getInsuranceMonth();

                String strRenewalYear = vehicleModel.getYear();
                String strRenewalMonth = vehicleModel.getMonth();

                String strEngineSize = vehicleModel.getEngineSize();

                tvRenewalTo.setText(vehicleModel.getDay()+"/"+vehicleModel.getMonth()+"/"+vehicleModel.getYear());

                //check infraction
                if (snapshot.hasChild("Infraction")) {
                    tvInfraction.setText("يرجى دفع المخالفات أولاَ");
                    tvInfraction.setTextColor(Color.parseColor("#FFFF0000"));
                    btnContinue.setVisibility(View.GONE);
                } else {
                    tvInfraction.setText("لا يوجد مخالفات ");
                    tvInfraction.setTextColor(Color.parseColor("#FF32A800"));
                }

                //check insurance
                if (intYear > Integer.parseInt(strInsuranceYear)) {
                    tvInsurance.setText("يرجى تأمين المركبة أولاَ");
                    tvInsurance.setTextColor(Color.parseColor("#FFFF0000"));
                    btnContinue.setVisibility(View.GONE);
                } else if (intYear == Integer.parseInt(strInsuranceYear) && Integer.parseInt(strInsuranceMonth) - intMonth <= 1) {
                    tvInsurance.setText("يرجى تأمين المركبة أولاَ");
                    tvInsurance.setTextColor(Color.parseColor("#FFFF0000"));
                    btnContinue.setVisibility(View.GONE);
                } else {
                    tvInsurance.setText("المركبة مؤمنة بنجاح");
                    tvInsurance.setTextColor(Color.parseColor("#FF32A800"));
                }

                //check insurance
                if (intYear > Integer.parseInt(strRenewalYear)) {
                    tvHint.setVisibility(View.GONE);
                } else if (intYear == Integer.parseInt(strRenewalYear) && Integer.parseInt(strRenewalMonth) - intMonth <= 1) {
                    tvHint.setVisibility(View.GONE);
                } else {
                    tvHint.setText("ترخيص المركبه ما زال فعال");
                    tvHint.setTextColor(Color.parseColor("#FFFF0000"));
                    btnContinue.setVisibility(View.GONE);

                }

                //check engine size
                if (Integer.parseInt(strEngineSize) < 1600){
                    tvCost.setText("45 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                } else if (Integer.parseInt(strEngineSize) >= 1600 && Integer.parseInt(strEngineSize) < 2000){
                    tvCost.setText("64 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                } else if (Integer.parseInt(strEngineSize) >= 2000 && Integer.parseInt(strEngineSize) < 2500){
                    tvCost.setText("173 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                }else if (Integer.parseInt(strEngineSize) >= 2500 && Integer.parseInt(strEngineSize) < 3000){
                    tvCost.setText("225 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                }else if (Integer.parseInt(strEngineSize) >= 3000 && Integer.parseInt(strEngineSize) < 4000){
                    tvCost.setText("440 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                }else if (Integer.parseInt(strEngineSize) >= 4000){
                    tvCost.setText("650 دينار");
                    tvCost.setTextColor(Color.parseColor("#FF32A800"));

                }


                if (snapshot.hasChild("check")) {
                    tvCheck.setText("تم قبول الفحص الفني");
                    tvCheck.setTextColor(Color.parseColor("#FF32A800"));
                } else {
                    tvCheck.setText("يرجى فحص السيارة فنيا ");
                    tvCheck.setTextColor(Color.parseColor("#FFFF0000"));
                    btnContinue.setVisibility(View.GONE);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}