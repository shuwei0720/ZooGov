package com.momo.zoogov.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.momo.zoogov.R
import com.momo.zoogov.databinding.FrgMainBinding
import com.momo.zoogov.ui.view.MainAdapter
import com.momo.zoogov.ui.viewmodel.MainViewModel
import java.util.*

class MainFragment : Fragment(), Observer {

    private lateinit var mainFragmentBinding: FrgMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("govzoo", "MainFragment-onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.reset()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d("govzoo", "MainFragment-onCreateView")

        // initDataBinding
        mainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.frg_main, container, false)
        mainViewModel = MainViewModel()
        mainFragmentBinding.mainViewModel = mainViewModel

        // initView
        mainFragmentBinding.rvFrgMain.adapter = MainAdapter()
        mainFragmentBinding.rvFrgMain.layoutManager = LinearLayoutManager(activity)

        return mainFragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("govzoo", "MainFragment-onActivityCreated")

        // setup Observer
        mainViewModel.addObserver(this)
        mainViewModel.fetchList()
    }

    override fun update(observable: Observable?, data: Any?) {

        Log.d("govzoo", "MainFragment-update")
        if (observable is MainViewModel) {
            Log.d("zoogov", "MainFragment-update: ${mainViewModel.getAnimalList().size}")

            var adapter: MainAdapter = mainFragmentBinding.rvFrgMain.adapter as MainAdapter
            adapter.setData(mainViewModel.getAnimalList())

        }
    }

}
