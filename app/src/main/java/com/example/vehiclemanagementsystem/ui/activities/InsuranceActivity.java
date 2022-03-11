package com.example.vehiclemanagementsystem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class InsuranceActivity extends AppCompatActivity {

    public static String strInsuranceType = "";
    public static String strInsuranceCompany = "";

    TextView tvCost, tvInsurance;
    private Spinner spinnerType, spinnerCompany;
    Button btnContinue;

    ArrayList<String> arrayType;
    ArrayAdapter<String> adapterType;

    ArrayList<String> arrayCompanyMandatory, arrayCompanyFull;
    ArrayAdapter<String> adapterCompany;

    VehicleModel vehicleModel;

    private DatabaseReference dbRef;

    String strRegisterNumber;

    int intYear;
    int intMonth;
    int intDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        strRegisterNumber = getIntent().getExtras().getString("details_register_number");
        init();

        String strYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        intYear = Integer.parseInt(strYear);

        String strMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        intMonth = Integer.parseInt(strMonth) + 1;

        String strDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        intDay = Integer.parseInt(strDay);


        arrayType = new ArrayList<>();
        arrayType.add("ضد الغير");
        arrayType.add("شامل");

        adapterType = new ArrayAdapter<>(getApplicationContext(), R.layout.layout_spinner, arrayType);
        spinnerType.setAdapter(adapterType);

        arrayCompanyMandatory = new ArrayList<>();
        arrayCompanyMandatory.add("التأمين الإلزامي");

        arrayCompanyFull = new ArrayList<>();
        arrayCompanyFull.add("شركة التأمين الأردنية");
        arrayCompanyFull.add("MetLife");
        arrayCompanyFull.add("شركة الشرق الأوسط للتأمين (GIG)");
        arrayCompanyFull.add("شركة المتحدة للتأمين");
        arrayCompanyFull.add("شركة التأمين الإسلامية");
        arrayCompanyFull.add("شركة المنارة للتأمين");
        arrayCompanyFull.add("شركة الأراضي المقدسة للتأمين");

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    adapterCompany = new ArrayAdapter<>(getApplicationContext(), R.layout.layout_spinner, arrayCompanyMandatory);
                    tvCost.setText("81 دينار");
                }

                if (position == 1) {
                    adapterCompany = new ArrayAdapter<>(getApplicationContext(), R.layout.layout_spinner, arrayCompanyFull);
                    tvCost.setText("600 دينار");

                }

                spinnerCompany.setAdapter(adapterCompany);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insuranceRenewal();
            }
        });

        setInsuranceDate();

    }

    private void init() {
        tvCost = findViewById(R.id.tv_insurance_cost);
        tvInsurance = findViewById(R.id.tv_insurance_date);
        spinnerType = findViewById(R.id.spinner_insurance_type);
        spinnerCompany = findViewById(R.id.spinner_insurance_company);
        btnContinue = findViewById(R.id.btn_insurance_continue);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strRegisterNumber);
    }

    private void insuranceRenewal() {
        String strInsuranceYear = vehicleModel.getInsuranceYear();
        String strInsuranceMonth = vehicleModel.getInsuranceMonth();
        String strInsuranceDay = vehicleModel.getInsuranceDay();

        if (intYear > Integer.parseInt(strInsuranceYear)) {

            strInsuranceType = spinnerType.getSelectedItem().toString();
            strInsuranceCompany = spinnerCompany.getSelectedItem().toString();

            if (strInsuranceType.equals("ضد الغير")) {
                Random r = new Random();
                int low = 1;
                int high = 8;
                int result = r.nextInt(high - low) + low;

                switch (result) {
                    case 1:
                        strInsuranceCompany = "شركة التأمين الأردنية";
                        break;
                    case 2:
                        strInsuranceCompany = "MetLife";
                        break;
                    case 3:
                        strInsuranceCompany = "شركة الشرق الأوسط للتأمين (GIG)";
                        break;
                    case 4:
                        strInsuranceCompany = "شركة المتحدة للتأمين";
                        break;
                    case 5:
                        strInsuranceCompany = "شركة التأمين الإسلامية";
                        break;
                    case 6:
                        strInsuranceCompany = "شركة المنارة للتأمين";
                        break;
                    default:
                        strInsuranceCompany = "شركة الأراضي المقدسة للتأمين";
                }
            }
            Intent intent = new Intent(InsuranceActivity.this, PayInsuranceActivity.class);
            intent.putExtra("infraction_register_number", strRegisterNumber);
            startActivity(intent);

        } else if (intYear == Integer.parseInt(strInsuranceYear) && Integer.parseInt(strInsuranceMonth) - intMonth <= 1) {
            strInsuranceType = spinnerType.getSelectedItem().toString();
            strInsuranceCompany = spinnerCompany.getSelectedItem().toString();

            if (strInsuranceType.equals("ضد الغير")) {
                Random r = new Random();
                int low = 1;
                int high = 8;
                int result = r.nextInt(high - low) + low;

                switch (result) {
                    case 1:
                        strInsuranceCompany = "شركة التأمين الأردنية";
                        break;
                    case 2:
                        strInsuranceCompany = "MetLife";
                        break;
                    case 3:
                        strInsuranceCompany = "شركة الشرق الأوسط للتأمين (GIG)";
                        break;
                    case 4:
                        strInsuranceCompany = "شركة المتحدة للتأمين";
                        break;
                    case 5:
                        strInsuranceCompany = "شركة التأمين الإسلامية";
                        break;
                    case 6:
                        strInsuranceCompany = "شركة المنارة للتأمين";
                        break;
                    default:
                        strInsuranceCompany = "شركة الأراضي المقدسة للتأمين";
                }
            }
            Intent intent = new Intent(InsuranceActivity.this, PayInsuranceActivity.class);
            intent.putExtra("infraction_register_number", strRegisterNumber);
            startActivity(intent);

        } else {
            Toast.makeText(this, "تأمين المركبة ما زال جاري, لا يمكن تجديد التأمين", Toast.LENGTH_SHORT).show();

        }

    }

    private void setInsuranceDate() {
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vehicleModel = snapshot.getValue(VehicleModel.class);
                tvInsurance.setText(vehicleModel.getInsuranceDay() + "/" + vehicleModel.getInsuranceMonth() + "/"
                        + vehicleModel.getInsuranceYear());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}