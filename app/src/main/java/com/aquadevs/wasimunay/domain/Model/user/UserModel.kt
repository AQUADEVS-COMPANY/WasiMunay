package com.aquadevs.wasimunay.domain.Model.user

import com.aquadevs.wasimunay.presentation.model.welcome.UserDto

data class UserModel(
    val documentId:String? = "",
    val userId:String? = "",
    val name:String? = "",
    val surnames:String? = "",
    val email:String? = "",
    val password:String? = "",
    val cellPhone:String? = "",
    val dni:String? = "",
    val typeUser:String? = "",
    val urlPictureEmail:String? = "",
    val creationDate:String? = "",
    val flgActivated:Boolean? = false,
    val emailVerified:Boolean? = false,
){
    fun toUserDto():UserDto{
        return UserDto(
            idDocument = this.documentId ?: "",
            name = this.name ?: "",
            surnames = this.surnames ?: "",
            email = this.email ?: "",
            cellPhone = this.cellPhone ?: "",
            dni = this.dni ?: "",
            typeUser = this.typeUser ?: "",
            isActivated = this.flgActivated ?: false,
            password = this.password ?: "",
            urlPicture = this.urlPictureEmail ?: "",
            isLogInGoogle = this.emailVerified ?: false
        )
    }
}
