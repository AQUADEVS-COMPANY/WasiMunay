package com.aquadevs.wasimunay.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aquadevs.wasimunay.data.database.model.CameraEntity
import kotlinx.coroutines.flow.Flow

/***
 * Class: CameraDao
 * From: com.aquadevs.wasimunay.data.database.dao
 * Author: Frank Gutierrez
 * Date: 17/01/2025 02:06
 * Description:
 *
 ***/

@Dao
interface CameraDao {
    @Query("SELECT * FROM CameraEntity")
    fun getListCamera() : Flow<List<CameraEntity>>

    @Query("SELECT * FROM CameraEntity WHERE nameId=:nameId")
    suspend fun getCamera(nameId:String) : CameraEntity

    @Insert
    suspend fun addCamera(item:CameraEntity)

    @Update
    suspend fun updateCamera(item:CameraEntity)

    @Query("UPDATE CameraEntity SET flgSave=:flgSave, description=:description WHERE nameId=:id")
    suspend fun updateCustomCamera(flgSave:Boolean, description:String, id:String)

    @Delete
    suspend fun deleteCamera(item:CameraEntity)

    @Query("DELETE FROM CameraEntity")
    suspend fun deleteCustomCamera()
}