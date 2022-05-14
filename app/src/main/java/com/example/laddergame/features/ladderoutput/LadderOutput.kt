package com.example.laddergame.features.ladderoutput

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.laddergame.core.fragments.DefaultComposeFragment
import com.example.laddergame.core.navigation.FragmentKey
import com.zhuinden.simplestack.Backstack
import kotlinx.parcelize.Parcelize

@Parcelize
data class LadderOutputKey(
    private val noArgsPlaceholder: String = ""
): FragmentKey() {
    override fun instantiateFragment(): Fragment = LadderOutputFragment()
}

class LadderOutputFragment: DefaultComposeFragment() {
    @Composable
    override fun FragmentComposable(backstack: Backstack) {
        ScreenComposable(
            onRestartClicked = {
                // TODO
            },
        )
    }

    @Composable
    fun ScreenComposable(
        onRestartClicked: () -> Unit,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1.0f)) {
                    // TODO
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 48.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                        ),
                        content = {
                            Text(color = Color.Black, text = "Restart")
                        },
                        onClick = onRestartClicked,
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun Preview() {
        ScreenComposable(
            onRestartClicked = {},
        )
    }
}