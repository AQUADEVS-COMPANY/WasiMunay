package com.aquadevs.wasimunay.presentation.features.notifications

import android.Manifest
import android.app.Activity
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Composable.AnimationLottie
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.CircularProgressCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/***
 * Class: RequestCameraPermissionsNotification
 * From: com.aquadevs.wasimunay.presentation.features.notifications
 * Author: Frank Gutierrez
 * Date: 17/01/2025 03:03
 * Description:
 *
 ***/

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestCameraPermissionsNotification(notificationViewModel: NotificationViewModel = hiltViewModel()) {
    val activity = LocalContext.current as Activity

    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            permissionsState.allPermissionsGranted -> {
                MyBody(
                    strBody = R.string.msgPermissionsGranted,
                )
                notificationViewModel.onBack(activity = activity)
            }

            permissionsState.shouldShowRationale -> {
                MyBody(
                    strBody = R.string.msgPermissionNeedCamara,
                    strButton = R.string.requestPermissions,
                    onClick = {
                        permissionsState.launchMultiplePermissionRequest()
                    }
                )
            }

            permissionsState.revokedPermissions.isNotEmpty() -> {
                MyBody(
                    strBody = R.string.msgPermissionNeedCamara,
                    strButton = R.string.requestPermissions,
                    onClick = {
                        permissionsState.launchMultiplePermissionRequest()
                    }
                )
            }

            else -> {
                MyBody(
                    strBody = R.string.msgPermissionDeneidGoToSettingsCamera,
                    strButton = R.string.openSettings,
                    onClick = {
                        notificationViewModel.openAppSettingsLocation(activity)
                    }
                )
            }
        }
    }
}

@Composable
private fun MyBody(
    @StringRes strBody: Int,
    @StringRes strButton: Int? = null,
    onClick: (() -> Unit)? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AnimationLottie(
            modifier = Modifier,
            size = 200,
            lottie = R.raw.lottie_camera
        )
        TextCustom(
            text = stringResource(strBody),
            fontSize = 16,
            modifier = Modifier.padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        if (strButton == null) CircularProgressCustom(modifier = Modifier.size(40.dp))
        else ButtonCustom(
            textButton = stringResource(strButton),
            onClick = {
                if (onClick != null) onClick()
            },
            textColor = Color.White
        )
    }
}