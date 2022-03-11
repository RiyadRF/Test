package com.example.vehiclemanagementsystem.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PayActivity extends AppCompatActivity {

    EditText edCardName, edCardNumber, edCardMonth, edCardYear, edCardPassword;
    TextView tvCost;
    Button btnPay;

    int intYear;
    int intMonth;

    String strRegisterNumber;
    String strCost;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        strRegisterNumber = getIntent().getExtras().getString("infraction_register_number");
        strCost = getIntent().getExtras().getString("infraction_cost");
        init();
        tvCost.setText(strCost + " دينار");


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
                checkCardData();
            }
        });

    }

    private void init() {
        edCardName = findViewById(R.id.ed_pay_card_name);
        edCardNumber = findViewById(R.id.ed_pay_card_number);
        edCardMonth = findViewById(R.id.ed_pay_card_month);
        edCardYear = findViewById(R.id.ed_pay_card_year);
        edCardPassword = findViewById(R.id.ed_pay_card_password);
        tvCost = findViewById(R.id.tv_pay_cost);
        btnPay = findViewById(R.id.btn_pay);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(strRegisterNumber).child("Infraction");
    }

    private void checkCardData() {
        if (!edCardName.getText().toString().isEmpty()
                || !edCardNumber.getText().toString().isEmpty() || !edCardMonth.getText().toString().isEmpty()
                || !edCardYear.getText().toString().isEmpty() || !edCardPassword.getText().toString().isEmpty()) {

            if (edCardNumber.getText().length() == 16) {


                if (Integer.parseInt(edCardYear.getText().toString()) > intYear
                        ||( Integer.parseInt(edCardYear.getText().toString()) == intYear
                        && Integer.parseInt(edCardMonth.getText().toString()) > intMonth)) {

                    if (edCardMonth.getText().length() == 2 && edCardYear.getText().length() == 2) {
                        if (edCardPassword.getText().length() == 3) {

                            dbRef.removeValue();
                            Toast.makeText(this, "تم دفع جميع المخالفات بشكل صحيح", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PayActivity.this, MainActivity.class);
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