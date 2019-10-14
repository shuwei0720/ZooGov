package com.momo.zoogov.ui.viewmodel

import android.view.View
import androidx.navigation.Navigation
import com.momo.zoogov.data.AnimalResults
import com.momo.zoogov.ui.main.MainFragmentDirections

class ItemViewModel(animalResults: AnimalResults) {

    private var mAnimalResults: AnimalResults = animalResults

    fun onItemClick(view: View) {
        val action = MainFragmentDirections.actionMainFragmentToContentFragment(
            mAnimalResults)
        action.title = mAnimalResults?.E_Name ?: ""
        Navigation.findNavController(view).navigate(action)
    }

    fun changeDataSet(animalResults: AnimalResults?) {
        mAnimalResults = animalResults?:AnimalResults()
    }

    fun getName(): String {
        return mAnimalResults?.E_Name?:""
    }

    fun getInfo(): String {
        return mAnimalResults?.E_Info?:""
    }

    fun getMemo(): String {
        return mAnimalResults?.E_Memo?:""
    }

    fun getPicUrl(): String {
        return mAnimalResults?.E_Pic_URL?:""
    }

}

