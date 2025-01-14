package com.aquadevs.wasimunay.presentation.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedButtonCustom
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.img_1),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MyHeader()
            MyBody()
            MyFooter(goToMain = goToMain)
            MyDialog()
        }
    }

}

@Composable
private fun MyHeader(modifier: Modifier = Modifier) {
    TextCustom(
        text = stringResource(R.string.app_name),
        fontSize = 25,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun MyBody(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        OutlinedTextFieldCustom(
            value = email,
            onValue = { email = it },
            label = stringResource(R.string.email),
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 25,

            )

        Spacer(modifier = Modifier.height(6.dp))

        OutlinedTextFieldCustom(
            value = password,
            onValue = { password = it },
            label = stringResource(R.string.password),
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 25
        )
    }
}

@Composable
private fun MyFooter(
    goToMain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 15.dp)
    ) {
        ButtonCustom(
            textButton = stringResource(R.string.logIn),
            textColor = Color.White,
            backgroundColor = Color.Blue,
            shape = RoundedCornerShape(percent = 25),
            onClick = goToMain,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(6.dp))
        TextCustom(
            text = stringResource(id = R.string.haveYouForgottenYourPassword),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .weight(1f)
                    .background(Color(0XFF8E9194))
            )
            TextCustom(
                text = stringResource(id = R.string.orSingUpWith),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp),
                textAlign = TextAlign.Center
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .weight(1f)
                    .background(Color(0XFF8E9194))
            )
        }

        OutlinedButtonCustom(
            textButton = stringResource(R.string.continueWithGoogle),
            shape = RoundedCornerShape(percent = 25),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            textColor = Color.White,
            onClick = goToMain
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButtonCustom(
            textButton = stringResource(R.string.continueWithApple),
            shape = RoundedCornerShape(percent = 25),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            textColor = Color.White,
            onClick = goToMain
        )
    }
}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}