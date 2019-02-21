package com.vodrex.login_sample.utils

enum class CompanyType () {
    TIEMPO_AIRE, MEGAS
}

fun CompanyType.getValue() : String{
    return when(this){
        CompanyType.TIEMPO_AIRE -> "Tiempo Aire"
        CompanyType.MEGAS -> "Megas"
    }
}

fun String.toCompanyType() : CompanyType{
    return when(this){
        "ta" -> CompanyType.TIEMPO_AIRE
        "mb" -> CompanyType.MEGAS
        else -> CompanyType.TIEMPO_AIRE
    }
}