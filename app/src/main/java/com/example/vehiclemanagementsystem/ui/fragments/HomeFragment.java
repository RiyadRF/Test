package com.example.vehiclemanagementsystem.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.adapter.HomeAdapter;
import com.example.vehiclemanagementsystem.model.HomeModel;
import com.example.vehiclemanagementsystem.model.UserModel;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;
    TextView tvNoVehicle;

    private RecyclerView recyclerHome;

    private DatabaseReference dbRef;
    private FirebaseAuth auth;

    private ArrayList<VehicleModel> vehicleModels;
    HomeAdapter adapter;

    int vehicleCount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();

        setUpRecyclerView();
        return view;
    }

    private void init() {
        tvNoVehicle = view.findViewById(R.id.tv_home_no_vehicle);
        adapter = new HomeAdapter(getContext(), vehicleModels);
        recyclerHome = view.findViewById(R.id.recycler_home);

        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("Vehicles");
        dbRef.keepSynced(true);
    }

    private void setUpRecyclerView() {
        recyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        vehicleModels = new ArrayList<>();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    VehicleModel vehicleModel = dataSnapshot1.getValue(VehicleModel.class);
                    vehicleModels.add(vehicleModel);
                    vehicleCount ++;
                }
                if (vehicleCount > 0){
                    tvNoVehicle.setVisibility(View.GONE);
                }else {
                    tvNoVehicle.setVisibility(View.VISIBLE);
                }

                adapter = new HomeAdapter(getContext(), vehicleModels);
                adapter.notifyDataSetChanged();
                recyclerHome.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}