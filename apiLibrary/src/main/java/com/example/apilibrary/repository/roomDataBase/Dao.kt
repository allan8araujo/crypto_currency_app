package com.example.apilibrary.repository.roomDataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apilibrary.repository.roomDataBase.AssetsDAO.AssetsItemDAO

@Dao
abstract class Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addAsset(asset: AssetsItemDAO)

    @Query("SELECT * FROM favorites ")
    abstract fun readAllAssets(): LiveData<List<AssetsItemDAO>>

}