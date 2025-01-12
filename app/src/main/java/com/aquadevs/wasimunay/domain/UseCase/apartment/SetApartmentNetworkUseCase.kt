package com.aquadevs.wasimunay.domain.UseCase.apartment

import com.aquadevs.wasimunay.core.Constants.CO_APARTMENT
import com.aquadevs.wasimunay.domain.UseCase.user.GetUserLocalUseCase
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: SetApartmentNetworkUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.apartment
 * Author: Frank Gutierrez
 * Date: 10/01/2025 00:34
 * Description:
 *
 ***/

class SetApartmentNetworkUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val getUserLocalUseCase: GetUserLocalUseCase
) {
    suspend operator fun invoke(apartmentDto: ApartmentDto): Boolean {
        return try {
            val userDto = getUserLocalUseCase()
            if (userDto == null) false
            else{
                firestore.collection(CO_APARTMENT)
                    .add(apartmentDto.toApartmentModel().copy(idUserCreate = userDto.idDocument))
                    .await()
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}