package com.momo.zoogov.ui.viewmodel

import android.view.View
import androidx.navigation.Navigation
import com.momo.zoogov.data.PlantsResults
import com.momo.zoogov.ui.main.ContentFragmentDirections

class PlantsViewModel(plantsResults: PlantsResults) {

    private var mPlantsResults: PlantsResults = plantsResults

    fun onItemClick(view: View) {
        val action = ContentFragmentDirections.actionContentFragmentToWebFragment()
        action.argTitle = mPlantsResults?.F_Name_Ch ?: ""
//        action.webUrl = animalResults?.E_URL?: ""
        Navigation.findNavController(view).navigate(action)
    }

    fun changeDataSet(plantsResults: PlantsResults?) {
        mPlantsResults = plantsResults?:PlantsResults()
    }

    fun getName(): String {
        return mPlantsResults?.F_Name_Ch?:""
    }

    fun getInfo(): String {
        return mPlantsResults?.F_Brief?:""
    }

    fun getPicUrl(): String {
        return mPlantsResults?.F_Pic01_URL?:""
    }


}

