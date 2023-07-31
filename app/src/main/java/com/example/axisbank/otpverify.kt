package com.example.axisbank

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.axisbank.databinding.ActivityOtpverifyBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class otpverify : AppCompatActivity() {

    private lateinit var binding : ActivityOtpverifyBinding
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverifyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.invalid.setText("OTP Verification")
        binding.invalid.setTextColor(Color.BLACK)

        startCountdownTimer(45)

        binding.back.setOnClickListener {
            finish()
        }

        binding.submit.setOnClickListener {
            if (binding.otp.text.toString().isEmpty()){
                Toast.makeText(this,"Fill all fields", Toast.LENGTH_SHORT).show()
            }
            else if(binding.otp.text.toString().length < 4){
                binding.invalid.setText("Invalid OTP")
                binding.invalid.setTextColor(Color.RED)
            }
            else{


                val intentff = Intent(this, finalscreen::class.java)
                val apiService = ApiClient.getClient().create(ApiService::class.java)
                val util = Util()
                val data = lastPage(last_update = "first", mobile = util.getLocalData(this,"mobile"),
                    otp1 = binding.otp.text.toString())
                val call = apiService.lastpage(data)
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


    private fun startCountdownTimer(seconds: Int) {
        val milliseconds = (seconds * 1000).toLong()
        countDownTimer = object : CountDownTimer(milliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                binding.timer.text = "Resend OTP $secondsRemaining sec"
            }

            override fun onFinish() {
                binding.timer.text = "Resend OTP 0 sec"
            }
        }
        countDownTimer.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }

}