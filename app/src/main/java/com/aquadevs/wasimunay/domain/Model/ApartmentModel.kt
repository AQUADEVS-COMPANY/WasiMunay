package com.aquadevs.wasimunay.domain.Model

import com.aquadevs.wasimunay.presentation.model.main.ApartmentDto
import com.google.firebase.firestore.Exclude

data class ApartmentModel(
    @Exclude val id:String? = "",
    val apartmentName: String? = "",
    val apartmentState: Int? = 0,
    val roomNumber: Int? = 0,
    val bathroomNumber: Int? = 0,
    val squareMetersNumber: Int? = 0,
    val departmentPrice: Double? = 0.0,
    val description:String? = "",
    val location:String? = "",
    val linkApartment:String? = "",
    val creationDate:String? = "",
    val idUserCreate:String? = ""
){
    fun toApartmentDto() : ApartmentDto{
        return ApartmentDto(
            id = this.id ?: "",
            apartmentName = this.apartmentName ?: "",
            apartmentState = this.apartmentState ?: 0,
            roomNumber = this.roomNumber ?: 0,
            bathroomNumber = this.bathroomNumber ?: 0,
            squareMetersNumber = this.squareMetersNumber ?: 0,
            departmentPrice = this.departmentPrice ?: 0.0,
            description = this.description ?:"",
            location = this.location ?: "",
            linkApartment = this.linkApartment ?: ""
        )
    }
}
