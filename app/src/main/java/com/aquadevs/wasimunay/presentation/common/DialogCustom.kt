package com.aquadevs.wasimunay.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun DialogCustom(
    modifier: Modifier,
    cornerRadius: Int = 15,
    background:Color = validateTheme().surface,
    verticalArrangement:Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onDismissRequest:() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        CardCustom(
            background = background,
            cornerRadius = cornerRadius
        ) {
            Column(
                modifier = modifier,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment
            ) {
                this.content()
            }
        }
    }
}