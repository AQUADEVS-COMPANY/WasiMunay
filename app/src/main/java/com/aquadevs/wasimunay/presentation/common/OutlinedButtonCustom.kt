package com.aquadevs.wasimunay.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun OutlinedButtonCustom(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int? = null,
    textAlign: TextAlign = TextAlign.Start,
    isIcon: Boolean = false,
    colorIcon: Color = validateTheme().onPrimary,
    sizeIcon: Int = 22,
    textSize: Int = 14,
    textColor: Color = validateTheme().onPrimary,
    enabled: Boolean = true,
    textButton: String,
    shape: Shape = ButtonDefaults.shape,
    color: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = color,
        shape = shape
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (imageRes != null) {
                if (isIcon) {
                    Icon(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        tint = colorIcon,
                        modifier = Modifier.size(sizeIcon.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        modifier = Modifier.size(sizeIcon.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = textButton,
                fontSize = textSize.sp,
                color = textColor,
                textAlign = textAlign
            )
        }
    }
}

@Composable
fun ButtonCustom(
    modifier: Modifier = Modifier,
    textButton: String,
    backgroundColor: Color = validateTheme().primary,
    enabled: Boolean = true,
    textColor: Color = validateTheme().primary,
    shape: Shape = ButtonDefaults.shape,
    fontSize: Int = 14,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = Color.White,
            disabledContainerColor = validateTheme().primary.copy(0.7f),
            disabledContentColor = Color.White.copy(0.7f)
        ),
        shape = shape
    ) {
        TextCustom(
            text = textButton,
            color = textColor,
            fontSize = fontSize
        )
    }
}