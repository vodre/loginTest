package com.vodrex.login_sample.api

import com.vodrex.login_sample.db.entities.LoginRequest
import com.vodrex.login_sample.db.entities.LoginResponse
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface AppService {
    @Headers(
        "content-type:application/json",
        "SO:Android",
        "Version:2.5.2"
    )
    @POST("AgenteMovil.svc/agMov/login")
    fun postLogin(@Body data: LoginRequest?): Observable<LoginResponse>
}