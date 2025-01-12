package com.aquadevs.wasimunay.presentation.navigation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aquadevs.wasimunay.presentation.features.detail.DetailScreen
import com.aquadevs.wasimunay.presentation.features.login.LoginScreen
import com.aquadevs.wasimunay.presentation.features.main.MainScreen
import com.aquadevs.wasimunay.presentation.features.welcome.WelcomeScreen
import com.aquadevs.wasimunay.presentation.navigation.model.WasiMunayRoute

/***
 * Class: NavWasiMunay
 * From: com.aquadevs.wasimunay.presentation.navigation.component
 * Author: Frank Gutierrez
 * Date: 11/11/2024 19:08
 * Description:
 *
 ***/

@Composable
fun NavWasiMunay(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WasiMunayRoute.WelcomeScreenRoute.route
    ){
        composable(route = WasiMunayRoute.WelcomeScreenRoute.route){
            WelcomeScreen(
                goToMain = {
                    navController.navigate(WasiMunayRoute.MainScreenRoute.route){
                        popUpTo(route = WasiMunayRoute.WelcomeScreenRoute.route){
                            inclusive = true
                        }
                    }
                },
                goToDetail = {
                    navController.navigate(WasiMunayRoute.DetailScreenRoute.paramStr())
                }
            )
        }

        composable(route = WasiMunayRoute.LoginScreenRoute.route){
            LoginScreen(
                goToMain = {
                    navController.navigate(WasiMunayRoute.MainScreenRoute.route){
                        popUpTo(route = WasiMunayRoute.LoginScreenRoute.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = WasiMunayRoute.MainScreenRoute.route){
            MainScreen(
                goToDetail = {
                    navController.navigate(WasiMunayRoute.DetailScreenRoute.paramStr(it))
                },
                goToMain = {
                    navController.navigate(WasiMunayRoute.WelcomeScreenRoute.route){
                        popUpTo(route = WasiMunayRoute.MainScreenRoute.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = WasiMunayRoute.DetailScreenRoute.route,
            arguments = listOf(
                navArgument(name = "paramStr") {
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}