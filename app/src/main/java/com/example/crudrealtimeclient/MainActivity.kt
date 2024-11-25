package com.example.crudrealtimeclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Initialize binding
        setContentView(binding.root)

        // Set up the search button click listener
        binding.btnSearch.setOnClickListener {
            val searchVehicleNumber: String = binding.searchVehicle.text.toString() // Use EditText for vehicle number input
            if (searchVehicleNumber.isNotEmpty()) {
                readData(searchVehicleNumber)  // Call readData to fetch the data
            } else {
                Toast.makeText(this, "Please enter the vehicle number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnExit.setOnClickListener {
            finishAffinity() // Close all activities
            System.exit(0) // Exit the application
        }

    }

    // Function to read vehicle data from Firebase using the vehicle number
    private fun readData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information") // Firebase reference for vehicle data
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {

            if (it.exists()) {
                // Retrieve the data from Firebase
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRTO = it.child("vehicleRTO").value

                // Display success message and clear the search field
                Toast.makeText(this, "Results Found", Toast.LENGTH_SHORT).show()
                binding.searchVehicle.text.clear() // Clear the input field

                // Display the fetched data in TextViews
                binding.readOwnerName.text = ownerName.toString()
                binding.readVehicleBrand.text = vehicleBrand.toString()
                binding.readVehicleTRO.text = vehicleRTO.toString()

            } else {
                // Show message if vehicle number does not exist in the database
                Toast.makeText(this, "Vehicle number does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            // Handle failure when reading data from Firebase
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
        }
    }
}
