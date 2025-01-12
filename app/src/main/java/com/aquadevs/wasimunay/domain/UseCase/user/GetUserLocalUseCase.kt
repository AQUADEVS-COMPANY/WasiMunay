package com.aquadevs.wasimunay.domain.UseCase.user

import com.aquadevs.wasimunay.data.database.dao.UserDao
import com.aquadevs.wasimunay.presentation.model.welcome.UserDto
import javax.inject.Inject

/***
 * Class: GetUserLocaUseCase
 * From: com.aquadevs.wasimunay.domain.UseCase.User
 * Author: Frank Gutierrez
 * Date: 8/01/2025 23:48
 * Description:
 *
 ***/

class GetUserLocalUseCase @Inject constructor(
    private val userDao: UserDao
){
    suspend operator fun invoke():UserDto?{
        val listUser = userDao.getProfile()
        return if (listUser.isEmpty()) null else listUser[0].toUserDto()
    }
}