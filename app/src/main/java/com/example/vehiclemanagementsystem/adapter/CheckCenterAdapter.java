package com.example.vehiclemanagementsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.CheckCenterModel;
import com.example.vehiclemanagementsystem.model.VehicleModel;

import java.util.ArrayList;

public class CheckCenterAdapter extends RecyclerView.Adapter<CheckCenterAdapter.ViewHolder> {
    Context context;
    ArrayList<CheckCenterModel> centerModels;

    public CheckCenterAdapter(Context context, ArrayList<CheckCenterModel> centerModels) {
        this.context = context;
        this.centerModels = centerModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_check_center,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckCenterModel checkCenterModel = centerModels.get(position);

        holder.tvCenterName.setText(checkCenterModel.getCenterName());
        holder.tvCenterPhone.setText(checkCenterModel.getCenterPhone());
        holder.tvCenterLocation.setText(checkCenterModel.getCenterLocation());
    }

    @Override
    public int getItemCount() {
        return centerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCenterName, tvCenterPhone, tvCenterLocation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCenterName = itemView.findViewById(R.id.tv_center_name);
            tvCenterPhone = itemView.findViewById(R.id.tv_center_phone);
            tvCenterLocation = itemView.findViewById(R.id.tv_center_location);
        }
    }
}
