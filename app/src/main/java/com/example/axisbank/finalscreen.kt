package com.example.axisbank

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.axisbank.databinding.ActivityFinalscreenBinding

class finalscreen : AppCompatActivity() {

    private lateinit var binding: ActivityFinalscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}