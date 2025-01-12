package com.aquadevs.wasimunay.domain.UseCase.welcome

import com.aquadevs.wasimunay.domain.Model.user.UserModel
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: LogInUseCase
 * From: com.aquadevs.wasimunay.domain.welcome
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:03
 * Description:
 *
 ***/

class LogInUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    suspend operator fun invoke(credential: AuthCredential):UserDto{
        var newUserModel = UserModel()

        firebaseAuth.signInWithCredential(credential)
            .await().additionalUserInfo?.profile?.entries?.forEach { data ->
                if (data.key == "given_name") {
                    newUserModel = newUserModel.copy(name = data.value.toString())
                }
                if (data.key == "family_name") {
                    newUserModel = newUserModel.copy(surnames = data.value.toString())
                }
                if (data.key == "picture") {
                    newUserModel = newUserModel.copy(urlPictureEmail = data.value.toString())
                }
                if (data.key == "email") {
                    newUserModel = newUserModel.copy(email = data.value.toString())
                }
                if (data.key == "email_verified") {
                    newUserModel = newUserModel.copy(emailVerified = data.value as Boolean)
                }
            }

        return newUserModel.toUserDto().copy(isActivated = true)
    }
}