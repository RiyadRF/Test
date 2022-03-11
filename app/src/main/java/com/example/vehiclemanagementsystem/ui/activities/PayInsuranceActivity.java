package com.example.vehiclemanagementsystem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.example.vehiclemanagementsystem.ui.activities.InsuranceActivity.strInsuranceCompany;
import static com.example.vehiclemanagementsystem.ui.activities.InsuranceActivity.strInsuranceType;
import static com.example.vehiclemanagementsystem.ui.activities.VehicleRenewalActivity.RENEWAL;

public class PayInsuranceActivity extends AppCompatActivity {
    EditText edCardName, edCardNumber, edCardMonth, edCardYear, edCardPassword;
    Button btnPay;

    int intYear;
    int intMonth;

    String strRegisterNumber;
    String strCost;
    private VehicleModel vehicleModel;
    private DatabaseReference dbRef, dbRefCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_insurance);

        strRegisterNumber = getIntent().getExtras().getString("infraction_register_number");
        strCost = getIntent().getExtras().getString("infraction_cost");
        init();


        String strYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2);
        intYear = Integer.parseInt(strYear);

        String strMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        if (strMonth.length() != 2) {
            strMonth = "0" + strMonth;
        }
        intMonth = Integer.parseInt(strMonth) + 1;

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RENEWAL.equals("RENEWAL")) {
                    checkCardDataRenewal();
                    RENEWAL = "";
                } else {
                    checkCardDataInsurance();
                }
            }
        });

    }

    private void init() {
        edCardName = findViewById(R.id.ed_pay_insurance_card_name);
        edCardNumber = findViewById(R.id.ed_pay_insurance_card_number);
        edCardMonth = findViewById(R.id.ed_pay_insurance_card_month);
        edCardYear = findViewById(R.id.ed_pay_insurance_card_year);
        edCardPassword = findViewById(R.id.ed_pay_insurance_card_password);
        btnPay = findViewById(R.id.btn_pay_insurance);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strRegisterNumber);
        dbRefCheck = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strRegisterNumber).child("check");

    }

    private void checkCardDataInsurance() {
        if (!edCardName.getText().toString().isEmpty()
                || !edCardNumber.getText().toString().isEmpty() || !edCardMonth.getText().toString().isEmpty()
                || !edCardYear.getText().toString().isEmpty() || !edCardPassword.getText().toString().isEmpty()) {

            if (edCardNumber.getText().length() == 16) {


                if (Integer.parseInt(edCardYear.getText().toString()) > intYear
                        || (Integer.parseInt(edCardYear.getText().toString()) == intYear
                        && Integer.parseInt(edCardMonth.getText().toString()) > intMonth)) {

                    if (edCardMonth.getText().length() == 2 && edCardYear.getText().length() == 2) {
                        if (edCardPassword.getText().length() == 3) {
                            vehicleModel = new VehicleModel();
                            Map<String, Object> updates = new HashMap<String, Object>();


                            String strNewYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                            int intNewYear = Integer.parseInt(strNewYear) + 1;
                            strNewYear = String.valueOf(intNewYear);
                            updates.put("insuranceYear", strNewYear);
                            updates.put("insurance", strInsuranceType);
                            updates.put("insuranceCompany", strInsuranceCompany);

                            dbRef.updateChildren(updates);
                            Toast.makeText(this, "تم تجديد تأمين المركبه بشكل صحيح", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PayInsuranceActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);

                        } else {
                            Toast.makeText(this, "يرجى إدخال رقم cvv صحيح", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(this, "يرجى إدخال تاريخ بطاقة صحيح", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(this, "يرجى إدخال تاريخ بطاقة صحيح", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "يرجى إدخال رقم بطاقة صحيح", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "جميع الحقول مطلوبة", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkCardDataRenewal() {
        if (!edCardName.getText().toString().isEmpty()
                || !edCardNumber.getText().toString().isEmpty() || !edCardMonth.getText().toString().isEmpty()
                || !edCardYear.getText().toString().isEmpty() || !edCardPassword.getText().toString().isEmpty()) {

            if (edCardNumber.getText().length() == 16) {


                if (Integer.parseInt(edCardYear.getText().toString()) > intYear
                        || (Integer.parseInt(edCardYear.getText().toString()) == intYear
                        && Integer.parseInt(edCardMonth.getText().toString()) > intMonth)) {

                    if (edCardMonth.getText().length() == 2 && edCardYear.getText().length() == 2) {
                        if (edCardPassword.getText().length() == 3) {
                            vehicleModel = new VehicleModel();
                            Map<String, Object> updates = new HashMap<String, Object>();


                            String strNewYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                            int intNewYear = Integer.parseInt(strNewYear) + 1;
                            strNewYear = String.valueOf(intNewYear);
                            updates.put("year", strNewYear);

                            dbRef.updateChildren(updates);
                            Toast.makeText(this, "تم ترخيص المركبه بشكل صحيح", Toast.LENGTH_SHORT).show();
                            dbRefCheck.removeValue();
                            Intent intent = new Intent(PayInsuranceActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);

                        } else {
                            Toast.makeText(this, "يرجى إدخال رقم cvv صحيح", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(this, "يرجى إدخال تاريخ بطاقة صحيح", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(this, "يرجى إدخال تاريخ بطاقة صحيح", Toast.LENGTH_SHORT).show();

                }
            } else {
                Toast.makeText(this, "يرجى إدخال رقم بطاقة صحيح", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "جميع الحقول مطلوبة", Toast.LENGTH_SHORT).show();
        }
    }


}