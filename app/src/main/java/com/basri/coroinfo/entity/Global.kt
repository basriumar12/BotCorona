package com.bas.botinfo.data.entities.cor

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "global")
data class Global(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @SerializedName("NewConfirmed")
    val newConfirmed: Int,

    @SerializedName("NewDeaths")
    val newDeaths: Int,

    @SerializedName("NewRecovered")
    val newRecovered: Int,

    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int,

    @SerializedName("TotalDeaths")
    val totalDeaths: Int,

    @SerializedName("TotalRecovered")
    val totalRecovered: Int
)