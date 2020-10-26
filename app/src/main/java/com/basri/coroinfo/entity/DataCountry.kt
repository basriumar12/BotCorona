package com.basri.coroinfo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bas.botinfo.data.entities.cor.Premium


@Entity(tableName = "country")
data class DataCountry(
    @PrimaryKey
    val id : String,
    val country: String,
    val countryCode: String,
    val date: String,
    val newConfirmed: Int,
    val newDeaths: Int,
    val newRecovered: Int,
    val slug: String,
    val totalConfirmed: Int,
    val totalDeaths: Int,
    val totalRecovered: Int
)