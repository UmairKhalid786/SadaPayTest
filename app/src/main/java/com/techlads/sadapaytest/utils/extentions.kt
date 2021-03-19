package com.techlads.sadapaytest.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.squareup.picasso.Picasso
import com.techlads.sadapaytest.R
import jp.wasabeef.picasso.transformations.CropCircleTransformation


/**
 *
 * Kotlin
 *
 * @author Umair Khalid (umair.khalid786@outlook.com)
 * @package com.techlads.swvl.utils
 */


fun View.visibility(visible: Boolean){
    this.visibility = if(visible) View.VISIBLE else View.GONE
}

fun ViewGroup.inflate(@LayoutRes layout: Int, attachToParent: Boolean = false) : View {
    return LayoutInflater.from(context).inflate(layout, this, attachToParent)
}

fun ImageView.loadImage(
    url: String,
    defaultImg: Int = R.drawable.circular_corners_bg) {

    Picasso.get().load(url).placeholder(defaultImg).transform(CropCircleTransformation()).error(defaultImg).into(this)
}
