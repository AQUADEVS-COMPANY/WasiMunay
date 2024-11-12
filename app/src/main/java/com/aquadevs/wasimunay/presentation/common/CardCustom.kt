package com.aquadevs.wasimunay.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun CardCustom(
    modifier: Modifier = Modifier,
    widthBorder:Int = 0,
    cornerRadius: Int = 50,
    colorStatus: Color = Color.Transparent,
    background:Color = validateTheme().onBackground,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(percent = cornerRadius),
        colors = CardDefaults.cardColors(
            containerColor = background
        ),
        content = content,
        border = BorderStroke(width = widthBorder.dp, color = colorStatus)
    )
}