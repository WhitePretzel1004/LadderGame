package com.example.laddergame

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.core.view.updateLayoutParams

class Item(context: Context): LinearLayout(context) {

    private val util: Util = Util()

    init {

        this.setBackgroundColor(Color.GREEN)
        this.orientation = VERTICAL
        this.layoutParams = LayoutParams(util.pxToDp(200), MATCH_PARENT)
//        this.layoutParams = MarginLayoutParams(500, 500)
//        this.layoutParams = MarginLayoutParams()
        println("aaaa: " + this.layout(5, 5, 5, 5).toString())
        this.setPadding(util.pxToDp(10), util.pxToDp(10), util.pxToDp(10), util.pxToDp(10))

        println("Item init")
    }

    fun drawItem(viewId: Int) {

        println("drawItem: " + findViewById<LinearLayout>(viewId))

        val parentView = findViewById<LinearLayout>(viewId)

        val etTopInput: EditText = TopInputEditText(context, parentView)
//        val topSpacer: View = TopSpacer(context)
        val connectorLine = ConnectorLine(context, viewId)
//        val botSpacer: View = BotSpacer(context)
        val etBotInput: EditText = BotInputEditText(context, viewId)

        println("etBotInput: " + etBotInput.toString())

        this.addView(etTopInput)
//        this.addView(topSpacer)
        this.addView(connectorLine)
//        this.addView(botSpacer)
        this.addView(etBotInput)
        println("Item init")
    }

    class TopInputEditText(context: Context, parentView: View): AppCompatEditText(context) {

        private val util: Util = Util()

        init {
            this.setBackgroundColor(Color.BLUE)
            val background: Drawable = GradientDrawable()
            this.background = background
            (this.background as GradientDrawable).setStroke(2, Color.BLACK)
//            println("util.dpToHPx(1): " + util.dpToWPx(1))
            this.layoutParams = LayoutParams(MATCH_PARENT, util.pxToDp(1), 1F)
            this.gravity = Gravity.CENTER
        }
    }

    class TopSpacer(context: Context): View(context) {

        init {
            val spacer = Space(context)
            spacer.layoutParams = LayoutParams(0, 1, 1F)
            this.layoutParams = spacer.layoutParams
        }
    }

    class ConnectorLine(context: Context, viewId: Int): View(context) {

        private val util: Util = Util()

        private val paint: Paint = Paint()
        private var connectorLine: View = View(context)

        init {
            paint.color = Color.BLACK
            paint.strokeWidth = 5F
            connectorLine.setBackgroundColor(Color.WHITE)
            connectorLine.layoutParams = LayoutParams(MATCH_PARENT, util.pxToDp(1), 1F)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
//            canvas?.drawLine(util.dpToWPx(500).toFloat(), 500F, util.dpToWPx(500).toFloat(), 500F, paint)
//            canvas?.drawLine(20F, 0F, 0F, 20F, paint)
            canvas?.drawLine(util.pxToDp(90).toFloat(), util.pxToDp(10).toFloat(), util.pxToDp(90).toFloat(), MATCH_PARENT.toFloat() - util.pxToDp(10).toFloat(), paint)
        }
    }

    class BotSpacer(context: Context): View(context) {

        init {
            val spacer = Space(context)
            spacer.layoutParams = LayoutParams(0, 1, 1F)
            this.layoutParams = spacer.layoutParams
        }
    }

    class BotInputEditText(context: Context, viewId: Int): AppCompatEditText(context) {

        private val util: Util = Util()

        init {
            this.setBackgroundColor(Color.BLUE)
            val background: Drawable = GradientDrawable()
            this.background = background
            (this.background as GradientDrawable).setStroke(2, Color.BLACK)
            this.layoutParams = LayoutParams(MATCH_PARENT, util.pxToDp(1), 1F)
            this.gravity = Gravity.CENTER
        }

    }

}



