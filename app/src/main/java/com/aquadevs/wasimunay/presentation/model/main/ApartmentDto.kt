package com.aquadevs.wasimunay.presentation.model.main

import com.aquadevs.wasimunay.domain.Model.ApartmentModel

data class ApartmentDto(
    val id:String = "",
    val apartmentName: String = "",
    val apartmentState: Int = 0,
    val roomNumber: Int = 0,
    val bathroomNumber: Int = 0,
    val squareMetersNumber: Int = 0,
    val departmentPrice: Double = 0.0,
    val description:String = "",
    val location:String = "",
    val linkApartment:String = ""
){
    fun toApartmentModel():ApartmentModel{
        return ApartmentModel(
            apartmentName = this.apartmentName,
            apartmentState = this.apartmentState,
            roomNumber = this.roomNumber,
            bathroomNumber = this.bathroomNumber,
            squareMetersNumber = this.squareMetersNumber,
            departmentPrice = this.departmentPrice,
            description = this.description,
            location = this.location,
            linkApartment = this.linkApartment,
            creationDate = System.currentTimeMillis().toString()
        )
    }
}
