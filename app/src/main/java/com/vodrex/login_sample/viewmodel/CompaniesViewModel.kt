package com.vodrex.login_sample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vodrex.login_sample.R
import com.vodrex.login_sample.db.entities.Company
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CompaniesViewModel(private val repository: CompaniesRepository) : ViewModel() {

    private var disposable = CompositeDisposable()
    val companiesLiveData = MutableLiveData<List<Company>>()

    init {
        storeCompanies( listOf(
                Company(1,"Claro", "ta-mb-mb",  R.drawable.ic_claro),
                Company(2,"Tuenti", "ta-ta-ta",  R.drawable.ic_tuenti),
                Company(3,"Entel", "ta-ta-ta", R.drawable.ic_entel)
            )
        )
    }

    fun getCompanyList() {
        disposable.clear()
        disposable.add(repository.getCompanies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                companiesLiveData.postValue(it)
            })

    }

    fun storeCompanies(list: List<Company>) {
       repository.storeCompanies(list)
        getCompanyList()
    }

    override fun onCleared() {
        disposable.dispose()
    }

    fun refreshData() {
        disposable.clear()
    }
}