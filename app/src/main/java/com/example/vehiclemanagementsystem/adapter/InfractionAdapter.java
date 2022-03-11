package com.example.vehiclemanagementsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vehiclemanagementsystem.R;
import com.example.vehiclemanagementsystem.model.InfractionModel;
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.example.vehiclemanagementsystem.ui.activities.PayActivity;
import com.example.vehiclemanagementsystem.ui.activities.VehicleDetailsActivity;

import java.util.ArrayList;

public class InfractionAdapter extends RecyclerView.Adapter<InfractionAdapter.ViewHolder> {

    Context context;
    ArrayList<InfractionModel> infractionModels;


    public InfractionAdapter(Context context, ArrayList<InfractionModel> infractionModels) {
        this.context = context;
        this.infractionModels = infractionModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_infractions,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfractionAdapter.ViewHolder holder, int position) {
        holder.tvInfractionType.setText(infractionModels.get(position).getInfractionType());
        holder.tvInfractionNumber.setText(infractionModels.get(position).getInfractionNumber());
        holder.tvInfractionCost.setText(infractionModels.get(position).getInfractionCost());
        holder.tvInfractionDate.setText(infractionModels.get(position).getInfractionDate());
        holder.tvInfractionLocation.setText(infractionModels.get(position).getInfractionLocation());
        holder.tvInfractionMunicipal.setText(infractionModels.get(position).getInfractionMunicipal());
        holder.tvInfractionEfwaterkoom.setText(infractionModels.get(position).getInfractionEfwaterkoom());


    }

    @Override
    public int getItemCount() {
        return infractionModels.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvInfractionType;
        TextView tvInfractionNumber;
        TextView tvInfractionCost;
        TextView tvInfractionDate;
        TextView tvInfractionLocation;
        TextView tvInfractionMunicipal;
        TextView tvInfractionEfwaterkoom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvInfractionType = itemView.findViewById(R.id.tv_infractions_type);
            tvInfractionNumber = itemView.findViewById(R.id.tv_infractions_number);
            tvInfractionCost = itemView.findViewById(R.id.tv_infractions_cost);
            tvInfractionDate = itemView.findViewById(R.id.tv_infractions_date);
            tvInfractionLocation = itemView.findViewById(R.id.tv_infractions_location);
            tvInfractionMunicipal = itemView.findViewById(R.id.tv_infractions_municipal);
            tvInfractionEfwaterkoom = itemView.findViewById(R.id.tv_infractions_efwatekom);


        }
    }
}
