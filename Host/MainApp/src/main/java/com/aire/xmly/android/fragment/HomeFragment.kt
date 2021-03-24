package com.aire.xmly.android.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aire.xmly.android.R
import com.aire.xmly.android.bus.*
import com.aire.xmly.android.common.BaseUtil
import com.aire.xmly.android.common.LinearItemDecoration
import com.aire.xmly.android.common.MutiTypeAdapter
import com.aire.xmly.android.opensdk.BaseFragment

/**
 * Created by ZhuPeipei on 2021/3/23 16:20.
 */
class HomeFragment private constructor() : BaseFragment() {
    private val mBusRv by lazy { findViewById<RecyclerView>(R.id.host_bus_rv) }
    private val mAdapter = MutiTypeAdapter()

    init {
        mAdapter.registerAdapterForData(BusModel::class.java, BusAdapter())
        mAdapter.registerAdapterForData(BusStationModel::class.java, BusStationAdapter())
    }

    companion object {
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun initUi() {
        mBusRv.adapter = mAdapter
        mBusRv.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mBusRv.addItemDecoration(
            LinearItemDecoration(
                BaseUtil.dp2px(activity, 20F),
                BaseUtil.dp2px(activity, 30F),
                LinearLayoutManager.VERTICAL
            )
        )
    }

    override fun loadData() {
        val data = ArrayList<Any>()
        data.add(BusModel("989路", 0))
        data.add(BusModel("浦东1路", 0))
        data.add(BusStationModel(Attribute("浦东1路"), "1232", "1234", "121", "13"))
        data.add(BusStationModel(Attribute("浦东2路"), "1232", "1234", "121", "13"))
        mAdapter.addListData(data)
    }

    override fun getContainerLayoutId() = R.layout.host_fragment_home

}
