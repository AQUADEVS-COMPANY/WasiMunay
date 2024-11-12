package com.aquadevs.wasimunay.presentation.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun TextCustom(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 14,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = validateTheme().onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow =  TextOverflow.Clip,
    fontStyle: FontStyle? = null
) {
    Text(
        text = text,
        fontWeight = fontWeight,
        color = color,
        modifier = modifier,
        fontSize = fontSize.sp,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        fontStyle = fontStyle
    )
}
