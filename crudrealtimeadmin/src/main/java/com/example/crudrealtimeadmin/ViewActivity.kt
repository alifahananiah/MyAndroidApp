package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crudrealtimeadmin.databinding.ActivityViewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var vehicleList: MutableList<Vehicle>
    private lateinit var vehicleAdapter: VehicleAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vehicleList = mutableListOf()
        vehicleAdapter = VehicleAdapter(vehicleList)
        binding.recyclerView.adapter = vehicleAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Back button listener
        binding.btnBack3.setOnClickListener {
            Toast.makeText(this, "Back button clicked!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Fetch data from Firebase
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")

        // Listening to changes in the Firebase database
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    vehicleList.clear()  // Clear existing data
                    for (vehicleSnapshot in snapshot.children) {
                        val vehicle = vehicleSnapshot.getValue(Vehicle::class.java)
                        if (vehicle != null) {
                            vehicleList.add(vehicle)
                        }
                    }
                    vehicleAdapter.notifyDataSetChanged()  // Notify adapter to refresh data
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ViewActivity, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
