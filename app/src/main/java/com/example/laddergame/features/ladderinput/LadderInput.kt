package com.example.laddergame.features.ladderinput

import android.os.Parcelable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.fragment.app.Fragment
import com.example.laddergame.core.fragments.DefaultComposeFragment
import com.example.laddergame.core.navigation.FragmentKey
import com.zhuinden.simplestack.Backstack
import kotlinx.parcelize.Parcelize

@Parcelize
data class LadderInputKey(
    val numItems: Int,
) : FragmentKey() {
    override fun instantiateFragment(): Fragment = LadderInputFragment()
}

@Parcelize
data class LadderConnection(
    val ladderIndex: Int,
    val rowIndex: Int,
) : Parcelable

private fun generateConnections(numItems: Int): List<LadderConnection> {
    val numbers = (0 until numItems).toList()
    val shuffledNumbers = numbers.shuffled()
    return (0 until (numItems-1)).mapIndexed { index, ladderIndex ->
        LadderConnection(ladderIndex, shuffledNumbers[ladderIndex])
    }
}

class LadderInputFragment : DefaultComposeFragment() {
    @Composable
    override fun FragmentComposable(backstack: Backstack) {
        val key = remember { getKey<LadderInputKey>() }

        var connections by rememberSaveable(key = "connections") {
            mutableStateOf(generateConnections(numItems = key.numItems))
        }

        ScreenComposable(
            numItems = key.numItems,
            onRandomizeClicked = {
                connections = generateConnections(key.numItems)
            },
            connections = connections,
        )
    }

    @Composable
    fun ScreenComposable(
        numItems: Int,
        onRandomizeClicked: () -> Unit,
        connections: List<LadderConnection>,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(16.dp),
                ) {
                    Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                        val size = this.size

                        for (i in 0 until numItems) {
                            drawLine(
                                color = Color.Black,
                                start = Offset(
                                    x = ((size.width / numItems) / 2) + (i * (size.width / numItems)),
                                    y = 0f
                                ),
                                end = Offset(
                                    x = ((size.width / numItems) / 2) + (i * (size.width / numItems)),
                                    y = size.height
                                ),
                            )
                        }

                        for (connection in connections) {
                            drawLine(
                                color = Color.Green,
                                start = Offset(
                                    x = ((size.width / numItems) / 2) + (connection.ladderIndex) * (size.width / numItems),
                                    y = ((size.height / numItems) / 2) + (connection.rowIndex * (size.height / numItems))
                                ),
                                end = Offset(
                                    x = ((size.width / numItems) / 2) + ((connection.ladderIndex + 1)) * (size.width / numItems),
                                    y = ((size.height / numItems) / 2) + (connection.rowIndex * (size.height / numItems))
                                ),
                            )
                        }
                    })
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
                            Text(color = Color.Black, text = "Randomize")
                        },
                        onClick = onRandomizeClicked,
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun Preview() {
        ScreenComposable(
            numItems = 3,
            onRandomizeClicked = {},
            connections = generateConnections(3),
        )
    }
}