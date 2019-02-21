package com.vodrex.login_sample

import android.app.Application
import com.vodrex.login_sample.di.KoinDSL
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        // Koin
        startKoin(this, listOf(
            KoinDSL.networkModule,
            KoinDSL.viewModelModule,
            KoinDSL.apiModule

        ))
    }
}
