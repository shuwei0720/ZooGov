package com.momo.zoogov.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.momo.zoogov.R
import com.momo.zoogov.data.PlantsResults
import com.momo.zoogov.databinding.PlantsInfoBinding
import com.momo.zoogov.ui.viewmodel.PlantsViewModel

//@BindingAdapter("imgUrl")
//fun loadImage(imageView: ImageView, url: String) {
//    Glide.with(imageView.context).load(url).into(imageView)
//}
class ContentAdapter : RecyclerView.Adapter<ContentAdapter.MainViewHolder>() {

    private var mResultsList = ArrayList<PlantsResults>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val itemBinding: PlantsInfoBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.plants_info, parent, false)
        return MainViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = mResultsList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(mResultsList[position])
    }

    class MainViewHolder(itemBinding: PlantsInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        var mItemBiding: PlantsInfoBinding = itemBinding
        var picture: ImageView = itemBinding.root.findViewById(R.id.image_hall_pic)

        fun bind(plantsResults: PlantsResults) {
            if (mItemBiding.plantsViewModel == null) {
                mItemBiding.plantsViewModel = PlantsViewModel(plantsResults)
            } else {
                mItemBiding.plantsViewModel?.changeDataSet(plantsResults)
            }
            // Load Picture use
            if (plantsResults.F_Pic01_URL?.isNotEmpty() == true) {
                Glide.with(mItemBiding.root.context).load(plantsResults.F_Pic01_URL).into(picture)
            }

            Log.d("govzoo", "MainAdapter-MainViewHolder: ${mItemBiding.plantsViewModel?.getPicUrl()}")
        }
    }

    fun setData(plantsResultsList: List<PlantsResults>) {
        mResultsList.clear()
        mResultsList.addAll(plantsResultsList)
        notifyDataSetChanged()
    }
}