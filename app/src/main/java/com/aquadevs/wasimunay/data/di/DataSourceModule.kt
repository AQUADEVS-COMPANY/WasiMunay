package com.aquadevs.wasimunay.data.di

import android.content.Context
import androidx.room.Room
import com.aquadevs.wasimunay.core.Constants.NAME_DATABASE
import com.aquadevs.wasimunay.data.database.AppDataBase
import com.aquadevs.wasimunay.data.database.dao.CameraDao
import com.aquadevs.wasimunay.data.database.dao.UserDao
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Class: DataSourceModule
 * From: com.aquadevs.wasimunay.data.di
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:05
 * Description:
 *
 ***/

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun roomDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = NAME_DATABASE
        ).build()
    }

    @Singleton
    @Provides
    fun provideProfileDao(appDataBase: AppDataBase) : UserDao {
        return appDataBase.userDao()
    }

    @Singleton
    @Provides
    fun provideCameraDao(appDataBase: AppDataBase) : CameraDao {
        return appDataBase.cameraDao()
    }
}