package com.momo.zoogov.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.momo.zoogov.R
import com.momo.zoogov.data.AnimalResults
import com.momo.zoogov.databinding.FrgContentBinding
import com.momo.zoogov.ui.view.ContentAdapter
import com.momo.zoogov.ui.viewmodel.ContentViewModel
import java.util.*

private const val ARG_TITLE = "title"
private const val ARG_OBJECT = "arg_object"
class ContentFragment : Fragment(), Observer {

    private lateinit var contentFragmentBinding: FrgContentBinding
    private lateinit var contentViewModel: ContentViewModel

    private var mTitle: String = ""
    private var mAnimalResults: AnimalResults? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString(ARG_TITLE)?.let {
            mTitle = it
        }
        arguments?.getSerializable(ARG_OBJECT).let {
            mAnimalResults = it as? AnimalResults
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        contentViewModel.reset()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // initDataBinding
        contentFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.frg_content, container, false)
        mAnimalResults?.let {
            contentViewModel = ContentViewModel(it)
            contentFragmentBinding.contentViewModel = contentViewModel
        }

        // initView
        contentFragmentBinding.rvFrgContent.adapter = ContentAdapter()
        contentFragmentBinding.rvFrgContent.layoutManager = LinearLayoutManager(activity)

        Glide.with(contentFragmentBinding.root).load(mAnimalResults?.E_Pic_URL).into(contentFragmentBinding.imagePictureDetail)

        return contentFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("govzoo", "ContentFragment-onActivityCreated")

        // setup Observer
        contentViewModel.addObserver(this)
        contentViewModel.fetchList()
    }

    companion object {
        @JvmStatic
        fun newInstance(
            title: String,
            animalResults: AnimalResults
        ) =
            ContentFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putSerializable(ARG_OBJECT, animalResults)
                }
            }
    }

    override fun update(observable: Observable?, data: Any?) {

        Log.d("govzoo", "ContentFragment-update")
        if (observable is ContentViewModel) {
            Log.d("zoogov", "ContentFragment-update: ${contentViewModel.getPlantsList().size}")

            var adapter: ContentAdapter = contentFragmentBinding.rvFrgContent.adapter as ContentAdapter
            adapter.setData(contentViewModel.getPlantsList())

        }
    }

}
