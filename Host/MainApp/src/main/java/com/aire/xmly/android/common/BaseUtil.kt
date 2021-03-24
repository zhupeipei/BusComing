package com.aire.xmly.android.common

import android.content.Context

/**
 * Created by ZhuPeipei on 2021/3/23 21:45.
 */
class BaseUtil {
    companion object {
        fun dp2px(context: Context?, dipValue: Float): Int {
            if (context == null) return (dipValue * 1.5).toInt()
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }

        fun dp2pxReturnFloat(
            context: Context?,
            dipValue: Float
        ): Float {
            if (context == null) return dipValue * 1.5f
            val scale = context.resources.displayMetrics.density
            return dipValue * scale + 0.5f
        }

        fun sp2px(context: Context?, dipValue: Float): Int {
            if (context == null) return (dipValue * 1.5).toInt()
            val scale = context.resources.displayMetrics.scaledDensity
            return (dipValue * scale + 0.5f).toInt()
        }

        fun px2dip(context: Context?, pxValue: Float): Int {
            if (context == null) return (pxValue * 1.5).toInt()
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale = context.resources.displayMetrics.scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }

        fun pt2px(context: Context, ptValue: Float): Int {
            val dpi = context.resources.displayMetrics.densityDpi
            return (ptValue * dpi / 72).toInt()
        }
    }
}