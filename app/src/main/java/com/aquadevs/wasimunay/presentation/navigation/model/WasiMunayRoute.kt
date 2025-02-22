package com.aquadevs.wasimunay.presentation.navigation.model

/***
 * Class: WasiMunayRoute
 * From: com.aquadevs.wasimunay.presentation.navigation.model
 * Author: Frank Gutierrez
 * Date: 11/11/2024 19:06
 * Description:
 *
 ***/

sealed class WasiMunayRoute(val route: String) {
    data object WelcomeScreenRoute : WasiMunayRoute(route = "welcomeScreenRoute")
    data object LoginScreenRoute : WasiMunayRoute(route = "loginScreenRoute")
    data object MainScreenRoute : WasiMunayRoute(route = "mainScreenRoute")
    data object DetailScreenRoute : WasiMunayRoute(route = "detailScreenRoute?paramStr={paramStr}"){
        fun paramStr(uid:String = "") = "detailScreenRoute?paramStr=$uid"
    }
}