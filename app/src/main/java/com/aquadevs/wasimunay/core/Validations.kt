package com.aquadevs.wasimunay.core

object Validations {
    fun String.validateDouble(): Boolean {
        return try {
            if (this == "") true
            else {
                this.toDouble()
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    fun String.validateInt(): Boolean {
        return try {
            if (this == "") true
            else {
                this.toInt()
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    fun getStringToInt(str: String?):Int{
        return try {
            str!!.toInt()
        }catch (e:Exception){
            0
        }
    }

    fun getStringToDouble(str: String?):Double{
        return try {
            str!!.toDouble()
        }catch (e:Exception){
            0.0
        }
    }
}