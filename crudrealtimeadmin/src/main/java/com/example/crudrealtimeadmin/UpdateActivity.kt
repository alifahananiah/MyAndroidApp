package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            val referenceVehicleNumber = binding.referenceVehicleNumber.text.toString()
            val updateOwnerName = binding.updateOwnerName.text.toString()
            val updateVehicleBrand = binding.updateVehicleBrand.text.toString()
            val updateVehicleRTO = binding.updateVehicleRTO.text.toString()

            updateVehicleData(referenceVehicleNumber, updateOwnerName, updateVehicleBrand, updateVehicleRTO)
        }

        binding.btnBack1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Go back to MainActivity
            startActivity(intent)
            finish() // Finish UpdateActivity to prevent it from staying in the back stack
        }
    }

    private fun updateVehicleData(
        vehicleNumber: String,
        ownerName: String,
        vehicleBrand: String,
        vehicleRTO: String
    ) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String, String>(
            "ownerName" to ownerName,
            "vehicleBrand" to vehicleBrand,
            "vehicleRTO" to vehicleRTO
        )
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.referenceVehicleNumber.text.clear()
            binding.updateOwnerName.text.clear()
            binding.updateVehicleBrand.text.clear()
            binding.updateVehicleRTO.text.clear()
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}

