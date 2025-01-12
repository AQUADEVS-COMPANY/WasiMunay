package com.aquadevs.wasimunay.domain.UseCase.user

import com.aquadevs.wasimunay.data.database.dao.UserDao
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto
import javax.inject.Inject

/***
 * Class: AddUserLocalUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.User
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:24
 * Description:
 *
 ***/

class AddUserLocalUseCase @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(userDto: UserDto):Boolean{
        return try {
            userDao.insertProfile(userDto.toUserEntity())
            true
        }catch (e:Exception){
            false
        }
    }
}