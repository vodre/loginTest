package com.vodrex.login_sample.viewmodel

import com.vodrex.login_sample.api.CompanyRepository
import com.vodrex.login_sample.db.entities.Company
import com.vodrex.login_sample.db.entities.LoginResponse
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class CompaniesRepository(private val companyRepository: CompanyRepository) {

    fun storeCompanies(companies : List<Company>) = companyRepository
        .storeCompaniesInDb(companies)

    fun getCompanies(): Observable<List<Company>> = companyRepository
        .getCompaniesFromDb().debounce(400, TimeUnit.MILLISECONDS)

    fun getCompany(name: String): Observable<Company> = companyRepository
        .getCompanyFromDb(name).debounce(400, TimeUnit.MILLISECONDS)
}