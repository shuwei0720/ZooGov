package com.momo.zoogov.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.navigation.Navigation
import com.momo.zoogov.MainApplication
import com.momo.zoogov.data.AnimalResults
import com.momo.zoogov.data.PlantsInfo
import com.momo.zoogov.data.PlantsResults
import com.momo.zoogov.ui.main.ContentFragmentDirections
import com.momo.zoogov.ui.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import io.reactivex.functions.Consumer

class ContentViewModel  (private val animalResults: AnimalResults) : Observable(){

    var plantsRecycler: ObservableInt = ObservableInt(View.GONE)
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    private var plants: ArrayList<PlantsResults> = arrayListOf()

    fun onWebClick(view: View) {
        val action = ContentFragmentDirections.actionContentFragmentToWebFragment()
        action.argTitle = animalResults?.E_Name?: ""
        action.webUrl = animalResults?.E_URL?: ""
        Navigation.findNavController(view).navigate(action)
    }

    fun getInfo(): String {
        return animalResults?.E_Info?:""
    }

    fun getCategory(): String {
        return animalResults?.E_Category?:""
    }

    fun getMemo(): String {
        return animalResults?.E_Memo?:""
    }

    fun fetchList() {

        val apiService: ApiService? = MainApplication.getInstance()?.getApiService()
        apiService?.let { it ->

            val disposable = it.getPlantsInfo()
                .subscribeOn(MainApplication.getInstance()?.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<PlantsInfo> { plants ->
                    if (plants.result?.count?:0 > 0) {
                        plants.result?.results?.let { plant ->
                            changeDataSet(plant)
                            plantsRecycler.set(View.VISIBLE)
                        }
                    }
                }, Consumer<Throwable> {
                    plantsRecycler.set(View.GONE)
                })

            compositeDisposable?.add(disposable)
        }
    }

    fun getPlantsList(): List<PlantsResults> {
        Log.d("govzoo", "MainViewModel-getAnimalList: ${plants.size}")
        return plants
    }

    private fun changeDataSet(result: List<PlantsResults>) {
        Log.d("govzoo", "MainViewModel-changeDataSet: ${result.size}")
        plants.addAll(result)
        setChanged()
        notifyObservers()
    }

    private fun unSubscribeFromObservable() {

        compositeDisposable?.let {
            it.dispose()
        }
    }

    fun reset() {

        unSubscribeFromObservable()
        compositeDisposable = null
    }
}

