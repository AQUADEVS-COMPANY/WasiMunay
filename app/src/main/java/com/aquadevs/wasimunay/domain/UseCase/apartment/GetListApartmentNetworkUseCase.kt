package com.aquadevs.wasimunay.domain.UseCase.apartment

import android.content.Context
import android.content.IntentFilter
import android.util.Log
import com.aquadevs.wasimunay.core.Constants.CO_APARTMENT
import com.aquadevs.wasimunay.core.File
import com.aquadevs.wasimunay.domain.Model.ApartmentModel
import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class GetListApartmentNetworkUseCase @Inject constructor(
    private val firestore: FirebaseFirestore
){
    suspend operator fun invoke(context: Context):List<ApartmentDto>{
        val listApartment = mutableListOf<ApartmentDto>()
        val data = firestore.collection(CO_APARTMENT).get().await()

        for (apartment in data.documents){
            apartment.toObject(ApartmentModel::class.java)?.let {
                var apartmentDto = it.copy(id = apartment.id).toApartmentDto()
                val listBitmap = File.getImagesFromCacheDirectory(context, apartmentDto.linkApartment)

                if (listBitmap.isNotEmpty()) {
                    apartmentDto = apartmentDto.copy(bitmap = listBitmap[0])
                }

                listApartment.add(apartmentDto)
            }
        }

        return listApartment
    }
}
