package com.aquadevs.wasimunay.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun LinearProgressCustom(
    modifier: Modifier = Modifier
) {
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        color = validateTheme().primary,
        trackColor = validateTheme().primary.copy(alpha = 0.2f),
        strokeCap = StrokeCap.Butt
    )
}