package com.example.apilibrary.repository.roomDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO

@Database(entities = [AssetsItemDAO::class], version = 1, exportSchema = false)
abstract class AssetsDatabase: RoomDatabase(){

    abstract fun assetDao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: AssetsDatabase? = null

        fun getDatabase(context: Context): AssetsDatabase{
            val tempInst = INSTANCE
            if(tempInst != null){
                return tempInst
            }
            synchronized(this){
                val instance  = Room.databaseBuilder(
                    context.applicationContext,
                    AssetsDatabase::class.java,
                    "favorites"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

