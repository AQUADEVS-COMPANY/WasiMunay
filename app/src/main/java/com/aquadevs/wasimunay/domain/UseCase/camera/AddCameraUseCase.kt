package com.aquadevs.wasimunay.domain.UseCase.camera

import com.aquadevs.wasimunay.data.database.dao.CameraDao
import com.aquadevs.wasimunay.presentation.model.welcome.CameraDto
import javax.inject.Inject

/***
 * Class: AddCameraUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:13
 * Description:
 *
 ***/

class AddCameraUseCase @Inject constructor(
    private val collectionDao: CameraDao
) {
    suspend operator fun invoke(cameraDto: CameraDto){
        collectionDao.addCamera(cameraDto.toCameraEntity())
    }
}