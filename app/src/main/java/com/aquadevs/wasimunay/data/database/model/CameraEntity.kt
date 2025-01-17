package com.aquadevs.wasimunay.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aquadevs.wasimunay.presentation.model.welcome.CameraDto

/***
 * Class: CameraEntity
 * From: com.aquadevs.wasimunay.data.database.model
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:06
 * Description:
 *
 ***/

@Entity(tableName = "CameraEntity")
data class CameraEntity(
    @PrimaryKey
    val nameId: String,
    val nameCollection: String,
    val nroPhotos: Int,
    val description: String,
    val flgSave: Boolean
) {
    fun toCameraDto(): CameraDto {
        return CameraDto(
            nameId = this.nameId,
            nameCollection = this.nameCollection,
            nroPhotos = this.nroPhotos,
            description = this.description,
            flgSave = this.flgSave
        )
    }
}