package com.momo.zoogov.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.momo.zoogov.R
import com.momo.zoogov.data.AnimalResults
import com.momo.zoogov.databinding.ItemInfoBinding
import com.momo.zoogov.ui.viewmodel.ItemViewModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var mResultsList = ArrayList<AnimalResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val itemBinding: ItemInfoBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_info, parent, false)
        return MainViewHolder(itemBinding)
    }


    override fun getItemCount(): Int = mResultsList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mResultsList[position])
    }

    class MainViewHolder(itemBinding: ItemInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        var mItemBiding: ItemInfoBinding = itemBinding
        var picture: ImageView = itemBinding.root.findViewById(R.id.image_hall_pic)

        fun bind(animalResults: AnimalResults) {
            if (mItemBiding.itemViewModel == null) {
                mItemBiding.itemViewModel = ItemViewModel(animalResults)
            } else {
                mItemBiding.itemViewModel?.changeDataSet(animalResults)
            }
            // Load Picture use
            Glide.with(mItemBiding.root.context).load(animalResults.E_Pic_URL).into(picture)

            Log.d("govzoo", "MainAdapter-MainViewHolder: ${mItemBiding.itemViewModel?.getPicUrl()}")
        }
    }

    fun setData(animalResultsList: List<AnimalResults>) {
        mResultsList.clear()
        mResultsList.addAll(animalResultsList)
        notifyDataSetChanged()
    }
}