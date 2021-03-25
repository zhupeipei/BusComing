package com.aire.xmly.android.request

import com.aire.xmly.android.bus.BusStationModel
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.HeaderMap
import retrofit2.http.POST


/**
 * Created by ZhuPeipei on 2021/3/24 18:03.
 */
interface RequestInterface {
    @POST("public/bus/Getstop")
    @FormUrlEncoded
    fun getBusArriveInfo(
        @HeaderMap header: Map<String, String>,
        @FieldMap params: Map<String, String>
    ): Call<Array<BusStationModel>?>

    //// 协程
    @POST("public/bus/Getstop")
    @FormUrlEncoded
    suspend fun getBusArriveInfoCor(
        @HeaderMap header: Map<String, String>,
        @FieldMap params: Map<String, String>
    ): Array<BusStationModel>?
}