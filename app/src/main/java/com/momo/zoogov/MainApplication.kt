package com.momo.zoogov

import android.app.Application
import com.momo.zoogov.ui.retrofit.ApiFactory
import com.momo.zoogov.ui.retrofit.ApiService
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class MainApplication: Application() {

    private var apiService: ApiService? = null
    private var scheduler: Scheduler? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun getInstance(): MainApplication? {
            return instance
        }
    }

    fun getApiService(): ApiService? {
        if (apiService == null) {
            apiService = ApiFactory.create()
        }
        return apiService
    }

    fun subscribeScheduler(): Scheduler? {
        if (scheduler == null) {
            scheduler = Schedulers.io()
        }
        return scheduler
    }
}