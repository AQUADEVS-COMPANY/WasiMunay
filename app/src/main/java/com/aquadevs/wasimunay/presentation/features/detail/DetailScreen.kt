package com.aquadevs.wasimunay.presentation.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Composable.DialogLoading
import com.aquadevs.wasimunay.core.Validations.validateDouble
import com.aquadevs.wasimunay.core.Validations.validateInt
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.DialogCustom
import com.aquadevs.wasimunay.presentation.common.IconButtonCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.aquadevs.wasimunay.ui.theme.OnBackgroundDark

/***
 * Class: DetalleScreen
 * From: com.aquadevs.wasimunay.presentation.features.detail
 * Author: Frank Gutierrez
 * Date: 12/11/2024 17:15
 * Description:
 *
 ***/

@Composable
fun DetailScreen(
    goBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        MyHeader(goBack = goBack)
        MyBody()
        MyFooter(goBack = goBack)
        MyDialog()
    }
}

@Composable
private fun MyHeader(
    detailViewModel: DetailViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val showInputNameApartment by detailViewModel.isRegisterScreen.observeAsState(false)
    val nameApartment by detailViewModel.nameApartment.observeAsState("")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue.copy(alpha = 0.6f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonCustom(
            icon = Icons.Filled.ArrowBack,
            iconSize = 30,
            onClick = goBack,
            iconColor = Color.White
        )
        TextCustom(
            text = if (showInputNameApartment) stringResource(R.string.newDepartment)
            else nameApartment,
            color = Color.White,
            fontSize = 20,
            maxLines = 1,
            overflow = TextOverflow.Clip,
            modifier = Modifier.padding(vertical = 15.dp)
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color(0XFF8E9194))
    )
}

@Composable
private fun MyBody(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyItemDepartmentInformation(detailViewModel)
        Spacer(modifier = Modifier.height(15.dp))
        MyLocation(detailViewModel)
        Spacer(modifier = Modifier.height(15.dp))
        MyApartment(detailViewModel)
    }
}

