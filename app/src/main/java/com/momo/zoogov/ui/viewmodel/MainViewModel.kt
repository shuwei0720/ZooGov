package com.momo.zoogov.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import com.momo.zoogov.MainApplication
import com.momo.zoogov.data.AnimalResults
import com.momo.zoogov.data.ZooInfo
import com.momo.zoogov.ui.retrofit.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import java.util.*

class MainViewModel : Observable() {

    var zooRecycler: ObservableInt = ObservableInt(View.GONE)
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()

    private var animals: ArrayList<AnimalResults> = arrayListOf()

    fun fetchList() {

        val apiService: ApiService? = MainApplication.getInstance()?.getApiService()
        apiService?.let { it ->

            val disposable = it.getZooInfo()
                .subscribeOn(MainApplication.getInstance()?.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<ZooInfo> { zoo ->
                    if (zoo.result?.count?:0 > 0) {
                        zoo.result?.results?.let { animals ->
                            changeDataSet(animals)
                            zooRecycler.set(View.VISIBLE)
                        }
                    }
                }, Consumer<Throwable> {
                    zooRecycler.set(View.GONE)
                })

            compositeDisposable?.add(disposable)
        }
    }

    private fun changeDataSet(result: List<AnimalResults>) {
        Log.d("govzoo", "MainViewModel-changeDataSet: ${result.size}")
        animals.addAll(result)
        setChanged()
        notifyObservers()
    }

    fun getAnimalList(): List<AnimalResults> {
        Log.d("govzoo", "MainViewModel-getAnimalList: ${animals.size}")
        return animals
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

