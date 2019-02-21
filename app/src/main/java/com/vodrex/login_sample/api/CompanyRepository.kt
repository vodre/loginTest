package com.vodrex.login_sample.api

import com.vodrex.login_sample.db.AppDatabase
import com.vodrex.login_sample.db.entities.Company
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CompanyRepository(private val local: AppDatabase) {
    fun storeCompaniesInDb(companies: List<Company>) {
        Observable.fromCallable { local.companyDao().insertCompanies(companies) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
            }
    }


    fun getCompaniesFromDb(): Observable<List<Company>> {
        return local.companyDao().getCompanies()
            .toObservable()
            .doOnNext {
            }.doOnError {
            }
    }

    fun getCompanyFromDb(company : String): Observable<Company> {
        return local.companyDao().getCompany(company)
            .toObservable()
            .doOnNext {
            }.doOnError {
            }
    }
}