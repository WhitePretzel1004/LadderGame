package com.example.laddergame.application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.laddergame.R
import com.example.laddergame.features.totalitemsselector.TotalItemsSelectorKey
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.SimpleStateChanger
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.navigator.Navigator
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider

class MainActivity : AppCompatActivity(), SimpleStateChanger.NavigationHandler {
    private lateinit var fragmentStateChanger: DefaultFragmentStateChanger

    private val backPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (!Navigator.onBackPressed(this@MainActivity)) {
                this.remove() // this is the only safe way to invoke onBackPressed while cancelling the execution of this callback
                onBackPressed() // this is the only safe way to invoke onBackPressed while cancelling the execution of this callback
                this@MainActivity.onBackPressedDispatcher.addCallback(this) // this is the only safe way to invoke onBackPressed while cancelling the execution of this callback
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onBackPressedDispatcher.addCallback(backPressedCallback) // this is required for `onBackPressedDispatcher` to work correctly

        fragmentStateChanger = DefaultFragmentStateChanger(supportFragmentManager, R.id.container)

        Navigator.configure()
            .setScopedServices(DefaultServiceProvider())
            .setStateChanger(SimpleStateChanger(this))
            .install(this, findViewById(R.id.container), History.of(TotalItemsSelectorKey()))
    }

    @Suppress("RedundantModalityModifier")
    override final fun onBackPressed() { // you cannot use `onBackPressed()` if you use `OnBackPressedDispatcher`
        super.onBackPressed()
    }

    override fun onNavigationEvent(stateChange: StateChange) {
        fragmentStateChanger.handleStateChange(stateChange)
    }
}