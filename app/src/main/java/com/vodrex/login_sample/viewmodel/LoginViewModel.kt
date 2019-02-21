package com.vodrex.login_sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vodrex.login_sample.db.entities.LoginRequest
import com.vodrex.login_sample.db.entities.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val repository: CredentialsRepository) : ViewModel() {

    private var disposable = CompositeDisposable()

    val loginLiveData = MutableLiveData<LoginResponse>()

    init {

    }

    fun loginCredentials(data: LoginRequest) {
        disposable.clear()
        disposable.add(repository.attemptLogin(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
              loginLiveData.postValue(it)
            })
    }

    override fun onCleared() {
        disposable.dispose()
    }

    fun refreshData() {
        disposable.clear()
    }
}