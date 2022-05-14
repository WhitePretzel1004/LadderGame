package com.example.laddergame.features.totalitemsselector

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import com.example.laddergame.core.fragments.DefaultComposeFragment
import com.example.laddergame.core.navigation.FragmentKey
import com.example.laddergame.features.ladderinput.LadderInputKey
import com.zhuinden.simplestack.Backstack
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalItemsSelectorKey(
    private val noArgsPlaceholder: String = ""
) : FragmentKey() {
    override fun instantiateFragment(): Fragment = TotalItemsSelectorFragment()
}

class TotalItemsSelectorFragment : DefaultComposeFragment() {
    companion object {
        const val MIN_VALUE = 2
        const val MAX_VALUE = 24
    }

    @Composable
    override fun FragmentComposable(backstack: Backstack) {
        var selectedCount by rememberSaveable(key = "selectedCount") { mutableStateOf(2) }

        ScreenComposable(
            selectedCount = selectedCount,
            onMinusClicked = {
                selectedCount = selectedCount - 1
                if (selectedCount < MIN_VALUE) {
                    selectedCount = MAX_VALUE
                }
            },
            onPlusClicked = {
                selectedCount = selectedCount + 1
                if (selectedCount > MAX_VALUE) {
                    selectedCount = MIN_VALUE
                }
            },
            onStartClicked = {
                backstack.goTo(LadderInputKey(
                    numItems = selectedCount,
                ))
            }
        )
    }

    @Composable
    fun ScreenComposable(
        selectedCount: Int,
        onMinusClicked: () -> Unit,
        onPlusClicked: () -> Unit,
        onStartClicked: () -> Unit,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 32.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                    ),
                    onClick = onMinusClicked,
                    content = {
                        Text(fontSize = 24.sp, text = "-", color = Color.White)
                    }
                )

                Column(
                    modifier = Modifier
                        .weight(1.0f)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        fontSize = 36.sp,
                        text = "Ladder Game",
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        fontSize = 36.sp,
                        text = "${selectedCount}",
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                        ),
                        content = {
                            Text(color = Color.Black, text = "Start")
                        },
                        onClick = onStartClicked,
                    )
                }

                Button(
                    modifier = Modifier.size(48.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                    ),
                    onClick = onPlusClicked,
                    content = {
                        Text(fontSize = 24.sp, text = "+", color = Color.White)
                    }
                )
            }
        }
    }

    @Preview
    @Composable
    fun Preview() {
        ScreenComposable(
            selectedCount = 2,
            onMinusClicked = {},
            onPlusClicked = {},
            onStartClicked = {},
        )
    }
}