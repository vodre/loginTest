package com.vodrex.login_sample.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vodrex.login_sample.db.entities.Company
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentsViewModel(private val repository: CompaniesRepository) : ViewModel() {

    private var disposable = CompositeDisposable()
    val imagePath = ObservableField(0)
    val company = MutableLiveData<Company>()

    init {
    }

    fun getCompany(name: String) {
        disposable.clear()
        disposable.add(repository.getCompany(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                imagePath.set(it.imagePath)
                company.postValue(it)
            })

    }

    override fun onCleared() {
        disposable.dispose()
    }
}