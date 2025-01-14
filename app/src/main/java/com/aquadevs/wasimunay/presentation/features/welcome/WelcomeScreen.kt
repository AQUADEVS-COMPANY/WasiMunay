package com.aquadevs.wasimunay.presentation.features.welcome

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Composable.DialogLoading
import com.aquadevs.wasimunay.core.Constants.ID_TOKEN_GOOGLE
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedButtonCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.ui.theme.OnBackgroundDark
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

/***
 * Class: WelcomeScreen
 * From: com.aquadevs.wasimunay.presentation.features.welcome
 * Author: Frank Gutierrez
 * Date: 11/11/2024 19:02
 * Description:
 *
 ***/

@Composable
fun WelcomeScreen(goToMain: () -> Unit, goToDetail: () -> Unit) {
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
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyHeader()
            MyBody(goToMain = goToMain, goToDetail = goToDetail)
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
private fun MyBody(
    welcomeViewModel: WelcomeViewModel = hiltViewModel(),
    goToMain: () -> Unit,
    goToDetail: () -> Unit
) {
    val isLogInGoogle by welcomeViewModel.isLogInGoogle.observeAsState(true)
    val nameUser by welcomeViewModel.namePerson.observeAsState("")
    CardCustom(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 10.dp),
        cornerRadius = 15,
        background = OnBackgroundDark
    ) {
        AnimatedVisibility(isLogInGoogle) {
            MyBodyGoogle(welcomeViewModel)
        }

        AnimatedVisibility(!isLogInGoogle) {
            MyBodyGeneral(nameUser, goToMain, goToDetail)
        }
    }
}

@Composable
private fun MyBodyGoogle(welcomeViewModel: WelcomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp, vertical = 15.dp)
    ) {
        TextCustom(
            text = stringResource(R.string.msgUserContinueGoogle),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(13.dp))
        MyButtonGoogle(welcomeViewModel)
    }
}

@Composable
fun MyBodyGeneral(nameUser: String, goToMain: () -> Unit, goToDetail: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 15.dp)
    ) {
        TextCustom(
            text = stringResource(R.string.hello) + "! $nameUser, " + stringResource(R.string.quetion1Login),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18,
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
            onClick = goToDetail
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
            onClick = goToMain
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

@Composable
fun MyButtonGoogle(welcomeViewModel: WelcomeViewModel) {
    val context = LocalContext.current as Activity
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        try {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            welcomeViewModel.signInWithGoogle(credential = credential)
        } catch (e: Exception) {
            Log.e("FRANK18GF", "${e.message}")
        }
    }

    OutlinedButtonCustom(
        textButton = stringResource(R.string.startWithGoogle),
        modifier = Modifier.fillMaxWidth().height(45.dp)
    ) {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(ID_TOKEN_GOOGLE)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, options)
        launcher.launch(googleSignInClient.signInIntent)
    }
}

@Composable
private fun MyDialog(welcomeViewModel: WelcomeViewModel = hiltViewModel()) {
    val isLoading by welcomeViewModel.isLoading.observeAsState(false)

    if (isLoading) DialogLoading()
}