package com.aquadevs.wasimunay.presentation.features.notifications

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val keyCode = intent.getStringExtra("KEY_CODE")
        setContent {
            Box(
                modifier = Modifier.background(Color.Black)
            ) {
                when(keyCode){
                    "1000" -> RequestLocationPermissionsNotification()
                    "1001" -> RequestCameraPermissionsNotification()
                }
            }
        }
    }
}