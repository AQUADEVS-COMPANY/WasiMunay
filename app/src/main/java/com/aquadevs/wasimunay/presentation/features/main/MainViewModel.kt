package com.aquadevs.wasimunay.presentation.features.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.presentation.model.main.MainDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * Class: MainViewModel
 * From: com.aquadevs.wasimunay.presentation.features.main
 * Author: Frank Gutierrez
 * Date: 12/11/2024 15:22
 * Description:
 *
 ***/

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel() {
    val listName = mutableListOf<Int>(
        R.string.price,
        R.string.rooms,
        R.string.type
    )

    val listDepartment = mutableListOf(
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 1",
            departmentState = 1,
            departmentNumber = 2,
            bathroomNumber = 3,
            squareMetersNumber = 200,
            departmentPrice = 800
        ),
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 2",
            departmentState = 1,
            departmentNumber = 3,
            bathroomNumber = 1,
            squareMetersNumber = 200,
            departmentPrice = 1000
        ),
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 3",
            departmentState = 1,
            departmentNumber = 1,
            bathroomNumber = 1,
            squareMetersNumber = 200,
            departmentPrice = 600
        ),
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 4",
            departmentState = 2,
            departmentNumber = 5,
            bathroomNumber = 2,
            squareMetersNumber = 200,
            departmentPrice = 1500
        ),
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 5",
            departmentState = 1,
            departmentNumber = 2,
            bathroomNumber = 3,
            squareMetersNumber = 200,
            departmentPrice = 900
        )
    )

    fun getText(state: Int): Int {
        return when (state){
            1 -> R.string.avialable
            else -> R.string.exhausted
        }
    }

    fun getBackGround(state: Int): Color {
        return when (state){
            1 -> Color.Green
            else -> Color.Red
        }
    }
}