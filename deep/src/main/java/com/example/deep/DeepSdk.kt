package com.example.deep

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.Keep

@Keep
class DeepSdk(
  private val context: Context,
  private val baseUrl: String,
) {
  fun startActivity() {
    Log.d("DEEP_SDK", baseUrl)

    val intent = Intent(context, DeepActivity::class.java)
    context.startActivity(intent)
  }

  @Keep
  class Builder(private val context: Context) {
    private var baseUrl: String = ""
    fun setBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }
    fun build(): DeepSdk = DeepSdk(
      context = context, baseUrl = baseUrl
    )
  }
}

@Keep
fun deepSdk(context: Context, block: DeepSdk.Builder.() -> DeepSdk.Builder): DeepSdk {
  return block(DeepSdk.Builder(context)).build()
}
