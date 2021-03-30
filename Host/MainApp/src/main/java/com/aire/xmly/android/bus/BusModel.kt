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

enum class BusStationEnum(
    val busName: String,
    val stationName: String = "",
    val index: Int,
    val sid: String
) {
    BUS_989_GUTANG("989路", "顾唐路龙东大道", 14, "82944a2f25006fada079b02a4ecaebea"),
    BUS_PUDONG_1_GUTANG("浦东1路", "龙东大道顾唐路", 8, "de82841bae84af0e7df130cc51bb417d")
}
