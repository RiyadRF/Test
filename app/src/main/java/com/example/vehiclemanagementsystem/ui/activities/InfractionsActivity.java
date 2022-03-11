package com.example.vehiclemanagementsystem.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.adapter.InfractionAdapter;
import com.example.vehiclemanagementsystem.model.InfractionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InfractionsActivity extends AppCompatActivity {

    TextView tvTotalInfractions, tvTotalCost;

    int cost = 0;
    Button btnPay;

    private RecyclerView recyclerInfraction;

    private DatabaseReference dbRef;
    private ArrayList<InfractionModel> infractionModels;
    InfractionAdapter adapter;
    String registerNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infractions);
        registerNumber = getIntent().getExtras().getString("details_register_number");

        init();

        setUpRecyclerView();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfractionsActivity.this,PayActivity.class);
                intent.putExtra("infraction_register_number",registerNumber);
                intent.putExtra("infraction_cost",String.valueOf(cost));
                startActivity(intent);
            }
        });
    }

    private void init() {
        tvTotalInfractions = findViewById(R.id.tv_infractions_total);
        tvTotalCost = findViewById(R.id.tv_infractions_total_cost);
        btnPay= findViewById(R.id.btn_infraction_pay);

        adapter = new InfractionAdapter(this, infractionModels);

        recyclerInfraction = findViewById(R.id.recycler_infractions);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Vehicles").child(registerNumber).child("Infraction");
        dbRef.keepSynced(true);
    }

    private void setUpRecyclerView() {
        recyclerInfraction.setLayoutManager(new LinearLayoutManager(this));
        infractionModels = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    InfractionModel infractionModel = dataSnapshot1.getValue(InfractionModel.class);
                    infractionModels.add(infractionModel);
                    cost = cost + Integer.parseInt(infractionModel.getInfractionCost());
                    tvTotalCost.setText(cost + " دينار ");
                }
                if (cost == 0){
                    btnPay.setVisibility(View.GONE);
                }
                adapter = new InfractionAdapter(InfractionsActivity.this, infractionModels);
                adapter.notifyDataSetChanged();
                recyclerInfraction.setAdapter(adapter);

                tvTotalInfractions.setText(String.valueOf(infractionModels.size()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}