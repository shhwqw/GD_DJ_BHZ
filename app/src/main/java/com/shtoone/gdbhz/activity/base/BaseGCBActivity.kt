package com.shtoone.gdbhz.activity.base

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.shtoone.gdbhz.BaseApplication
import com.shtoone.gdbhz.event.EventData
import com.shtoone.gdbhz.utils.ConstantsUtils

/**
 * Created by liangfeng on 2017/11/8.
 */

abstract class BaseGCBActivity : BaseActivity() {
    var isRegistered = false
    var transaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        if (!isRegistered) {
            BaseApplication.bus.register(this)
            isRegistered = true
        }
        val manager = supportFragmentManager
        transaction = manager.beginTransaction()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        BaseApplication.bus.post(EventData(ConstantsUtils.REFRESH_ENGINEER_DEPARTMENT))
    }

    abstract fun setLayout(): Int
}
