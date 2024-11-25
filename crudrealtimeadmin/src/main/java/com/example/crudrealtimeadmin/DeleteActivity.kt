package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDelete.setOnClickListener {
            val vehicleNumber = binding.deleteVNumber.text.toString();
            if (vehicleNumber.isNotEmpty()) {
                deleteVehicleData(vehicleNumber)
            } else {
                Toast.makeText(this, "Please enter vehicle number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBack2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java) // Go back to MainActivity
            startActivity(intent)
            finish() // Finish UpdateActivity to prevent it from staying in the back stack
        }
    }

    private fun deleteVehicleData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deleteVNumber.text.clear()
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to Delete", Toast.LENGTH_SHORT).show()
        }
    }
}