package com.aire.xmly.android.test

import android.view.View
import android.widget.TextView
import com.aire.xmly.android.R
import com.aire.xmly.android.opensdk.BaseFragment


/**
 * Created by ZhuPeipei on 2021/4/25 10:53.
 */
class TestFragment : BaseFragment() {

    override fun initUi() {
        // 网络请求测试
        testNet()
        // textview垂直居中demo
        testTextView()
    }

    private fun testTextView() {
        val v = findViewById<View>(R.id.host_test_textview_test_tv)
        v.setOnClickListener {
            start(TestTextViewFragment())
        }
    }

    private fun testNet() {
        val tv = findViewById<TextView>(R.id.host_test_net_test_tv)

        tv.setOnClickListener {
            NetTest().startTest()
        }
    }

    override fun loadData() {
    }

    override fun getContainerLayoutId() = R.layout.host_fragment_test
}