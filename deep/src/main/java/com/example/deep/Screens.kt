package com.example.deep

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.example.deep.destinations.SecondScreensDestination
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DestinationStyle

@RootNavGraph(start = true)
@Composable
@Destination
fun FirstScreen(navigator: DestinationsNavigator) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Button(onClick = { navigator.navigate(SecondScreensDestination) }) {
      Text("Go to the SecondScreens")
    }
  }
}

@Composable
@Destination(
  deepLinks = [
    DeepLink(uriPattern = WEB)
  ],
  style = DestinationStyle.BottomSheet::class
)
fun SecondScreens() {
  val uriHandler = LocalUriHandler.current

  Column(modifier = Modifier.height(300.dp)) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Button(onClick = { uriHandler.openUri(WEB) }) {
        Text("Go to the Web")
      }
    }
  }
}

private const val WEB = "https://stackoverflow.com/questions/66801838/how-do-i-programmatically" +
    "-open-an-external-url-on-a-button-click-with-jetpack-co"
