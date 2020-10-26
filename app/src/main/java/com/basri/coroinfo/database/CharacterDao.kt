package com.basri.coroinfo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bas.botinfo.data.entities.cor.Country
import com.bas.botinfo.data.entities.cor.Global
import com.basri.coroinfo.entity.DataCountry

@Dao
interface CharacterDao {

    //    @Query("SELECT * FROM characters")
//    fun getAllCharacters() : LiveData<List<Character>>
//
    @Query("SELECT * FROM country WHERE id = :id")
    fun getDeaths(id: String): DataCountry

    @Query("SELECT * FROM global")
    fun getTotal(): Global

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(data: List<DataCountry>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGlobal(character: Global)


}