package com.vodrex.login_sample.di


import com.vodrex.login_sample.api.CompanyRepository
import com.vodrex.login_sample.api.LoginRepository
import com.vodrex.login_sample.viewmodel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


object KoinDSL {

    val viewModelModule = module {
        viewModel { LoginViewModel(get()) }
        viewModel { CompaniesViewModel(get()) }
        viewModel { PaymentsViewModel(get()) }
    }

    val apiModule = module {
        single { CredentialsRepository(loginRepository = get()) }
        single { CompaniesRepository(companyRepository = get()) }
        single { LoginRepository(remote = get(), local = get()) }
        single { CompanyRepository(local = get()) }
    }

    val networkModule = module {
        single { NetworkModule.getRemote() }
        single { NetworkModule.getLocal(androidContext()) }
    }
}