@Composable
private fun MyItemDepartmentInformation(detailViewModel: DetailViewModel) {
    val isRegister by detailViewModel.isRegisterScreen.observeAsState(false)
    val announcementTitle by detailViewModel.nameApartment.observeAsState(initial = "")
    val description by detailViewModel.description.observeAsState(initial = "")
    val numberRooms by detailViewModel.numberRooms.observeAsState(initial = "")
    val numberBathrooms by detailViewModel.numberBathrooms.observeAsState(initial = "")
    val numberSquareMeters by detailViewModel.numberSquareMeters.observeAsState(initial = "")
    val monthlyPrice by detailViewModel.monthlyPrice.observeAsState(initial = "")

    CardCustom(
        cornerRadius = 10,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCustom(
                text = stringResource(R.string.departmentInformation),
                fontSize = 22,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextFieldCustom(
                value = announcementTitle,
                onValue = { detailViewModel.changeInput(id = 1, str = it) },
                label = stringResource(R.string.announcementTitle),
                modifier = Modifier.fillMaxWidth(),
                enabled = isRegister
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
            ) {
                OutlinedTextFieldCustom(
                    value = numberRooms,
                    onValue = {
                        if (it.validateInt()) detailViewModel.changeInput(id = 2, str = it)
                    },
                    label = stringResource(R.string.rooms),
                    keyBoardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = isRegister
                )
                Spacer(modifier = Modifier.width(6.dp))
                OutlinedTextFieldCustom(
                    value = numberBathrooms,
                    onValue = {
                        if (it.validateInt()) detailViewModel.changeInput(id = 3, str = it)
                    },
                    label = stringResource(R.string.bathroom),
                    keyBoardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = isRegister
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextFieldCustom(
                    value = monthlyPrice,
                    onValue = {
                        if (it.validateDouble()) detailViewModel.changeInput(id = 4, str = it)
                    },
                    label = stringResource(R.string.price),
                    keyBoardType = KeyboardType.Decimal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = isRegister
                )
                Spacer(modifier = Modifier.width(6.dp))
                OutlinedTextFieldCustom(
                    value = numberSquareMeters,
                    onValue = {
                        if (it.validateInt()) detailViewModel.changeInput(id = 5, str = it)
                    },
                    label = stringResource(R.string.sqaureMeters),
                    keyBoardType = KeyboardType.Number,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = isRegister
                )
            }

            OutlinedTextFieldCustom(
                value = description,
                onValue = { detailViewModel.changeInput(id = 6, str = it) },
                label = stringResource(R.string.description),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                enabled = isRegister,
                singleLine = false,
                maxLine = 3
            )
        }
    }
}

@Composable
private fun MyLocation(detailViewModel: DetailViewModel) {
    val isRegister by detailViewModel.isRegisterScreen.observeAsState(false)
    val fullAddress by detailViewModel.fullAddress.observeAsState(initial = "")

    CardCustom(
        cornerRadius = 10,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextCustom(
                text = stringResource(R.string.location),
                fontSize = 22,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextFieldCustom(
                value = fullAddress,
                onValue = { detailViewModel.changeInput(id = 7, str = it) },
                label = stringResource(R.string.fullAddress),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                enabled = isRegister,
                maxLength = 100
            )
        }
    }
}

@Composable
private fun MyApartment(detailViewModel: DetailViewModel) {
    val isRegister by detailViewModel.isRegisterScreen.observeAsState(false)
    val linkApartment by detailViewModel.linkApartment.observeAsState(initial = "")

    if (isRegister) {
        CardCustom(
            cornerRadius = 10,
            background = OnBackgroundDark
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextCustom(
                    text = stringResource(R.string.enterLinkApartment),
                    fontSize = 22,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                OutlinedTextFieldCustom(
                    value = linkApartment,
                    onValue = { detailViewModel.changeInput(id = 8, str = it) },
                    label = stringResource(R.string.enterLink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    enabled = isRegister,
                    maxLength = 1000,
                    keyBoardType = KeyboardType.Text
                )

                ButtonCustom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(45.dp),
                    textButton = stringResource(R.string.verify),
                    enabled = linkApartment.isNotEmpty(),
                    textColor = Color.White
                ) {
                    detailViewModel.changeInput(id = 9, bool = true)
                }
            }
        }
    }
}

@Composable
private fun MyFooter(
    detailViewModel: DetailViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    val isRegister by detailViewModel.isRegisterScreen.observeAsState(false)
    val enableButtonRegister by detailViewModel.enableButtonRegister.observeAsState(initial = false)

    if (isRegister) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ButtonCustom(
                textButton = stringResource(R.string.register),
                fontSize = 18,
                textColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                enabled = enableButtonRegister
            ) {
                detailViewModel.onRegisterApartment {
                    goBack()
                }
            }
        }
    }
}

@Composable
private fun MyDialog(detailViewModel: DetailViewModel = hiltViewModel()) {
    val showDialogImage by detailViewModel.showDialogImage.observeAsState(false)
    val isLoading by detailViewModel.isLoading.observeAsState(false)

    if (showDialogImage) MyDialogImage(detailViewModel)
    if (isLoading) DialogLoading()
}

@Composable
private fun MyDialogImage(detailViewModel: DetailViewModel) {
    val link by detailViewModel.linkApartment.observeAsState("")
    DialogCustom(
        onDismissRequest = {
            detailViewModel.changeInput(id = 9)
        }
    ) {
        CardCustom(
            cornerRadius = 10,
            background = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(horizontal = 15.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(link)
                    .crossfade(true)
                    .listener(
                        onError = { request, throwable ->
                            detailViewModel.changeInput(id = 9)
                        }
                    )
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onError = {
                    detailViewModel.changeInput(id = 9)
                }
            )
        }
    }
}