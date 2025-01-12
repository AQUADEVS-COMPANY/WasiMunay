package com.aquadevs.wasimunay.domain.UseCase.user

import android.util.Log
import com.aquadevs.wasimunay.core.Constants.CO_USER
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: AddUserUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.User
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:15
 * Description:
 *
 ***/

class AddUserNetworkUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val addUserLocalUseCase: AddUserLocalUseCase,
    private val getUserNetworkUseCase: GetUserNetworkUseCase
) {
    suspend operator fun invoke(userDto: UserDto): Boolean {
        var userDtoGeneral = UserDto()
        val userDtoRptNetwork = getUserNetworkUseCase(email = userDto.email)

        if (userDtoRptNetwork == null) {
            val rpt = firestore.collection(CO_USER).add(userDto).await()
            userDtoGeneral = userDto.copy(idDocument = rpt.id)
        } else userDtoGeneral = userDtoRptNetwork

        return addUserLocalUseCase(userDtoGeneral)
    }
}