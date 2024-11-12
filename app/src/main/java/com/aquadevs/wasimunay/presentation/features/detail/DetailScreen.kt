package com.aquadevs.wasimunay.presentation.features.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Validations.validateDouble
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.IconButtonCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedButtonCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.presentation.features.main.MainViewModel
import com.aquadevs.wasimunay.presentation.model.main.MainDto
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
    goToMain: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MyHeader(goToMain = goToMain)
        MyBody()
        MyFooter()
        MyDialog()
    }
}

@Composable
private fun MyHeader(
    detailViewModel: DetailViewModel = hiltViewModel(),
    goToMain: () -> Unit
) {
    val mainDto by detailViewModel.mainDto.observeAsState(MainDto())

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Blue.copy(alpha = 0.6f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonCustom(
            icon = Icons.Filled.ArrowBack,
            iconSize = 30,
            onClick = goToMain,
            iconColor = Color.White
        )
        TextCustom(
            text = mainDto.departmentName,
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
        MyItemLocation(detailViewModel)
    }
}

@Composable
private fun MyItemDepartmentInformation(detailViewModel: DetailViewModel) {
    val announcementTitle by detailViewModel.announcementTitle.observeAsState(initial = "")
    val description by detailViewModel.description.observeAsState(initial = "")
    val monthlyPrice by detailViewModel.monthlyPrice.observeAsState(initial = "0")
    val mainDto by detailViewModel.mainDto.observeAsState(initial = MainDto())

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
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextFieldCustom(
                value = description,
                onValue = { detailViewModel.changeInput(id = 2, str = it) },
                label = stringResource(R.string.description),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextFieldCustom(
                    value = "${mainDto.departmentNumber}",
                    onValue = { },
                    label = stringResource(R.string.rooms),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = false
                )
                Spacer(modifier = Modifier.width(6.dp))
                OutlinedTextFieldCustom(
                    value = "${mainDto.bathroomNumber}",
                    onValue = { },
                    label = stringResource(R.string.bathroom),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    enabled = false
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedTextFieldCustom(
                value = monthlyPrice,
                onValue = { detailViewModel.changeInput(id = 3, str = it) },
                label = stringResource(R.string.description),
                keyBoardType = KeyboardType.Decimal,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MyItemLocation(detailViewModel: DetailViewModel) {
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

            OutlinedButtonCustom(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 8.dp)
                    .height(45.dp),
                textButton = stringResource(R.string.selectLocation),
                textColor = Color.White,
                onClick = {}
            )

            OutlinedTextFieldCustom(
                value = fullAddress,
                onValue = { detailViewModel.changeInput(id = 4, str = it) },
                label = stringResource(R.string.fullAddress),
                keyBoardType = KeyboardType.Decimal,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MyFooter(modifier: Modifier = Modifier) {

}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}