package com.aquadevs.wasimunay.presentation.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/***
 * Class: MainScreen
 * From: com.aquadevs.wasimunay.presentation.features.main
 * Author: Frank Gutierrez
 * Date: 11/11/2024 20:51
 * Description:
 *
 ***/

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        MyHeader()
        MyBody()
        MyFooter()
        MyDialog()
    }
}

@Composable
private fun MyHeader(modifier: Modifier = Modifier) {

}

@Composable
private fun MyBody(modifier: Modifier = Modifier) {

}

@Composable
private fun MyFooter(modifier: Modifier = Modifier) {

}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}