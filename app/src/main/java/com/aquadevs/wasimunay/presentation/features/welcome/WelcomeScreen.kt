package com.aquadevs.wasimunay.presentation.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
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
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedButtonCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.ui.theme.OnBackgroundDark

/***
 * Class: WelcomeScreen
 * From: com.aquadevs.wasimunay.presentation.features.welcome
 * Author: Frank Gutierrez
 * Date: 11/11/2024 19:02
 * Description:
 *
 ***/

@Composable
fun WelcomeScreen(goToLogIn: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.foto1), // Asegúrate de que el nombre coincide
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)), // Fondo semitransparente para mejorar la legibilidad
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyHeader()
            MyBody(goToLogIn = goToLogIn)
            MyFooter()
            MyDialog()
        }
    }
}
@Composable
private fun MyHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextCustom(
            text = stringResource(R.string.app_name),
            fontSize = 25,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextCustom(
            text = stringResource(R.string.subTitleLogin),
            fontSize = 20,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun MyBody(goToLogIn: () -> Unit) {
    CardCustom(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 10.dp),
        cornerRadius = 15,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp, vertical = 15.dp)
        ) {
            TextCustom(
                text = stringResource(R.string.quetion1Login),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(13.dp))

            ButtonCustom(
                textButton = stringResource(R.string.iamLandlord),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                backgroundColor = Color.Blue,
                textColor = Color.White,
                onClick = goToLogIn
            )

            Spacer(modifier = Modifier.height(13.dp))

            OutlinedButtonCustom(
                textButton = stringResource(R.string.iamTenant),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                textColor = Color.White,
                color = ButtonDefaults.outlinedButtonColors(

                ),
                onClick = goToLogIn
            )

            Spacer(modifier = Modifier.height(13.dp))

            TextCustom(
                text = stringResource(R.string.msgWelcomeScreen),
                fontSize = 14,
                color = Color(0XFF8E9194),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MyFooter(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextCustom(
            text = stringResource(R.string.quetionAccountWelcome),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        TextCustom(
            text = stringResource(R.string.logIn),
            color = Color.Blue,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}