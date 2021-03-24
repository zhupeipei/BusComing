package com.aire.xmly.android.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ZhuPeipei on 2021/3/23 17:33.
 */
abstract class BaseRecyclerAdapter<VH : RecyclerView.ViewHolder, Data> :
    RecyclerView.Adapter<VH>() {
    private val mListData = ArrayList<Data>()

    fun addListData(listData: List<Data>?) {
        listData?.let {
            mListData.addAll(listData)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView =
            LayoutInflater.from(parent.context).inflate(getContainerLayoutId(), parent, false)
        return createViewHolder(itemView)
    }

    abstract fun createViewHolder(itemView: View): VH

    abstract fun getContainerLayoutId(): Int

    override fun getItemCount() = mListData.size
}
