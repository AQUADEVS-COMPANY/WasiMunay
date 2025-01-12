package com.aquadevs.wasimunay.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.presentation.common.CircularProgressCustom
import com.aquadevs.wasimunay.presentation.common.DialogCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom

object Composable {
    @Composable
    fun DialogLoading(text:String = "") {
        DialogCustom(
            modifier = Modifier,
            onDismissRequest = { /*TODO*/ },
            background = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressCustom(Modifier.size(50.dp))
                if (text.isNotEmpty()){
                    TextCustom(
                        text = text,
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }
    }
}