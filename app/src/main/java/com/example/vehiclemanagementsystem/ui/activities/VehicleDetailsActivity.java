package com.example.vehiclemanagementsystem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class VehicleDetailsActivity extends AppCompatActivity {

    TextView tvVehicleOwnerName, tvAddress, tvVehicleNumber, tvRegistrationNumber, tvChassisNumber, tvColor, tvEngineNumber, tvEngineSize,
            tvFuel, tvInsurance, tvLicensingCenter, tvManufacturingYear, tvVehicleClass, tvVehicleType, tvDay, tvMonth, tvYear,
            tvVehicleGroup,tvAdjectiveUse,tvRegistrationAttribute,tvInsuranceCompany,tvPassenger,tvWeight,tvPolisa,tvVehicleInsuranceDate;

    Button btnInfraction, btnVehicleRenewal, btnVehicleInsurance;
    String strRegisterNumber;

    VehicleModel vehicleModel;
    private DatabaseReference dbRef,dbRefCheckVehicle, dbRefVehicleInUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);
        strRegisterNumber = getIntent().getExtras().getString("register_number");

        init();

        tvRegistrationNumber.setText(strRegisterNumber);
        initLicense();

        btnInfraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleDetailsActivity.this, InfractionsActivity.class);
                intent.putExtra("details_register_number", strRegisterNumber);
                startActivity(intent);
            }
        });

        btnVehicleInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleDetailsActivity.this, InsuranceActivity.class);
                intent.putExtra("details_register_number", strRegisterNumber);
                startActivity(intent);
            }
        });

        btnVehicleRenewal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleDetailsActivity.this, VehicleRenewalActivity.class);
                intent.putExtra("details_register_number", strRegisterNumber);
                startActivity(intent);
            }
        });

    }

    private void init() {
        tvVehicleOwnerName = findViewById(R.id.tv_vehicle_det_front_owner_name);
        tvAddress = findViewById(R.id.tv_vehicle_det_front_address);
        tvVehicleInsuranceDate = findViewById(R.id.tv_vehicle_det_insurance_date);

        tvVehicleNumber = findViewById(R.id.tv_vehicle_det_front_vehicle_number);
        tvRegistrationNumber = findViewById(R.id.tv_vehicle_det_back_registration_number);
        tvChassisNumber = findViewById(R.id.tv_vehicle_det_back_chassis_number);
        tvColor = findViewById(R.id.tv_vehicle_det_front_vehicle_color);
        tvEngineNumber = findViewById(R.id.tv_vehicle_det_back_engine_number);
        tvEngineSize = findViewById(R.id.tv_vehicle_det_back_engine_size);
        tvFuel = findViewById(R.id.tv_vehicle_det_back_fuel);
        tvInsurance = findViewById(R.id.tv_vehicle_det_back_insurance_type);
        tvLicensingCenter = findViewById(R.id.tv_vehicle_det_back_licensing_center);
        tvManufacturingYear = findViewById(R.id.tv_vehicle_det_front_manufacturing_year);
        tvVehicleClass = findViewById(R.id.tv_vehicle_det_front_vehicle_class);
        tvVehicleType = findViewById(R.id.tv_vehicle_det_front_vehicle_type);
        tvDay = findViewById(R.id.tv_vehicle_det_front_day);
        tvMonth = findViewById(R.id.tv_vehicle_det_front_month);
        tvYear = findViewById(R.id.tv_vehicle_det_front_year);
        btnInfraction = findViewById(R.id.btn_vehicle_det_infractions);
        tvVehicleGroup = findViewById(R.id.tv_vehicle_det_front_vehicle_Group);
        tvAdjectiveUse = findViewById(R.id.tv_vehicle_det_front_adjective_use);
        tvRegistrationAttribute = findViewById(R.id.tv_vehicle_det_front_registration_attribute);
        tvPolisa = findViewById(R.id.tv_vehicle_det_back_polisa_number);

        tvInsuranceCompany = findViewById(R.id.tv_vehicle_det_back_insurance_company);
        tvPassenger = findViewById(R.id.tv_vehicle_det_back_passengers);
        tvWeight = findViewById(R.id.tv_vehicle_det_back_weight);
        btnVehicleRenewal = findViewById(R.id.btn_vehicle_det_Vehicle_license_renewal);
        btnVehicleInsurance = findViewById(R.id.btn_vehicle_det_insurance);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strRegisterNumber);
        dbRefVehicleInUser = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid())
        .child("Vehicles").child(strRegisterNumber);
        dbRefCheckVehicle = FirebaseDatabase.getInstance().getReference().child("Vehicles");
    }

    private void initLicense() {
        Query query = dbRefCheckVehicle.orderByChild("registrationNumber").equalTo(strRegisterNumber);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            vehicleModel = snapshot.getValue(VehicleModel.class);

                            tvVehicleOwnerName.setText(vehicleModel.getVehicleOwnerName());

                            tvVehicleGroup.setText(vehicleModel.getVehicleGroup());
                            tvAdjectiveUse.setText(vehicleModel.getAdjectiveUse());
                            tvRegistrationAttribute.setText(vehicleModel.getRegistrationAttribute());
                            tvInsuranceCompany.setText(vehicleModel.getInsuranceCompany());
                            tvPassenger.setText(vehicleModel.getPassengers());
                            tvWeight.setText(vehicleModel.getWeight());
                            tvPolisa.setText(vehicleModel.getPolisaNumber());

                            tvAddress.setText(vehicleModel.getAddress());
                            String prefixNumber = vehicleModel.getPrefixNumber();
                            tvVehicleNumber.setText(prefixNumber + "-" + vehicleModel.getVehicleNumber());
                            tvChassisNumber.setText(vehicleModel.getChassisNumber());
                            tvColor.setText(vehicleModel.getColor());
                            tvEngineNumber.setText(vehicleModel.getEngineNumber());
                            tvEngineSize.setText(vehicleModel.getEngineSize());
                            tvFuel.setText(vehicleModel.getFuel());
                            tvInsurance.setText(vehicleModel.getInsurance());
                            tvLicensingCenter.setText(vehicleModel.getLicensingCenter());
                            tvManufacturingYear.setText(vehicleModel.getManufacturingYear());
                            tvVehicleClass.setText(vehicleModel.getVehicleClass());
                            tvVehicleType.setText(vehicleModel.getVehicleType());
                            tvDay.setText(vehicleModel.getDay() + "/");
                            tvMonth.setText(vehicleModel.getMonth() + "/");
                            tvYear.setText(vehicleModel.getYear());
                            tvVehicleInsuranceDate.setText(vehicleModel.getInsuranceDay()+"/"+vehicleModel.getInsuranceMonth()+"/"+vehicleModel.getInsuranceYear());

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(VehicleDetailsActivity.this, "تم حذف المركبة من قبل دائرة السير", Toast.LENGTH_SHORT).show();
                    dbRefVehicleInUser.getRef().removeValue();
                    Intent intent = new Intent(VehicleDetailsActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

}