package com.aire.xmly.android.bus

import com.google.gson.annotations.SerializedName

/**
 * Created by ZhuPeipei on 2021/3/23 19:31.
 */
data class BusModel(val name: String, val id: Int)

data class BusStationModel(
    @SerializedName("@attributes") val attributes: Attribute,
    val distance: String,
    val stopdis: String,
    val terminal: String,
    val time: String
)

data class Attribute(val cod: String)
