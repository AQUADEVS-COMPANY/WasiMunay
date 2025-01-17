package com.aquadevs.wasimunay.domain.UseCase.camera

import com.aquadevs.wasimunay.data.database.dao.CameraDao
import com.aquadevs.wasimunay.presentation.model.welcome.CameraDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/***
 * Class: GetListCameraUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.camera
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:22
 * Description:
 *
 ***/

class GetListCameraUseCase @Inject constructor(
    private val cameraDao: CameraDao
) {
    operator fun invoke(): Flow<List<CameraDto>> {
        return cameraDao.getListCamera().map { items ->
            items.map {
                CameraDto(
                    nameId = it.nameId,
                    nameCollection = it.nameCollection,
                    nroPhotos = it.nroPhotos,
                    description = it.description,
                    flgSave = it.flgSave
                )
            }
        }
    }
}