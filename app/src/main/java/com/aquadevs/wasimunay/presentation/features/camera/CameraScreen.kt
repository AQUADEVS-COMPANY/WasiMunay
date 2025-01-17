package com.aquadevs.wasimunay.presentation.features.camera

import android.app.Activity
import android.view.ViewGroup
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Composable.DialogLoading
import com.aquadevs.wasimunay.presentation.common.IconButtonCustom

/***
 * Class: CameraScreen
 * From: com.aquadevs.wasimunay.presentation.features.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 01:53
 * Description:
 *
 ***/

@Composable
fun CameraScreen(cameraViewModel: CameraViewModel = hiltViewModel()) {
    val contextActivity = LocalContext.current as Activity

    val controller = remember {
        LifecycleCameraController(contextActivity.applicationContext).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE
            )
        }
    }

    MyDialog()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        CameraPreview(
            controller = controller,
            modifier = Modifier.fillMaxSize()
        )
        IconButtonCustom(
            iconDR = R.drawable.photo_camera_512px,
            iconSize = 45,
            iconColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(vertical = 12.dp)
        ) {
            cameraViewModel.takePhoto(
                context = contextActivity,
                controller = controller,
                onPhotoTaken = { bitmap ->
                    cameraViewModel.onTakePhoto(
                        activity = contextActivity,
                        bitmap = bitmap
                    )
                }
            )
        }
    }
}


@Composable
private fun MyDialog(cameraViewModel: CameraViewModel = hiltViewModel()) {
    val isLoading by cameraViewModel.isLoading.observeAsState(false)

    if (isLoading) DialogLoading()
}

@Composable
private fun CameraPreview(controller: LifecycleCameraController, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = {
                PreviewView(it).apply {
                    this.controller = controller
                    this.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    controller.bindToLifecycle(lifecycleOwner)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}