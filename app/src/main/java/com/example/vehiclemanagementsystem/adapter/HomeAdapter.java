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
import com.example.vehiclemanagementsystem.model.VehicleModel;
import com.example.vehiclemanagementsystem.ui.activities.DeleteActivity;
import com.example.vehiclemanagementsystem.ui.activities.VehicleDetailsActivity;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    ArrayList<VehicleModel> vehicleModels;

    private OnItemClickListener mlistener;


    public HomeAdapter(Context context, ArrayList<VehicleModel> vehicleModels) {
        this.context = context;
        this.vehicleModels = vehicleModels;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home,
                parent, false);

        return new ViewHolder(view, mlistener);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        holder.tvPreNum.setText(vehicleModels.get(position).getPrefixNumber());
        holder.tvCarNum.setText(vehicleModels.get(position).getVehicleNumber());
        holder.tvOwnerNAme.setText(vehicleModels.get(position).getVehicleOwnerName());
        holder.tvRegNum.setText(vehicleModels.get(position).getRegistrationNumber());

    }

    @Override
    public int getItemCount() {
        return vehicleModels.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPreNum;
        TextView tvCarNum;
        TextView tvRegNum;
        TextView tvOwnerNAme;


        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            tvPreNum = itemView.findViewById(R.id.tv_home_pre_number);
            tvCarNum = itemView.findViewById(R.id.tv_home_car_number);
            tvRegNum = itemView.findViewById(R.id.tv_home_registration_num);
            tvOwnerNAme = itemView.findViewById(R.id.tv_home_owner_name);

            itemView.findViewById(R.id.btn_hone_details).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, VehicleDetailsActivity.class);
                    intent.putExtra("register_number", tvRegNum.getText().toString());
                    context.startActivity(intent);
                }
            });
            itemView.findViewById(R.id.btn_home_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DeleteActivity.class);
                    intent.putExtra("register_number", tvRegNum.getText().toString());
                    context.startActivity(intent);

                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
