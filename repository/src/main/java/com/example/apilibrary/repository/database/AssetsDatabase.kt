package com.example.apilibrary.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.abstraction.AssetsItem

@Database(entities = [(AssetsItem::class)], version = 1)
abstract class AssetsDatabase : RoomDatabase() {
    abstract fun assetsDao(): AssetsDao

    companion object {

        private var INSTANCE: AssetsDatabase? = null
        fun getDatabase(context: Context): AssetsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AssetsDatabase::class.java, "favorite_asset"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
