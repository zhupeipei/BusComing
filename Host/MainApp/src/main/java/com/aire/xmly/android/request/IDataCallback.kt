package com.aire.xmly.android.request

import okhttp3.ResponseBody

/**
 * Created by ZhuPeipei on 2021/3/24 18:22.
 */
interface IDataCallback<Data> {
    fun onSuccess(data: Data?)
    fun onError(code: Int, msg: String?, data: ResponseBody?)
}

const val ERROR_CODE_LOCAL = -100