package com.vodrex.login_sample.db.entities


import com.google.gson.annotations.SerializedName

data class LoginRequest(@SerializedName("data") val data: Data)

data class Data(
        @SerializedName("pass") val pass: String,
        @SerializedName("user") val user: String
)