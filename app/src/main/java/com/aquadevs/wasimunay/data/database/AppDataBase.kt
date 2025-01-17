package com.aquadevs.wasimunay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aquadevs.wasimunay.data.database.dao.CameraDao
import com.aquadevs.wasimunay.data.database.dao.UserDao
import com.aquadevs.wasimunay.data.database.model.CameraEntity
import com.aquadevs.wasimunay.data.database.model.UserEntity

/***
 * Class: AppDataBase
 * From: com.aquadevs.wasimunay.data.database
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:29
 * Description:
 *
 ***/

@Database(
    entities = [
        UserEntity::class,
        CameraEntity::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun cameraDao(): CameraDao
}