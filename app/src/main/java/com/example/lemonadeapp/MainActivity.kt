package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                Lemonade()
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {

    var step by remember {
        mutableStateOf(1)
    }

    var squeeze by remember {
        mutableStateOf(1)
    }
    var maxSqueeze by remember {
        mutableStateOf(1)
    }

    val imageResource = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val imageDescription = when(step) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    val textResource = when(step) {
        1 -> R.string.first_step
        2 -> R.string.second_step
        3 -> R.string.third_step
        else -> R.string.fourth_step
    }

    fun incrementStep() {
        if (step == 2 && squeeze == 1) {                // sets max squeezes
            maxSqueeze = (1..5).random();
            squeeze++;
        } else {
            if (step == 2 && squeeze >= maxSqueeze) {   // jumps to next step if squeezes meets max
                step = (step + 1) % 4;
                squeeze = 1;
            } else if (step != 2) {                     // normal next step behaviour and resets squeeze
                step = (step + 1) % 4;

            } else {                                    // increments squeeze
                squeeze++;
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {

        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(imageDescription),
            modifier = Modifier
                        .clickable { incrementStep() }
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(213,250,192))
                        .size(300.dp)
                        .padding(30.dp)
        )
        Spacer(modifier = Modifier.height((16.dp)))
        Text(
            text = stringResource(textResource),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeAppTheme {
        Lemonade()
    }
}