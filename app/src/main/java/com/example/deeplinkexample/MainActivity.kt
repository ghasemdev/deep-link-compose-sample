package com.example.deeplinkexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deep.deepSdk
import com.example.deeplinkexample.ui.theme.DeeplinkExampleTheme
import kotlin.math.abs

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
            Button(onClick = { deep.startActivity() }) {
              Text(text = "Navigate To Deep Activity")
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
      .background(Color(0xFF001769)),
  ) {
    val width = constraints.maxWidth
    val height = constraints.maxHeight

    val mediumColoredPoint1 = Offset(0f, 0f)
    val mediumColoredPoint2 = Offset(width * 0.1f, height * 0.35f)
    val mediumColoredPoint3 = Offset(width * 0.4f, height * 0.05f)
    val mediumColoredPoint4 = Offset(width * 0.75f, height * 0.7f)
    val mediumColoredPoint5 = Offset(width * 1.4f, -height.toFloat())

    val mediumColorPath = Path().apply {
      moveTo(mediumColoredPoint1.x, mediumColoredPoint1.y)
      standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
      standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
      standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
      standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
      lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
      lineTo(-100f, height.toFloat() + 100f)
      close()
    }

    Canvas(modifier = Modifier) {
      drawPath(
        path = mediumColorPath,
        color = Color(0xFF000000)
      )
    }

    Box(modifier = Modifier.padding(16.dp)) {
      Text(
        text = LOREM,
        textAlign = TextAlign.Justify,
        color = Color.White,
        fontFamily = FontFamily.Cursive,
        fontSize = 24.sp
      )
    }
  }
}

private fun Path.standardQuadFromTo(from: Offset, to: Offset) {
  quadraticBezierTo(
    from.x,
    from.y,
    abs(from.x + to.x) / 2f,
    abs(from.y + to.y) / 2f
  )
}

private const val LOREM = "Quisque velit nisi, pretium ut lacinia in, elementum id enim. " +
    "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; " +
    "Donec velit neque, auctor sit amet aliquam vel, ullamcorper sit amet ligula. Lorem ipsum " +
    "dolor sit amet, consectetur adipiscing elit. Donec rutrum congue leo eget malesuada. " +
    "Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. " +
    "Pellentesque in ipsum id orci porta dapibus. Vivamus magna justo, lacinia eget consectetur " +
    "sed, convallis at tellus. Donec rutrum congue leo eget malesuada. Vestibulum ante ipsum " +
    "primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec velit neque, " +
    "auctor sit amet aliquam vel, ullamcorper sit amet ligula. " +
    "Donec rutrum congue leo eget malesuada."

@Preview(showBackground = true)
@Composable
private fun WaveViewPreview() {
  WaveView()
}
