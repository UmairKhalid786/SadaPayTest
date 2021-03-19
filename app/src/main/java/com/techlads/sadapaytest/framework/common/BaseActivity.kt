package com.techlads.sadapaytest.framework.common

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState)
        setContentView(layout)
//        setSupportActionBar(getToolbar())
        initStuff()
        listeners()
    }

    abstract fun initStuff()
    abstract fun listeners()
    abstract fun getToolbar(): Toolbar?
}