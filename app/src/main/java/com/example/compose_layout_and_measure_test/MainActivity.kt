package com.example.compose_layout_and_measure_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontLoader
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
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
        ExpandableText(
            modifier = Modifier
                .border(1.dp, color = Color.Gray)
                .padding(8.dp),
            text = bodyText
        )
    }
}

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String
) {

    var isExpanded by remember { mutableStateOf(false) }
    val maxLineCount = 3
    val lineHeight = 1.55.em

    val showMoreText = "もっと見る"
    val showLessText = "閉じる"
    val ellipsisSymbol = "..."

    BoxWithConstraints(
        modifier = modifier
    ) {
        val showMoreOrLessSpanStyle = SpanStyle(
            color = Color.Black,
            fontSize = 12.sp,
            textDecoration = TextDecoration.Underline
        )
        val bodyTextSpanStyle = SpanStyle(
            color = Color.Gray,
            fontSize = 12.sp
        )
        // TODO: 仮で適当に作ったもの。必要に応じてそれっぽいstyleに変更する
        val tmpTextStyle = TextStyle(
            color = Color.Black,
            fontSize = 12.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight.Normal
        )
        val paragraph = Paragraph(
            paragraphIntrinsics = ParagraphIntrinsics(
                text = text,
                style = tmpTextStyle,
                density = LocalDensity.current,
                resourceLoader = LocalFontLoader.current
            ),
            width = with(LocalDensity.current) { maxWidth.toPx() }
        )

        val clickable: Boolean
        val displayText: AnnotatedString
        if (paragraph.lineCount <= maxLineCount) {
            clickable = false
            displayText = buildAnnotatedString { withStyle(bodyTextSpanStyle){ append(text) } }
        } else {
            clickable = true
            displayText = if (isExpanded) {
                buildAnnotatedString {
                    withStyle(bodyTextSpanStyle) { append(text) }
                    withStyle(showMoreOrLessSpanStyle) { append(showLessText) }
                }
            } else {
                val body = text
                    .substring(startIndex = 0, endIndex = paragraph.getLineEnd(maxLineCount - 1, true))
                    .dropLast(showMoreText.length + ellipsisSymbol.length)
                    .let { "$it$ellipsisSymbol" }
                buildAnnotatedString {
                    withStyle(bodyTextSpanStyle) { append(body) }
                    withStyle(showMoreOrLessSpanStyle) { append(showMoreText) }
                }
            }
        }

        SelectionContainer(
            modifier = Modifier
                .clickable(enabled = clickable) {
                    isExpanded = !isExpanded
                }
                .animateContentSize()
        ) {
            Text(
                modifier = modifier,
                text = displayText,
                maxLines = if (isExpanded) Int.MAX_VALUE else maxLineCount,
                lineHeight = lineHeight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_layout_and_measure_testTheme {
        Screen(modifier = Modifier.fillMaxSize())
    }
}
