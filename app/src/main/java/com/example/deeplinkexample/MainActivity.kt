package com.example.deeplinkexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            Button(onClick = { deep.startActivity() }) {
              Text(text = "Navigate To Deep Activity")
            }
          }
        }
      }
    }
  }
}
