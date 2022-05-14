package com.example.laddergame

import android.content.Context


fun Context.spToPx(sp: Float): Float {
    return sp * resources.displayMetrics.scaledDensity
}

fun Context.pxToDp(px: Float): Float {
    return px / resources.displayMetrics.density
}

fun Context.dpToPx(dp: Float): Float {
    return dp * resources.displayMetrics.density
}