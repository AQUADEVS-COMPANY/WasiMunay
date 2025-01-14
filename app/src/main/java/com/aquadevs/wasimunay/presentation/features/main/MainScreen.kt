package com.aquadevs.wasimunay.presentation.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.core.Composable.DialogLoading
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.aquadevs.wasimunay.ui.theme.OnBackgroundDark

/***
 * Class: MainScreen
 * From: com.aquadevs.wasimunay.presentation.features.main
 * Author: Frank Gutierrez
 * Date: 11/11/2024 20:51
 * Description:
 *
 ***/

@Composable
fun MainScreen(
    goToDetail: (id: String) -> Unit,
    goToMain: () -> Unit
) {
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
                .background(color = Color.Transparent).padding(20.dp)
        ) {
            MyHeader()
            MyBody(modifier = Modifier.weight(1f), goToDetail = goToDetail)
            MyFooter(goToMain)
            MyDialog()
        }
    }

}

@Composable
private fun MyHeader(mainViewModel: MainViewModel = hiltViewModel()) {
    var searchLocation by remember { mutableStateOf("") }

    CardCustom(
        cornerRadius = 15,
        background = OnBackgroundDark
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            OutlinedTextFieldCustom(
                modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
                label = stringResource(R.string.searchApartmentMain),
                value = searchLocation,
                onValue = {
                    searchLocation = it
                    mainViewModel.filterSearchApartment(it)
                }
            )
        }
    }
}

@Composable
private fun MyBody(
    modifier: Modifier,
    mainViewModel: MainViewModel = hiltViewModel(),
    goToDetail: (id: String) -> Unit
) {
    LazyColumn(modifier = modifier.padding(vertical = 6.dp)) {
        items(mainViewModel.listApartment) {
            MyItem(mainViewModel, it) { apartment ->
                goToDetail(apartment.id)
            }
        }
    }
}

@Composable
private fun MyItem(
    mainViewModel: MainViewModel,
    mainDto: ApartmentDto,
    onClick: (ApartmentDto) -> Unit
) {
    CardCustom(
        modifier = Modifier.padding(vertical = 6.dp),
        cornerRadius = 12,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 20.dp)
        ) {
            TextCustom(
                text = mainDto.apartmentName,
                fontSize = 22,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(mainDto.linkApartment)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(percent = 15))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextCustom(
                    text = "${mainDto.roomNumber} ${
                        stringResource(R.string.rooms).toLowerCase(
                            locale = Locale.current
                        )
                    }",
                    color = Color(0XFF8E9194),
                    fontSize = 14
                )
                TextCustom(
                    text = "•",
                    color = Color(0XFF8E9194),
                    fontSize = 15,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                TextCustom(
                    text = "${mainDto.bathroomNumber} ${
                        stringResource(R.string.bathroom).toLowerCase(
                            locale = Locale.current
                        )
                    }",
                    color = Color(0XFF8E9194),
                    fontSize = 14
                )
                TextCustom(
                    text = "•",
                    color = Color(0XFF8E9194),
                    fontSize = 15,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                BasicText(
                    text = AnnotatedString.Builder().apply {
                        append("${mainDto.squareMetersNumber} m")
                        withStyle(
                            style = SpanStyle(
                                fontSize = 10.sp, // Adjust font size for superscript
                                baselineShift = BaselineShift.Superscript,
                                color = Color(0XFF8E9194)
                            )
                        ) {
                            append("2")
                        }
                    }.toAnnotatedString(),
                    style = TextStyle(fontSize = 14.sp, color = Color(0XFF8E9194)),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextCustom(
                    text = "$${mainDto.departmentPrice} / ${stringResource(R.string.month)}",
                    fontSize = 16,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                ButtonCustom(
                    textButton = stringResource(R.string.seeDetails),
                    shape = RoundedCornerShape(20),
                    backgroundColor = Color.White,
                    onClick = {
                        onClick(mainDto)
                    }
                )
            }

            ButtonCustom(
                textButton = stringResource(mainViewModel.getText(mainDto.apartmentState)),
                backgroundColor = mainViewModel.getBackGround(mainDto.apartmentState)
                    .copy(alpha = 0.6f),
                textColor = Color.White,
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
            )
        }
    }
}

@Composable
private fun MyFooter(
    goToMain: () -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    Row(modifier = Modifier) {
        ButtonCustom(
            textButton = stringResource(R.string.logOut),
            backgroundColor = Color.DarkGray,
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
        ) {
            mainViewModel.logOutSession(context) {
                goToMain()
            }
        }
    }
}

@Composable
private fun MyDialog(mainViewModel: MainViewModel = hiltViewModel()) {
    val isLoading by mainViewModel.isLoading.observeAsState(false)

    if (isLoading) DialogLoading()
}