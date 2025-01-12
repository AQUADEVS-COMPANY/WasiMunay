package com.aquadevs.wasimunay.domain.UseCase.user

import com.aquadevs.wasimunay.core.Constants.CO_APARTMENT
import com.aquadevs.wasimunay.core.Constants.CO_USER
import com.aquadevs.wasimunay.core.Constants.CO_USER_EMAIL_USER
import com.aquadevs.wasimunay.domain.Model.ApartmentModel
import com.aquadevs.wasimunay.domain.Model.user.UserModel
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: GetUserNetworkUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.user
 * Author: Frank Gutierrez
 * Date: 10/01/2025 03:09
 * Description:
 *
 ***/

class GetUserNetworkUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend operator fun invoke(email:String = ""):UserDto?{
        var query:Query? = null
        var userDto: UserDto? = null
        val data = firestore.collection(CO_USER)

        if (email.isNotEmpty()){
           query = data.whereEqualTo(CO_USER_EMAIL_USER, email)
        }

        if (query != null){
            val dataFull = query.get().await()
            for (apartment in dataFull.documents){
                apartment.toObject(UserModel::class.java)?.let {
                    userDto = it.copy(documentId = apartment.id).toUserDto()
                }
            }
        }

        return userDto
    }
}