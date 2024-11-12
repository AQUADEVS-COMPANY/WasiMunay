package com.aquadevs.wasimunay.presentation.model.main

import androidx.annotation.DrawableRes
import com.aquadevs.wasimunay.R

data class MainDto(
    @DrawableRes val img: Int = R.drawable.background_house,
    val departmentName: String = "",
    val departmentState: Int = 0,
    val departmentNumber: Int = 0,
    val bathroomNumber: Int = 0,
    val squareMetersNumber: Int = 0,
    val departmentPrice: Int = 0
)
