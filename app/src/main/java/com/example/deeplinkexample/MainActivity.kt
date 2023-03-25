package com.example.deeplinkexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deep.deepSdk
import com.example.deeplinkexample.ui.theme.DeeplinkExampleTheme

class MainActivity : ComponentActivity() {

  private val deep = deepSdk(context = this) {
    setBaseUrl("mock")
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      DeeplinkExampleTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            Column {
              WaveView()
              Button(onClick = { deep.startActivity() }) {
                Text(text = "Navigate To Deep Activity")
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun WaveView() {
  BoxWithConstraints(
    modifier = Modifier
      .padding(8.dp)
      .clip(RoundedCornerShape(16.dp))
      .background(Color(0xFF21354f)),
  ) {
    Box(
      modifier = Modifier
        .drawBehind {
          drawPath(
            path = Path().apply {
              moveTo(0f, 0f)
              lineTo(constraints.maxWidth.toFloat() / 4, 0f)
              cubicTo(
                constraints.maxWidth.toFloat() / 4,
                0f,
                200f,
                constraints.maxHeight.toFloat(),
                constraints.maxWidth.toFloat() + 100f,
                constraints.maxHeight.toFloat()
              )
              lineTo(-constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat())
              close()
            },
            brush = Brush.horizontalGradient(
              0.0f to Color(0xB221354F),
              0.5f to Color(0xFF364960),
              1.0f to Color(0xFF364960),
            ),
          )
          drawPath(
            path = Path().apply {
              moveTo(0f, 0f)
              lineTo(constraints.maxWidth.toFloat() / 8, 0f)
              cubicTo(
                constraints.maxWidth.toFloat() / 8,
                0f,
                100f,
                constraints.maxHeight.toFloat(),
                constraints.maxWidth.toFloat() / 2 + 50f,
                constraints.maxHeight.toFloat()
              )
              lineTo(-constraints.maxWidth.toFloat(), constraints.maxHeight.toFloat())
              close()
            },
            Brush.horizontalGradient(
              0.0f to Color(0xB3364960),
              0.5f to Color(0xFF2F425A),
              1.0f to Color(0xFF2F425A),
            ),
          )
        }
    )

    Column(modifier = Modifier.padding(16.dp)) {
      Text(
        text = LOREM,
        textAlign = TextAlign.Justify,
        color = Color.White,
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp
      )

      Spacer(modifier = Modifier.height(70.dp))

      Row(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = "http://example.com",
          textAlign = TextAlign.Justify,
          color = Color.White,
          fontFamily = FontFamily.Cursive,
          fontSize = 24.sp
        )
      }
    }
  }
}

private const val LOREM = "Quisque velit nisi, pretium ut lacinia in, elementum id enim. " +
    "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; " +
    "Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula. Lorem ipsum " +
    "dolor sit amet, consectetur adipiscing elit. Donec rutrum congue leo eget malesuada. " +
    "Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. "

@Preview(showBackground = true, widthDp = 500)
@Composable
private fun WaveViewPreview() {
  WaveView()
}
