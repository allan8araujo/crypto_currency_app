package com.example.apilibrary.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abstraction.AssetsItem

@Database(entities = [AssetsItem::class], version = 1, exportSchema = false)
abstract class AssetsDatabase : RoomDatabase() {
    abstract fun assetsDao(): AssetsDao

    companion object {
        @Volatile
        private var INSTANCE: AssetsDatabase? = null
        fun getDatabase(
            context: Context,
        ): AssetsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AssetsDatabase::class.java, "assets_database"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
