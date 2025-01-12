package com.aquadevs.wasimunay.presentation.model.welcome

import com.aquadevs.wasimunay.data.database.model.UserEntity
import com.aquadevs.wasimunay.domain.Model.user.UserModel

data class UserDto(
    val idDocument: String = "",
    val name: String = "",
    val surnames: String = "",
    val email: String = "",
    val password: String = "",
    val cellPhone: String = "",
    val dni: String = "",
    val typeUser: String = "",
    val urlPicture: String = "",
    val isActivated: Boolean = false,
    val isLogInGoogle: Boolean = false,
){
    fun toUserModel():UserModel{
        return UserModel(
            documentId = this.idDocument,
            name = this.name,
            surnames = this.surnames,
            email = this.email,
            cellPhone = this.cellPhone,
            dni = this.dni,
            typeUser = this.typeUser,
            flgActivated = this.isActivated,
            password = this.password,
            urlPictureEmail = this.urlPicture,
            emailVerified = this.isLogInGoogle,
            creationDate = System.currentTimeMillis().toString()
        )
    }

    fun toUserEntity() : UserEntity {
        return UserEntity(
            id = this.idDocument,
            dni = this.dni,
            name = this.name,
            surnames = this.surnames,
            email = this.email,
            password = this.password,
            nroCellPhone = this.cellPhone,
            typeUser = this.typeUser,
            urlPicture = this.urlPicture,
            isLoginGoogle = this.isLogInGoogle
        )
    }
}

