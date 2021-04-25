package com.aire.xmly.android.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.aire.xmly.android.R
import com.aire.xmly.android.fragment.HomeFragment
import com.aire.xmly.android.opensdk.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setBackgroundDrawable(null)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.host_activity_main)

        if (findFragment(HomeFragment::class.java) == null) {
            loadRootFragment(
                R.id.host_fragment_container,
                HomeFragment.newInstance()
            ) // 加载根Fragment
        }

        findViewById<TextView>(R.id.host_test_tv).setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}