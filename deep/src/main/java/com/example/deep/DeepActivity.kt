package com.example.deep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.navigation.plusAssign
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

class DeepActivity : ComponentActivity() {

  @OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
  )
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val navController = rememberAnimatedNavController()
      val bottomSheetNavigator = rememberBottomSheetNavigator()
      navController.navigatorProvider += bottomSheetNavigator

      ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = RoundedCornerShape(16.dp),
      ) {
        DestinationsNavHost(
          navGraph = NavGraphs.root,
          navController = navController,
          engine = rememberAnimatedNavHostEngine()
        )
      }
    }
  }
}
