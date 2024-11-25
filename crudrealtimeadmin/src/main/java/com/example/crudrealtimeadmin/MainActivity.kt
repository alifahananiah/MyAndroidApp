package com.example.crudrealtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.crudrealtimeadmin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            val intent = Intent(this@MainActivity, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnUpdate1.setOnClickListener {
            val intent = Intent(this@MainActivity, UpdateActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnDelete1.setOnClickListener {
            val intent = Intent(this@MainActivity, DeleteActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnView.setOnClickListener {
            val intent = Intent(this@MainActivity, ViewActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnClose.setOnClickListener {
            finishAffinity() // Close all activities
            System.exit(0) // Exit the application
        }
    }
}

