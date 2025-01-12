package com.aquadevs.wasimunay.domain.UseCase.welcome

import android.content.Context
import com.aquadevs.wasimunay.core.Constants.ID_TOKEN_GOOGLE
import com.aquadevs.wasimunay.domain.UseCase.user.DeleteUserLocalUserCase
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: LogOutUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.welcome
 * Author: Frank Gutierrez
 * Date: 9/01/2025 00:22
 * Description:
 *
 ***/

class LogOutUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val deleteUserLocalUserCase: DeleteUserLocalUserCase
) {
    suspend operator fun invoke(context: Context): Boolean {
        return try {
            val res = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ID_TOKEN_GOOGLE)
                .requestEmail()
                .build()

            GoogleSignIn.getClient(context, res).signOut().await()
            deleteUserLocalUserCase()
            firebaseAuth.signOut()
            true
        } catch (e: Exception) {
            false
        }
    }
}