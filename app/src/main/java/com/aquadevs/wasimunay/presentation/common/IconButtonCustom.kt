package com.aquadevs.wasimunay.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun IconButtonCustom(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    @DrawableRes iconDR: Int? = null,
    iconColor: Color = validateTheme().onPrimary,
    iconSize: Int = 14,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (icon != null){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(iconSize.dp)
            )
        }

        if (iconDR != null){
            Icon(
                painter = painterResource(id = iconDR),
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(iconSize.dp)
            )
        }

    }
}
@Composable
fun IconCustom(
    icon: ImageVector,
    iconColor: Color = validateTheme().onPrimary,
    iconSize: Int = 14
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = iconColor,
        modifier = Modifier.size(iconSize.dp)
    )
}