package com.example.laddergame

import android.content.res.Resources

class Util {

    fun pxToDp(px: Int): Int {
        return (px * Resources.getSystem().displayMetrics.density.toInt())
    }
    fun dpToWPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.widthPixels)
    }
    fun dpToHPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.heightPixels)
    }

}