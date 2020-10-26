package com.bas.botinfo.data.entities.cor

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Country(

    @SerializedName("Country")
    val country: String,

    @SerializedName("CountryCode")
    val countryCode: String,

    @SerializedName("Date")
    val date: String,

    @SerializedName("NewConfirmed")
    val newConfirmed: Int,


    @SerializedName("NewDeaths")
    val newDeaths: Int,

    @SerializedName("NewRecovered")
    val newRecovered: Int,

    @SerializedName("Premium")
    val premium: Premium,

    @SerializedName("Slug")
    val slug: String,

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,

    @SerializedName("TotalDeaths")
    val totalDeaths: Int,

    @SerializedName("TotalRecovered")
    val totalRecovered: Int
)