package com.bas.botinfo.data.entities.cor

data class ResponseData(
    val Countries: List<Country>,
    val Date: String,
    val Global: Global,
    val Message: String
)