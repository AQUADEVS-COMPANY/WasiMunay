package com.aquadevs.wasimunay.presentation.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.presentation.model.main.MainDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/***
 * Class: DetailViewModel
 * From: com.aquadevs.wasimunay.presentation.features.detail
 * Author: Frank Gutierrez
 * Date: 12/11/2024 17:39
 * Description:
 *
 ***/

@HiltViewModel
class DetailViewModel @Inject constructor(

) : ViewModel() {
    private val _announcementTitle = MutableLiveData<String>()
    var announcementTitle: LiveData<String> = _announcementTitle

    private val _description = MutableLiveData<String>()
    var description: LiveData<String> = _description

    private val _monthlyPrice = MutableLiveData<String>()
    var monthlyPrice: LiveData<String> = _monthlyPrice

    private val _fullAddress = MutableLiveData<String>()
    var fullAddress: LiveData<String> = _fullAddress

    private val _mainDto = MutableLiveData<MainDto>(
        MainDto(
            img = R.drawable.background_house,
            departmentName = "Departamento N° 1",
            departmentState = 1,
            departmentNumber = 2,
            bathroomNumber = 3,
            squareMetersNumber = 200,
            departmentPrice = 800
        )
    )
    var mainDto: LiveData<MainDto> = _mainDto

    fun changeInput(id: Int, str: String = "", double: Double = 0.0) {
        when (id) {
            1 -> _announcementTitle.value = str
            2 -> _description.value = str
            3 -> _monthlyPrice.value = str
            4 -> _fullAddress.value = str
        }

    }
}