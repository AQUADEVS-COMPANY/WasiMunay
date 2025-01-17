package com.aquadevs.wasimunay.domain.UseCase.camera

import com.aquadevs.wasimunay.data.database.dao.CameraDao
import javax.inject.Inject

/***
 * Class: DeleteAllCameraUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:18
 * Description:
 *
 ***/

class DeleteCustomCameraUseCase @Inject constructor(
    private val cameraDao: CameraDao
) {
    suspend operator fun invoke(){
        cameraDao.deleteCustomCamera()
    }
}