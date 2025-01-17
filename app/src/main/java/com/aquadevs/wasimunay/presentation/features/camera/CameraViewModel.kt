package com.aquadevs.wasimunay.presentation.features.camera

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aquadevs.wasimunay.core.Constants.WASIMUNAY_FILE_PHOTOS
import com.aquadevs.wasimunay.core.File.getCacheNameDirectories
import com.aquadevs.wasimunay.core.File.saveBitmapToFile
import com.aquadevs.wasimunay.domain.UseCase.camera.AddCameraUseCase
import com.aquadevs.wasimunay.presentation.model.welcome.CameraDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/***
 * Class: CameraViewModel
 * From: com.aquadevs.wasimunay.presentation.features.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 01:54
 * Description:
 *
 ***/

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val addCameraUseCase: AddCameraUseCase
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading:LiveData<Boolean> = _isLoading

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun takePhoto(
        context: Context,
        controller: LifecycleCameraController,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        _isLoading.value = true
        controller.takePicture(
            ContextCompat.getMainExecutor(context.applicationContext),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                        if (controller.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
                            postScale(-1f, 1f)
                        }
                    }

                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )

                    onPhotoTaken(rotatedBitmap)
                    image.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("FRANK_GF", exception.message.toString())
                }
            }
        )
    }

    fun onTakePhoto(activity: Activity, bitmap: Bitmap) {
        _bitmaps.value += bitmap
        if (_bitmaps.value.isNotEmpty()) {
            sendToBitmapStr(activity)
        }
    }

    private fun sendToBitmapStr(activity: Activity) {
        if (_bitmaps.value.isEmpty()) {
            _isLoading.value = false
            return
        }

        //_isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                var contDirectory = 0
                var nameDirectory = WASIMUNAY_FILE_PHOTOS
                val listNameDirectories = mutableListOf<String>()
                val listDirectories = getCacheNameDirectories(activity)
                val numberDirectory = System.currentTimeMillis().toString()

                listDirectories
                    .filter { it.contains(WASIMUNAY_FILE_PHOTOS) }
                    .forEach { nameFile ->
                        listNameDirectories.add(nameFile)
                    }

                nameDirectory = "${nameDirectory}${numberDirectory}"

                _bitmaps.value.forEach { bitmap ->
                    val compressBitmap = Bitmap.createScaledBitmap(
                        bitmap,
                        bitmap.width / 2,
                        bitmap.height / 2,
                        false
                    )

                    contDirectory++
                    saveBitmapToFile(
                        context = activity,
                        bitmap = compressBitmap,
                        fileName = "${nameDirectory}_$contDirectory",
                        nameDirectory = nameDirectory
                    )
                }

                addCameraUseCase(
                    cameraDto = CameraDto(
                        nameId = nameDirectory,
                        nameCollection = nameDirectory,
                        nroPhotos = _bitmaps.value.size,
                        description = "",
                    )
                )

                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    activity.finish()
                }
            } catch (e: Exception) {

            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                    activity.finish()
                }
            }
        }
    }
}