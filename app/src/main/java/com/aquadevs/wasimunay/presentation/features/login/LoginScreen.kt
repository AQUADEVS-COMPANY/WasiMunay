package com.aquadevs.wasimunay.presentation.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom

/***
 * Class: LoginScreen
 * From: com.aquadevs.wasimunay.presentation.features.login
 * Author: Frank Gutierrez
 * Date: 11/11/2024 19:12
 * Description:
 *
 ***/

@Composable
fun LoginScreen(goToMain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MyHeader()
        MyBody()
        MyFooter()
        MyDialog()
    }
}

@Composable
private fun MyHeader(modifier: Modifier = Modifier) {
    TextCustom(
        text = stringResource(R.string.logIn),
        fontSize = 25,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MyBody(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    OutlinedTextFieldCustom(
        value = email,
        onValue = { email = it },
        label = stringResource(R.string.email),
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 15,

    )

    Spacer(modifier = Modifier.height(6.dp))

    OutlinedTextFieldCustom(
        value = email,
        onValue = { email = it },
        label = stringResource(R.string.email),
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 15
    )
}

@Composable
private fun MyFooter(modifier: Modifier = Modifier) {

}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}