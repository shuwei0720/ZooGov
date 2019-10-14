package com.momo.zoogov.ui.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ApiFactory {

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://data.taipei/opendata/datalist/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService::class.java)
        }
    }

}