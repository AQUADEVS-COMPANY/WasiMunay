package com.aquadevs.wasimunay.presentation.features.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.wasimunay.domain.UseCase.user.AddUserNetworkUseCase
import com.aquadevs.wasimunay.domain.UseCase.user.GetUserLocalUseCase
import com.aquadevs.wasimunay.domain.UseCase.welcome.LogInUseCase
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Class: WelcomeViewModel
 * From: com.aquadevs.wasimunay.presentation.features.welcome
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:00
 * Description:
 *
 ***/

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val getUserLocalUseCase: GetUserLocalUseCase,
    private val addUserNetworkUseCase: AddUserNetworkUseCase
) : ViewModel() {

    private val _namePerson = MutableLiveData<String>()
    var namePerson: LiveData<String> = _namePerson

    private val _isLoading = MutableLiveData<Boolean>()
    var isLoading: LiveData<Boolean> = _isLoading

    private val _isLogInGoogle = MutableLiveData<Boolean>()
    var isLogInGoogle: LiveData<Boolean> = _isLogInGoogle

    init {
        isLogInWithGoogle()
    }

    fun signInWithGoogle(credential: AuthCredential) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userDto = logInUseCase(credential)
                val rptAddUser = addUserNetworkUseCase(userDto)
                withContext(Dispatchers.Main){
                    if (rptAddUser) {
                        isLogInWithGoogle()
                        _isLogInGoogle.value = false
                    }
                    _isLoading.value = false
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    _isLoading.value = false
                }
            }
        }
    }

    private fun isLogInWithGoogle(){
        viewModelScope.launch(Dispatchers.IO) {
            val rpt = getUserLocalUseCase()
            withContext(Dispatchers.Main){
                _isLogInGoogle.value = rpt == null
                rpt?.let { per ->
                    _namePerson.value = per.name
                }
            }
        }
    }

}