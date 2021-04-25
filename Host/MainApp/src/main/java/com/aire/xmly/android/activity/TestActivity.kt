package com.aire.xmly.android.activity

import android.os.Bundle
import com.aire.xmly.android.R
import com.aire.xmly.android.test.TestFragment
import com.aire.xmly.android.opensdk.BaseActivity

class TestActivity : BaseActivity() {
    companion object {
        const val TAG = "zimotag-main"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        if (findFragment(TestFragment::class.java) == null) {
            loadRootFragment(R.id.host_fragment_test_container,
                TestFragment()
            )
        }
    }
}
