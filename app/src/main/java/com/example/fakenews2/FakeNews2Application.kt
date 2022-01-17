package com.example.fakenews2

import android.app.Application
import com.example.data.di.dataModule
import com.example.fakenews2.di.appModule
import com.example.fakenews2.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FakeNews2Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@FakeNews2Application)
            modules(
                appModule,
                domainModule,
                dataModule
            )
        }
    }
}