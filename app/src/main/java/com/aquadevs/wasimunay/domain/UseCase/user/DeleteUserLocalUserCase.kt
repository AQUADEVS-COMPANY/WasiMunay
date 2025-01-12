package com.aquadevs.wasimunay.domain.UseCase.user

import com.aquadevs.wasimunay.data.database.dao.UserDao
import javax.inject.Inject

/***
 * Class: DeleteUserLocalUserCase
 * From: com.aquadevs.wasimunay.domain.UseCase.User
 * Author: Frank Gutierrez
 * Date: 9/01/2025 00:25
 * Description:
 *
 ***/

class DeleteUserLocalUserCase @Inject constructor(
    private val userDao: UserDao
) {
    suspend operator fun invoke(): Boolean {
        return try {
            userDao.deleteProfile()
            true
        } catch (e: Exception) {
            false
        }
    }
}