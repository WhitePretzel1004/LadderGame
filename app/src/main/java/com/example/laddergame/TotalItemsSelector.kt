package com.example.laddergame

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class TotalItemsSelector : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var numberPicker: TextView
    private lateinit var incrementButton: AppCompatButton
    private lateinit var decrementButton: AppCompatButton
    private lateinit var startButton: AppCompatButton
    private var curNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.total_items_selector)

        initView()
    }

    private fun initView() {
        title = findViewById(R.id.tvTitle)

        numberPicker = findViewById(R.id.tvNumberPicker)
        curNumber = Integer.parseInt(numberPicker.text as String)

        incrementButton = findViewById(R.id.butIncrement)
        incrementButton.setOnClickListener {

            numberPickerWrap(numberPicker, incrementButton)
//            numberPicker.text = (curNumber++).toString()
        }
        decrementButton = findViewById(R.id.butDecrement)
        decrementButton.setOnClickListener {
            numberPickerWrap(numberPicker, decrementButton)
//            numberPicker.text = (curNumber--).toString()
        }

        startButton = findViewById(R.id.butStart)

        startButton.setOnClickListener {
            val nextPage = Intent(this, LadderInput::class.java).apply {
                this.putExtra("numItems", curNumber)
            }
            println("numITems: " + (nextPage.extras?.getInt("numItems") ?: 1))
            startActivity(nextPage)
        }
    }

    private fun numberPickerWrap(np: TextView, button: AppCompatButton) {

        val numberMin = 2
        val numberMax = 24

        if (button.text == "+") {
            curNumber++
        } else {
            curNumber--
        }

        if (curNumber < numberMin) {
            curNumber = numberMax
        } else if (curNumber > numberMax) {
            curNumber = numberMin
        }

        np.text = curNumber.toString()

    }

}