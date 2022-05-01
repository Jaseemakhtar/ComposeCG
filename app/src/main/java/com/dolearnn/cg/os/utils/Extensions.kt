package com.dolearnn.cg.os.utils

import android.content.res.Resources
import android.util.DisplayMetrics

fun Float.dpToPx(): Float {
    val metrics = Resources.getSystem().displayMetrics
    return this * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}