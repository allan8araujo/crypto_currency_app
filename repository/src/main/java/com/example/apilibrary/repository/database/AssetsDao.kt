package com.example.apilibrary.repository.database

import androidx.room.*
import com.example.abstraction.AssetsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface AssetsDao {
    @Query("SELECT * from favorite_assets")
    fun getFavoriteAssets(): Flow<List<AssetsItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAssetToDatabase(item: AssetsItem)

    @Delete()
    suspend fun deleteFavoriteAssetFromDatabase(item: AssetsItem)
}
