package com.momo.zoogov.ui.retrofit

import com.momo.zoogov.data.PlantsInfo
import com.momo.zoogov.data.ZooInfo
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("apiAccess?scope=resourceAquire&rid=5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a")
    fun getZooInfo() : Observable<ZooInfo>

    @GET("apiAccess?scope=resourceAquire&rid=f18de02f-b6c9-47c0-8cda-50efad621c14")
    fun getPlantsInfo() : Observable<PlantsInfo>
}