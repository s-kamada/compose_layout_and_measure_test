package com.example.compose_layout_and_measure_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_layout_and_measure_test.ui.theme.Compose_layout_and_measure_testTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_layout_and_measure_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Screen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}


@Composable
fun Screen(modifier: Modifier = Modifier) {

    val bodyText = "あのイーハトーヴォのすきとおった風、夏でも底に冷たさをもつ青いそら、うつくしい森で飾られたモリーオ市、郊外のぎらぎらひかる草の波。またそのなかでいっしょんあったたくさんのひとたち、ファゼーロとロザーロ、羊飼のミーロや、顔の赤いこどもたち、地主のテーモ、山猫博士のボーガント・デストゥパーゴなど、いまこの暗い巨きな石の建物のなかで考えていると、みんなむかし風のなつかしい青い幻燈のように思われます。"
    val titleText = "ポラーノの広場"
    val authorText = "宮沢賢治"

    Column(modifier = modifier.padding(12.dp)) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = titleText)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = authorText)
        Spacer(modifier = Modifier.height(12.dp))
        LongText(
            modifier = Modifier
                .border(1.dp, color = Color.Gray)
                .padding(8.dp),
            text = bodyText
        )
    }
}

@Composable
fun LongText(modifier: Modifier = Modifier, text: String) {
    Text(modifier = modifier, text = text)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_layout_and_measure_testTheme {
        Screen(modifier = Modifier.fillMaxSize())
    }
}
