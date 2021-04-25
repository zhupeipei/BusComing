package com.aire.xmly.android.test

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.ReplacementSpan
import android.widget.TextView
import com.aire.xmly.android.R
import com.aire.xmly.android.common.BaseUtil.Companion.sp2px
import com.aire.xmly.android.opensdk.BaseFragment


/**
 * Created by ZhuPeipei on 2021/4/25 11:01.
 */
class TestTextViewFragment : BaseFragment() {

    override fun initUi() {
        var tv = findViewById<TextView>(R.id.host_test_my_show_tv)
        val spanStr1 = SpannableString("后面的文字居中显示")
        spanStr1.setSpan(
            ForegroundColorSpan(Color.parseColor("#0000ff")),
            0,
            5,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        val fontSizePx1 = sp2px(context, 12F) as Int
        spanStr1.setSpan(
            VerticalCenterSpan(fontSizePx1.toFloat()),
            5,
            spanStr1.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        tv.setText(spanStr1)
    }

    override fun loadData() {
    }

    override fun getContainerLayoutId() = R.layout.host_fragment_textview_test
}

/**
 * 使TextView中不同大小字体垂直居中
 */
class VerticalCenterSpan(//px
    private val fontSizePx: Float
) : ReplacementSpan() {

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        var text = text
        text = text.subSequence(start, end)
        val p: Paint = getCustomTextPaint(paint)
        return p.measureText(text.toString()).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        var text = text
        text = text.subSequence(start, end)
        val p: Paint = getCustomTextPaint(paint)
        val fm: Paint.FontMetricsInt = p.getFontMetricsInt()
        // 此处重新计算y坐标，使字体居中
        canvas.drawText(
            text.toString(),
            x,
            (y - ((y + fm.descent + y + fm.ascent) / 2 - (bottom + top) / 2)).toFloat(),
            p
        )
    }

    private fun getCustomTextPaint(srcPaint: Paint): TextPaint {
        val paint = TextPaint(srcPaint)
        paint.textSize = fontSizePx //设定字体大小, sp转换为px
        return paint
    }

}