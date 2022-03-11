package com.example.vehiclemanagementsystem.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.adapter.CheckCenterAdapter;
import com.example.vehiclemanagementsystem.model.CheckCenterModel;

import java.util.ArrayList;

public class CheckCenterFragment extends Fragment {
    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_center, container, false);
        ArrayList<CheckCenterModel> centerModels = new ArrayList<>();
        centerModels.add(new CheckCenterModel("مركز الشريف", "0791151112", "عمان - المدينة الرياضية"));
        centerModels.add(new CheckCenterModel("AutoScore", "(06) 582 8040", "عمان - بيادر وادي السير"));
        centerModels.add(new CheckCenterModel("شركة الصقري", "0795197544", "عمان - الوحدات"));
        centerModels.add(new CheckCenterModel("المحرك السريع", "0796151272", "عمان- ماركا"));

        recyclerView = view.findViewById(R.id.recycler_center);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());

        mAdapter = new CheckCenterAdapter(getContext(),centerModels);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


        return view;
    }
}