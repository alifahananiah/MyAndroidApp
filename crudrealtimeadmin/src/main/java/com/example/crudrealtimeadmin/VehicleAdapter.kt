package com.example.crudrealtimeadmin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealtimeadmin.databinding.ItemBinding

class VehicleAdapter(private val vehicleList: List<Vehicle>) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicleList[position]
        holder.bind(vehicle)
    }

    override fun getItemCount(): Int = vehicleList.size

    inner class VehicleViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: Vehicle) {
            binding.tvOwnerName.text = vehicle.ownerName
            binding.tvVehicleBrand.text = vehicle.vehicleBrand
            binding.tvVehicleRTO.text = vehicle.vehicleRTO
            binding.tvVehicleNumber.text = vehicle.vehicleNumber
        }
    }
}
