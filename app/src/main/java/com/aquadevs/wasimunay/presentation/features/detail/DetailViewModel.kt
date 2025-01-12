package com.aquadevs.wasimunay.presentation.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.wasimunay.core.Validations.getStringToDouble
import com.aquadevs.wasimunay.core.Validations.getStringToInt
import com.aquadevs.wasimunay.domain.UseCase.apartment.GetApartmentNetworkUseCase
import com.aquadevs.wasimunay.domain.UseCase.apartment.SetApartmentNetworkUseCase
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val setApartmentNetworkUseCase: SetApartmentNetworkUseCase,
    private val getApartmentNetworkUseCase: GetApartmentNetworkUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val idApartment: String = savedStateHandle["paramStr"] ?: ""

    private val _nameApartment = MutableLiveData<String>()
    var nameApartment: LiveData<String> = _nameApartment

    private val _description = MutableLiveData<String>()
    var description: LiveData<String> = _description

    private val _monthlyPrice = MutableLiveData<String>()
    var monthlyPrice: LiveData<String> = _monthlyPrice

    private val _numberRooms = MutableLiveData<String>()
    var numberRooms: LiveData<String> = _numberRooms

    private val _numberSquareMeters = MutableLiveData<String>()
    var numberSquareMeters: LiveData<String> = _numberSquareMeters

    private val _numberBathrooms = MutableLiveData<String>()
    var numberBathrooms: LiveData<String> = _numberBathrooms

    private val _fullAddress = MutableLiveData<String>()
    var fullAddress: LiveData<String> = _fullAddress

    private val _linkApartment = MutableLiveData<String>()
    var linkApartment: LiveData<String> = _linkApartment

    private val _isRegisterScreen = MutableLiveData<Boolean>()
    var isRegisterScreen: LiveData<Boolean> = _isRegisterScreen

    private val _enableButtonRegister = MutableLiveData<Boolean>()
    var enableButtonRegister: LiveData<Boolean> = _enableButtonRegister

    private val _showDialogImage = MutableLiveData<Boolean>()
    var showDialogImage: LiveData<Boolean> = _showDialogImage

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    init {
        validateRegisterScreen()
    }

    private fun validateRegisterScreen() {
        _isRegisterScreen.value = idApartment.isEmpty()
        if (idApartment.isNotEmpty()) getDataApartment(idApartment)
    }

    fun changeInput(id: Int, str: String = "", bool: Boolean = false) {
        when (id) {
            1 -> _nameApartment.value = str
            2 -> _numberRooms.value = str
            3 -> _numberBathrooms.value = str
            4 -> _monthlyPrice.value = str
            5 -> _numberSquareMeters.value = str
            6 -> _description.value = str
            7 -> _fullAddress.value = str
            8 -> _linkApartment.value = str
            9 -> _showDialogImage.value = bool
        }

        enableButtonRegister()
    }

    fun onRegisterApartment(onResponse: () -> Unit) {
        val apartmentDto = ApartmentDto(
            apartmentName = _nameApartment.value ?: "",
            apartmentState = 1,
            roomNumber = getStringToInt(_numberRooms.value),
            bathroomNumber = getStringToInt(_numberBathrooms.value),
            squareMetersNumber = getStringToInt(_numberSquareMeters.value),
            departmentPrice = getStringToDouble(_monthlyPrice.value),
            description = _description.value ?: "",
            location = _fullAddress.value ?: "",
            linkApartment = _linkApartment.value ?: ""
        )

        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val rptApartment = setApartmentNetworkUseCase(apartmentDto)
                withContext(Dispatchers.Main) {
                    if (rptApartment) onResponse()
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    private fun enableButtonRegister() {
        _enableButtonRegister.value = !_nameApartment.value.isNullOrBlank() &&
                !_numberRooms.value.isNullOrBlank() &&
                !_numberBathrooms.value.isNullOrBlank() &&
                !_monthlyPrice.value.isNullOrBlank() &&
                !_numberSquareMeters.value.isNullOrBlank() &&
                !_description.value.isNullOrBlank() &&
                !_fullAddress.value.isNullOrBlank() &&
                !_linkApartment.value.isNullOrBlank()
    }

    private fun getDataApartment(idApartment: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apartmentDto = getApartmentNetworkUseCase(idApartment)
                withContext(Dispatchers.Main) {
                    apartmentDto?.let { i ->
                        _nameApartment.value = i.apartmentName
                        _numberRooms.value = i.roomNumber.toString()
                        _numberBathrooms.value = i.bathroomNumber.toString()
                        _monthlyPrice.value = i.departmentPrice.toString()
                        _numberSquareMeters.value = i.squareMetersNumber.toString()
                        _description.value = i.description
                        _fullAddress.value = i.location
                        _linkApartment.value = i.linkApartment
                    }

                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }
}