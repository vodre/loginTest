package com.vodrex.login_sample.api

import com.vodrex.login_sample.db.AppDatabase
import com.vodrex.login_sample.db.entities.LoginRequest
import com.vodrex.login_sample.db.entities.LoginResponse
import io.reactivex.Observable
import org.json.JSONObject

class LoginRepository(private val remote: AppService, private val local: AppDatabase) {
    fun postLogin(data: LoginRequest?): Observable<LoginResponse> {
        return remote.postLogin(data)
    }

}