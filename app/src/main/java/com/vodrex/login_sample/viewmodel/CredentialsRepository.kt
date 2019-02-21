package com.vodrex.login_sample.viewmodel

import com.vodrex.login_sample.api.LoginRepository
import com.vodrex.login_sample.db.entities.LoginRequest
import com.vodrex.login_sample.db.entities.LoginResponse
import io.reactivex.Observable
import org.json.JSONObject
import java.util.concurrent.TimeUnit


class CredentialsRepository(private val loginRepository: LoginRepository) {


    fun attemptLogin(data: LoginRequest): Observable<LoginResponse> = loginRepository
        .postLogin(data).debounce(400, TimeUnit.MILLISECONDS)
}