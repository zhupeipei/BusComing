package com.aire.xmly.android.common

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created by ZhuPeipei on 2021/3/23 21:33.
 */
class LinearItemDecoration(
    val mSpace: Int,
    val mMargin: Int,
    val mOrientation: Int = LinearLayoutManager.VERTICAL
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter!!.itemCount
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            if (itemCount == 1) {
                outRect.top = mMargin
                outRect.bottom = mMargin
            } else if (position == 0) {
                outRect.top = mMargin
                outRect.bottom = mSpace
            } else if (position == itemCount - 1) {
                outRect.bottom = mMargin
            } else {
                outRect.bottom = mSpace
            }
        } else {
            if (itemCount == 1) {
                outRect.left = mMargin
                outRect.right = mMargin
            } else if (position == 0) {
                outRect.left = mMargin
                outRect.right = mSpace
            } else if (position == itemCount - 1) {
                outRect.right = mMargin
            } else {
                outRect.right = mSpace
            }
        }
    }
}
