package com.aire.xmly.android.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ZhuPeipei on 2021/3/23 20:15.
 */
open class MutiTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mListData = ArrayList<Any>()
    private val mAdapters = HashMap<Int, MultiChildAdapter>()
    private val mViewTypes = ArrayList<Int>()
    private val mDataClassByViewType = ArrayList<Class<*>>()

    fun registerAdapterForData(dataClazz: Class<*>, adapter: MultiChildAdapter) {
        if (!mDataClassByViewType.contains(dataClazz)) {
            mDataClassByViewType.add(dataClazz)
            // 注意顺序
            mAdapters.put(mViewTypes.size, adapter)
            mViewTypes.add(mViewTypes.size)
        }
    }

    fun clearData() {
        mListData.clear()
        notifyDataSetChanged()
    }

    fun addListData(listData: List<Any>?) {
        listData?.let {
            mListData.addAll(listData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mAdapters[viewType]!!.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount() = mListData.size

    private fun getItem(pos: Int): Any? = mListData.getOrNull(pos)

    private fun getAdapter(pos: Int) = mAdapters[getItemViewType(pos)]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adapter = getAdapter(position)
        adapter?.onBindViewHolder(holder, position, getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        item?.let {
            val index = mDataClassByViewType.indexOf(it.javaClass)
            return mViewTypes.getOrNull(index) ?: 0
        }
        return 0
    }
}

abstract class MultiChildAdapter {

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(getContainerLayoutId(), parent, false)
        return createViewHolder(view)
    }

    abstract fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: Any?)

    abstract fun createViewHolder(itemView: View): RecyclerView.ViewHolder

    abstract fun getContainerLayoutId(): Int
}