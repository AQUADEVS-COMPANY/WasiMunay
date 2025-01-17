package com.aquadevs.wasimunay.presentation.features.notifications

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Class: NotificationViewModel
 * From: com.aquadevs.wasimunay.presentation.features.notifications
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:53
 * Description:
 *
 ***/

@HiltViewModel
class NotificationViewModel @Inject constructor(

) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onBack(activity: Activity) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500)
            withContext(Dispatchers.Main) {
                activity.finish()
                _isLoading.value
            }
        }
    }

    fun openAppSettingsLocation(context: Context) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent)
    }
}