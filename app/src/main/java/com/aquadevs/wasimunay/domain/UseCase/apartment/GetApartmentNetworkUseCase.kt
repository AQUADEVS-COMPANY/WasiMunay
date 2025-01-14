package com.aquadevs.wasimunay.domain.UseCase.apartment

import com.aquadevs.wasimunay.core.Constants.CO_APARTMENT
import com.aquadevs.wasimunay.domain.Model.ApartmentModel
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/***
 * Class: GetApartmentNetworkUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.apartment
 * Author: Frank Gutierrez
 * Date: 10/01/2025 01:31
 * Description:
 *
 ***/

class GetApartmentNetworkUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    suspend operator fun invoke(idApartment: String): ApartmentDto? {
        var apartmentDto:ApartmentDto? = null
        val data = firestore.collection(CO_APARTMENT)
            .document(idApartment)
            .get().await()

        data.toObject(ApartmentModel::class.java)?.let {
            apartmentDto = it.copy(id = data.id).toApartmentDto()
        }

        return apartmentDto
    }
}