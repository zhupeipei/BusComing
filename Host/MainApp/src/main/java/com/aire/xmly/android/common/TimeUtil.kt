package com.aire.xmly.android.common

/**
 * Created by ZhuPeipei on 2021/3/25 16:55.
 */
class TimeUtil {
    companion object {
        fun getTimeDurStr(l: Int): String {
            if (l <= 0) {
                return ""
            }
            val month = l / (24 * 3600 * 30)
            val day = l / (24 * 3600)
            val hour = l / 3600
            val minute = l / 60
            val second = l % 60
            return with(StringBuilder()) {
                if (month > 0) {
                    append("${month}月")
                }
                if (day > 0) {
                    append("${day}天")
                }
                if (hour > 0) {
                    append("${hour}时")
                }
                if (minute > 0) {
                    append("${minute}分")
                }
                if (second > 0) {
                    append("${second}秒")
                }
                toString()
            }
        }
    }
}