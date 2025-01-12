package com.aquadevs.wasimunay.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.aquadevs.wasimunay.data.database.model.UserEntity

@Dao
interface UserDao {
    @Insert
    suspend fun insertProfile(item: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteProfile()

    @Query("SELECT * FROM UserEntity")
    suspend fun getProfile() : List<UserEntity>
}