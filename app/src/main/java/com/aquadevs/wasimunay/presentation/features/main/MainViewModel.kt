package com.aquadevs.wasimunay.presentation.features.main

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.wasimunay.R
import com.aquadevs.wasimunay.domain.UseCase.apartment.GetListApartmentNetworkUseCase
import com.aquadevs.wasimunay.domain.UseCase.welcome.LogOutUseCase
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    private val getListApartmentNetworkUseCase: GetListApartmentNetworkUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    val listName = mutableListOf<Int>(
        R.string.price,
        R.string.rooms,
        R.string.type
    )

    private var listOriginalApartment = listOf<ApartmentDto>()

    private val _listApartment = mutableStateListOf<ApartmentDto>()
    val listApartment: List<ApartmentDto> = _listApartment

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getApartment(context: Context) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                listOriginalApartment = listOf()
                listOriginalApartment = getListApartmentNetworkUseCase(context)
                withContext(Dispatchers.Main) {
                    _listApartment.clear()
                    _isLoading.value = false
                    _listApartment.addAll(listOriginalApartment)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    fun logOutSession(context: Context, onResponse:() -> Unit){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val stateOut = logOutUseCase(context)
                withContext(Dispatchers.Main){
                    if (stateOut) onResponse()
                    _isLoading.value = false
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    _isLoading.value = false
                }
            }
        }
    }

    fun filterSearchApartment(str:String){
        _listApartment.clear()
        if (str.isEmpty()) _listApartment.addAll(listOriginalApartment)
        else _listApartment.addAll(
            listOriginalApartment.filter {
                it.apartmentName.uppercase().contains(str.uppercase())
            }
        )
    }

    fun getText(state: Int): Int {
        return when (state) {
            1 -> R.string.avialable
            else -> R.string.exhausted
        }
    }

    fun getBackGround(state: Int): Color {
        return when (state) {
            1 -> Color.Green
            else -> Color.Red
        }
    }
}