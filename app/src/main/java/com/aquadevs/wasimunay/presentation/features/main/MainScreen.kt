package com.aquadevs.wasimunay.presentation.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.presentation.common.ButtonCustom
import com.aquadevs.wasimunay.presentation.common.CardCustom
import com.aquadevs.wasimunay.presentation.common.OutlinedTextFieldCustom
import com.aquadevs.wasimunay.presentation.common.TextCustom
import com.aquadevs.wasimunay.presentation.model.main.MainDto
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
fun MainScreen(goToDetail:() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        MyHeader()
        MyBody(goToDetail = goToDetail)
        MyFooter()
        MyDialog()
    }
}

@Composable
private fun MyHeader(mainViewModel: MainViewModel = hiltViewModel()) {
    var searchLocation by remember { mutableStateOf("") }

    CardCustom(
        modifier = Modifier.padding(20.dp),
        cornerRadius = 15,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            OutlinedTextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(R.string.searchLocationMain),
                value = searchLocation,
                onValue = { searchLocation = it }
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(mainViewModel.listName) { names ->
                    ButtonCustom(
                        textColor = Color.White,
                        backgroundColor = Color.Blue,
                        textButton = stringResource(names),
                        onClick = {},
                        shape = RoundedCornerShape(percent = 40),
                        modifier = Modifier.padding(horizontal = 5.dp),
                        fontSize = 12
                    )
                }
            }
        }
    }
}

@Composable
private fun MyBody(mainViewModel: MainViewModel = hiltViewModel(), goToDetail: () -> Unit) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        items(mainViewModel.listDepartment) {
            MyItem(mainViewModel, it) {
                goToDetail()
            }
        }
    }
}

@Composable
private fun MyItem(
    mainViewModel: MainViewModel,
    mainDto: MainDto,
    onClick: (MainDto) -> Unit
) {
    CardCustom(
        modifier = Modifier.padding(bottom = 10.dp),
        cornerRadius = 12,
        background = OnBackgroundDark
    ) {
        Column(
            modifier = Modifier.padding(vertical = 25.dp, horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(mainDto.img),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(percent = 15))
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextCustom(
                    text = mainDto.departmentName,
                    fontSize = 20,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                    color = Color.White,
                    maxLines = 1
                )
                ButtonCustom(
                    textButton = stringResource(mainViewModel.getText(mainDto.departmentState)),
                    backgroundColor = mainViewModel.getBackGround(mainDto.departmentState)
                        .copy(alpha = 0.6f),
                    textColor = mainViewModel.getBackGround(mainDto.departmentState),
                    onClick = {}
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextCustom(
                    text = "${mainDto.departmentNumber} ${
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
        }
    }
}

@Composable
private fun MyFooter(modifier: Modifier = Modifier) {

}

@Composable
private fun MyDialog(modifier: Modifier = Modifier) {

}