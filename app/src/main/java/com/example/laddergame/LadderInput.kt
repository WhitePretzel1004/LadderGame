package com.example.laddergame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.generateViewId
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.laddergame.databinding.ActivityMainBinding.inflate


class LadderInput: AppCompatActivity() {

    private lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ladder_input)

        val numItems: Int = intent.extras?.getInt("numItems") ?: 1


        setContentView(R.layout.ladder_input)

        mainLayout = findViewById(R.id.llInputContainer)
        mainLayout.id = generateViewId()
        mainLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        LayoutInflater.from(mainLayout.context).inflate(
            R.layout.ladder_input,
            mainLayout,
            false)

        for (i in 1..numItems) {

            var item = Item(this)

            val viewId: Int = View.generateViewId()
            item.id = viewId
            println("viewID b4: " + item.id)

            mainLayout.addView(item)

            println("mainLayout id: " + mainLayout.id)

//            println("findViewById<mainLayout>(id): " + findViewById<LinearLayout>(mainLayout.id))

//            LayoutInflater.from(item.context).inflate(
//                findViewById(mainLayout.id),
//                item,
//                true
//            )

//            println("item view paddingTop: " + findViewById<Item>(item.id).paddingTop)


            item.drawItem(viewId)

            println("item: " + item)

        }
    }
}