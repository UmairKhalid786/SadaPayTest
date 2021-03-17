package com.techlads.sadapaytest.framework.common

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.sadapaytest.framework.common
 */

abstract class BaseActivity(@LayoutRes val layout : Int) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
        setSupportActionBar(getToolbar())
        init()
        listeners()
    }

    abstract fun init()
    abstract fun listeners()
    abstract fun getToolbar(): Toolbar
}