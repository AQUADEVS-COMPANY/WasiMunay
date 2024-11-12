package com.aquadevs.wasimunay.core

object Validations {
    fun String.validateDouble():Boolean{
        return try {
            var x = this.toDouble()
            true
        }catch (e:Exception){
            false
        }
    }
}