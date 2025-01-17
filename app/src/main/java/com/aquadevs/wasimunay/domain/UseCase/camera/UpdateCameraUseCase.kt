package com.aquadevs.wasimunay.domain.UseCase.camera

import com.aquadevs.wasimunay.data.database.dao.CameraDao
import com.aquadevs.wasimunay.presentation.model.welcome.CameraDto
import javax.inject.Inject

/***
 * Class: UpdateCameraUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:26
 * Description:
 *
 ***/

class UpdateCameraUseCase @Inject constructor(
    private val cameraDao: CameraDao
) {
    suspend operator fun invoke(cameraDto: CameraDto){
        cameraDao.updateCamera(cameraDto.toCameraEntity())
    }
}