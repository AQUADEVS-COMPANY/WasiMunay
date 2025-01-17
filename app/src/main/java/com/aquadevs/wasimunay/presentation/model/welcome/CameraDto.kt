package com.aquadevs.wasimunay.presentation.model.welcome

import com.aquadevs.wasimunay.data.database.model.CameraEntity

/***
 * Class: CameraDao
 * From: com.aquadevs.wasimunay.presentation.model.welcome
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:09
 * Description:
 *
 ***/

data class CameraDto(
    val nameId: String,
    var nameCollection: String = "",
    val nroPhotos: Int = 0,
    val description: String = "",
    val flgSave: Boolean = false
) {
    fun toCameraEntity(): CameraEntity {
        return CameraEntity(
            nameId = this.nameId,
            nameCollection = this.nameCollection,
            nroPhotos = this.nroPhotos,
            description = this.description,
            flgSave = this.flgSave
        )
    }
}