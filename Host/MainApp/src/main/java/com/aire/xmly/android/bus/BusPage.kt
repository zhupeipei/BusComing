package com.aire.xmly.android.bus

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aire.xmly.android.R
import com.aire.xmly.android.common.BaseRecyclerAdapter
import com.aire.xmly.android.common.MultiChildAdapter
import com.aire.xmly.android.common.TimeUtil

/**
 * Created by ZhuPeipei on 2021/3/23 17:31.
 */
class BusAdapter : MultiChildAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: Any?) {
        Log.d("zimotag", "onBindViewHolder")
        if (holder is BusViewHolder && item is BusModel) {
            holder.groupTv.text = item.name
        }
    }

    override fun createViewHolder(itemView: View) = BusViewHolder(itemView)

    override fun getContainerLayoutId() = R.layout.host_item_bus_group_view

}

class BusViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val groupTv by lazy { itemView.findViewById<TextView>(R.id.host_bus_group_tv) }
}

class BusStationAdapter : MultiChildAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, item: Any?) {
        if (holder is BusStationViewHolder && item is BusStationModel) {
            holder.childTv.text =
                "车次${item.attributes.cod} 还有${item.stopdis}站 ${TimeUtil.getTimeDurStr(item.time.toInt())} 距离${item.distance}m"
        }
    }

    override fun createViewHolder(itemView: View) = BusStationViewHolder(itemView)

    override fun getContainerLayoutId() = R.layout.host_item_bus_child_view

}

class BusStationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val childTv by lazy { itemView.findViewById<TextView>(R.id.host_bus_child_tv) }
}