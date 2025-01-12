package com.aquadevs.wasimunay.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto

@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val dni: String?,
    val name: String?,
    val surnames: String?,
    val email: String?,
    val password: String?,
    val nroCellPhone: String?,
    val typeUser: String?,
    val urlPicture: String?,
    val isLoginGoogle: Boolean? = false
){
    fun toUserDto() : UserDto{
        return UserDto(
            idDocument = this.id ?: "",
            name = this.name ?: "",
            surnames = this.surnames ?: "",
            email = this.email ?: "",
            dni = this.dni ?: "",
            cellPhone = this.nroCellPhone ?: "",
            typeUser = this.typeUser ?: "",
            isActivated = true,
            password = this.password ?: "",
            urlPicture = this.urlPicture ?: ""
        )
    }
}