package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val ownerName = binding.uploadOwnerName.text.toString()
            val vehicleBrand = binding.uploadVehicleBrand.text.toString()
            val vehicleRTO = binding.uploadVehicleRTO.text.toString()
            val vehicleNumber = binding.uploadVehicleNumber.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
            val vehicleData = VehicleData(ownerName, vehicleBrand, vehicleRTO, vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                binding.uploadOwnerName.text.clear()
                binding.uploadVehicleBrand.text.clear()
                binding.uploadVehicleRTO.text.clear()
                binding.uploadVehicleBrand.text.clear()

                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent( this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Go back to MainActivity
            startActivity(intent)
            finish() // Finish UpdateActivity to prevent it from staying in the back stack
        }
    }
}