package com.example.apilibrary.repository.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.abstraction.AssetsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetsDao {
    @Query("SELECT * from favorite_assets")
    fun getFavoriteAssets(): Flow<List<AssetsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(item: AssetsItem)

    @Delete()
    suspend fun deleteFavorite(item: AssetsItem)
}
