package com.aquadevs.wasimunay.presentation.common

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.aquadevs.wasimunay.ui.theme.validateTheme

/**
 * Class: SwitchCustom
 * From: com.aquadevs.alertvita.app
 * Author: Frank Gutierrez
 * Date: 9/10/2024 15:41
 * Description:
 *
 */

@Composable
fun SwitchCustom(
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Switch(
        checked = checked,
        onCheckedChange = onChecked,
        colors = SwitchDefaults.colors(
            checkedTrackColor = validateTheme().primary,
            checkedThumbColor = Color.White
        ),
        modifier = modifier
    )
}