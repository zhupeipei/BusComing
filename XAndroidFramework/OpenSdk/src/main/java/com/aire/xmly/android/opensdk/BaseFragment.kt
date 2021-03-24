package com.aire.xmly.android.opensdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by ZhuPeipei on 2021/3/23 16:30.
 */
abstract class BaseFragment : SupportFragment() {
    private var mContentView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mContentView = inflater.inflate(getContainerLayoutId(), container, false)
        return mContentView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUi()
        loadData()
    }

    fun <T : View> findViewById(viewId: Int): T {
        return mContentView!!.findViewById<T>(viewId)
    }

    abstract fun getContainerLayoutId(): Int

    abstract fun initUi()

    abstract fun loadData()
}
