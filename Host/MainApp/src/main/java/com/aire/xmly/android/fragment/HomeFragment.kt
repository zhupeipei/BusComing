package com.aire.xmly.android.fragment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aire.xmly.android.R
import com.aire.xmly.android.bus.*
import com.aire.xmly.android.common.BaseUtil
import com.aire.xmly.android.common.GlobalCoroutineExceptionHandler
import com.aire.xmly.android.common.LinearItemDecoration
import com.aire.xmly.android.common.MutiTypeAdapter
import com.aire.xmly.android.opensdk.BaseFragment
import com.aire.xmly.android.request.IDataCallback
import com.aire.xmly.android.request.getBusArriveInfo
import com.aire.xmly.android.request.getBusArriveInfoCor
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import java.io.InputStreamReader

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
        try {
            val reader1 = InputStreamReader(context?.resources?.openRawResource(R.raw.bus_989))
            val bus989StationArray = Gson().fromJson(reader1, Array<String>::class.java)

            val reader2 = InputStreamReader(context?.resources?.openRawResource(R.raw.bus_989))
            val busPudong1StationArray = Gson().fromJson(reader2, Array<String>::class.java)


            CoroutineScope(Dispatchers.Main).launch(GlobalCoroutineExceptionHandler()) {
                var netData: List<Array<BusStationModel>?>? = null
                try {
                    netData = loadBusInfo()
                } catch (e: Exception) {
                    Log.d("zimotag", "loadBusInfo error ${e.message}")
                }
                val data = ArrayList<Any>()
                BusStationEnum.values().forEachIndexed { index, busStationEnum ->
                    data.add(
                        BusModel(
                            "${busStationEnum.busName} - ${busStationEnum.stationName}",
                            busStationEnum.index
                        )
                    )
                    val busStationModel = netData?.getOrNull(index)?.getOrNull(0)
                    busStationModel?.let {
                        data.add(busStationModel)
                    }
                }
                mAdapter.addListData(data)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun loadBusInfo(): List<Array<BusStationModel>?> = withContext(Dispatchers.IO) {
        val requestList = mutableListOf<Deferred<Array<BusStationModel>?>>()
        BusStationEnum.values().forEach { station ->
            val params = HashMap<String, String>()
            params["stoptype"] = "0"
            params["stopid"] = "${station.index}"
            params["sid"] = station.sid
            val defer: Deferred<Array<BusStationModel>?> =
                async { getBusArriveInfoCor(params) }
            requestList.add(defer)
        }
        val result = mutableListOf<Array<BusStationModel>?>()
        requestList.forEach {
            result.add(
                try {
                    it.await()
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            )
            it.await()
        }
        result
    }

    private fun getArriveInfo(station: BusStationEnum) {
        val params = HashMap<String, String>()
        params["stoptype"] = "0"
        params["stopid"] = "${station.index}"
        params["sid"] = station.sid
        getBusArriveInfo(params, object : IDataCallback<Array<BusStationModel>?> {
            override fun onSuccess(data: Array<BusStationModel>?) {
                Log.d("zimotag", "$data")
            }

            override fun onError(code: Int, msg: String?, data: ResponseBody?) {
                Log.d("zimotag", "$data")
            }
        })
    }

    override fun getContainerLayoutId() = R.layout.host_fragment_home

}
