package com.aquadevs.wasimunay.presentation.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aquadevs.wasimunay.ui.theme.validateTheme

@Composable
fun OutlinedTextFieldCustom(
    value: String,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    modifierAll: Modifier? = null,
    cornerRadius: Int = 20,
    maxLine: Int = 1,
    maxLength : Int = 25,
    keyBoardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @DrawableRes trailingIcon: Int? = null,
    trailingIconIV: ImageVector? = null,
    onClickTrailingIcon: (() -> Unit)? = null,
    singleLine: Boolean = true,
    color: TextFieldColors = OutlinedTextFieldDefaults.colors(
        cursorColor = Color.White, //validateTheme().onPrimary,
        focusedBorderColor = Color.White, //validateTheme().onPrimary,
        focusedLabelColor = Color.White, //validateTheme().onPrimary,
        unfocusedBorderColor = Color.White, // validateTheme().onPrimary,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledBorderColor = Color.White.copy(0.7f), //validateTheme().onPrimary.copy(0.7f),
        unfocusedLabelColor = Color.White.copy(0.7f),
        disabledLabelColor = Color.White, //
        focusedTextColor = Color.White, //
        unfocusedTextColor = Color.White.copy(alpha = 0.7f), //
        focusedPlaceholderColor = Color.White, //
        unfocusedPlaceholderColor = Color.White.copy(alpha = 0.7f)
    ),
    onValue: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifierAll ?: modifier.height(60.dp),
        value = value,
        onValueChange = {
            if(it.length <= maxLength){
                onValue(it)
            }
        },
        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = Color.White,//validateTheme().onPrimary,
            fontSize = 14.sp
        ),
        label = {
            TextCustom(
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White, //
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        ),
        colors = color,
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius),
        maxLines = maxLine,
        visualTransformation = visualTransformation,
        trailingIcon = if (trailingIcon != null || trailingIconIV != null) {
            {
                IconButton(
                    onClick = {
                        onClickTrailingIcon?.let {
                            it()
                        }
                    }
                ) {
                    if (trailingIcon != null ){
                        Icon(
                            painter = painterResource(id = trailingIcon),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = validateTheme().onPrimary
                        )
                    }

                    if (trailingIconIV != null ){
                        Icon(
                            imageVector = trailingIconIV,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = validateTheme().onPrimary
                        )
                    }
                }
            }
        } else null,
        singleLine = singleLine
    )
}