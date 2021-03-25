package com.aire.xmly.android.request

import com.aire.xmly.android.bus.BusStationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by ZhuPeipei on 2021/3/24 18:03.
 */
class CommonRequestM {
}

const val BUS_DOMAIN = "https://shanghaicity.openservice.kankanews.com/"
const val COMMON_COOKIE =
    "acw_tc=76b20f7216164679247261893e417024c634545421f857840015a4e5a1131f; souid=wKgBHGBZV9Rt4k2mkiDmAg==; _ga=GA1.2.1204245446.1616467925; iwt_uuid=01d8509f-46c9-4bfa-8746-3b0248a090a9; last_search_records=eyJpdiI6Im04cW90M1dCNWxsVVc0OW9cL2tYb1wvQT09IiwidmFsdWUiOiIyYzVVQ3pyYjRQOVQySFZCVEdDTElSc2FyVnR6b0lpNWNIak9pSzU2RmUzOGlucFwvTmRHVElhMXBNajNTbG5HSCIsIm1hYyI6IjgwZjJkNTNjYjA2NDYzYzZjYWIyZmI5ZWU1MmYxYmU4YzA5ODFhMzg3NDQyZjU2YmRiMmYxZTg4ZjY5NTZlZTkifQ%3D%3D; XSRF-TOKEN=eyJpdiI6IkhrZkdBb2t6ZHdFSmZ3cGpJeXV0XC9RPT0iLCJ2YWx1ZSI6ImdKaXFXUWk1TGhwYmxrWlFhaVE4M3F6SVlXNlVjQnFiTXFYbTFQN2hrTlQ1ZlFmeGI0bjVmeTZRaWtKTllXQ0ZsXC9ZSktoSDNcL2ljQ1Z4Q29qWm82T3c9PSIsIm1hYyI6ImUwYTVkMmIxOWU0NDRjNWE1MWE1NTU2NzI5MTkyOGE3OGJlYzMzZWUyNWVhOGJlZjg3OWFiMTc3MmExYmFiNGMifQ%3D%3D; _session=eyJpdiI6IjVBT3NsZFRlQ2ZiZE10aFlvTG9kRGc9PSIsInZhbHVlIjoiTnE4ZjZweWtUM0dLbmxcL2lOZ1RxT0hlMUdKcTZuRzhRU0twZnIyMURJY2ZwM2JSUmdIeGdsUHRzWGVBaWs4RFFzS3JPZVJGcFwvNkR4UzVLWk5TVTdnUT09IiwibWFjIjoiOWU3MjhkNmQ1YjJjYjkyMTc2MDczY2I3MjFmN2MyZWIyNWZjMjljY2Y5ZmQ5OTc5OTU3YmY1MjkwMzEyMGE5MiJ9; XSRF-TOKEN=eyJpdiI6InphUEdmRytjdnVHRjJQaURNeEJNSUE9PSIsInZhbHVlIjoiVER3dEc5MlwvT3ViZFNscDNldlZLcGJpb0g5Vzl3cjBsTkkxVDVmVjhQK3g0QkRHVHBaa1VqMmxaUHQ1dU9qVGdlanF4YmN3clVoNThSTlwvUzNvQ2owdz09IiwibWFjIjoiOWYzOGUzMzQ3MDg1MWRmZWZiYTlkZjIxMTIzZDM3Njk4M2E0ZTVhYWIzYTQ4MGZjNDk5NWU2ZTFlYjY1MGM1MyJ9; _session=eyJpdiI6Ik5JZHBxa3FVYm9tTUFITHRhQUJKcXc9PSIsInZhbHVlIjoiTkVsT1hWRWpnUlwvejBpOTJuZ2owWFJWQ3Rxc09CaGtBU3JWSEFtdU9adnhPTld6UUpXcmh3bTNjaFNyRWw1S1ZuYkxxVDhzdG5mbFBDNzVHZ0VYN2FBPT0iLCJtYWMiOiJlZGUyYjNmZTg1NzI4ZmYzYTUyMGJhNjE0ZTlkYmVjMzhjMGZmNzQ2MDAwOWU4NmFkN2M3ZDA1NTgwZmY2OTk5In0%3D"

val BUS_RETROFIT =
    Retrofit.Builder().baseUrl(BUS_DOMAIN).addConverterFactory(GsonConverterFactory.create())
        .build()

val BUS_REQUEST = BUS_RETROFIT.create(RequestInterface::class.java)

fun <Data> commonCallback(callback: IDataCallback<Data>): Callback<Data?> =
    object : Callback<Data?> {
        override fun onFailure(call: Call<Data?>, t: Throwable) {
            callback.onError(ERROR_CODE_LOCAL, t.message, null)
        }

        override fun onResponse(
            call: Call<Data?>,
            response: Response<Data?>
        ) {
            if (response.isSuccessful) {
                callback.onSuccess(response.body())
            } else {
                callback.onError(response.code(), response.message(), response.errorBody())
            }
        }
    }

fun getBusArriveInfo(
    params: Map<String, String>,
    callback: IDataCallback<Array<BusStationModel>?>
) {
    val header = HashMap<String, String>()
    header["cookie"] = COMMON_COOKIE
    header["authority"] = "shanghaicity.openservice.kankanews.com"
    val call: Call<Array<BusStationModel>?> = BUS_REQUEST.getBusArriveInfo(header, params)
    call.enqueue(commonCallback(callback))
}

// 协程
suspend fun getBusArriveInfoCor(params: Map<String, String>): Array<BusStationModel>? {
    val header = HashMap<String, String>()
    header["cookie"] = COMMON_COOKIE
    header["authority"] = "shanghaicity.openservice.kankanews.com"
    return BUS_REQUEST.getBusArriveInfoCor(header, params)
}