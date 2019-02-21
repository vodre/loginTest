package com.vodrex.login_sample.db.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

        @SerializedName("id_user")
        @Expose
        var idUser: Int?,

        @SerializedName("agente")
        @Expose
        var agente: String?,

        @SerializedName("error")
        @Expose
        var error: ResponseError?,

        @SerializedName("token")
        @Expose
        var token: String?

)

data class ResponseError(
        @SerializedName("id") val id: String?,
        @SerializedName("message") val message: String?,
        @SerializedName("title") val title: String?
)

