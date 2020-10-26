package com.basri.coroinfo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bas.botinfo.data.entities.cor.Global
import com.basri.coroinfo.entity.DataCountry

@Database(entities = [DataCountry::class, Global::class], version = 3,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        open fun createDatabase(context: Context?): AppDatabase? {
            synchronized(AppDatabase::class.java) {
                if (INSTANCE == null) {
                    INSTANCE = context?.let {
                        Room.databaseBuilder(it, AppDatabase::class.java, "datacorona")
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }

}