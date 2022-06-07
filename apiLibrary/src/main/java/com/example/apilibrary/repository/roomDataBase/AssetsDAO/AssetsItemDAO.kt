package com.example.apilibrary.repository.roomDataBase.AssetsDAO

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class AssetsItemDAO (
    @PrimaryKey val asset_id: String,
    @ColumnInfo(name = "asset_name") val asset_name: String,
    @ColumnInfo(name = "price_usd") val price_usd: Double? = null,
    @ColumnInfo(name = "type_is_crypto") val type_is_crypto: Int,
    @ColumnInfo(name = "volume_1day_usd") val volume_1day_usd: Double,
    @ColumnInfo(name = "volume_1hrs_usd") val volume_1hrs_usd: Double,
    @ColumnInfo(name = "volume_1mth_usd") val volume_1mth_usd: Double,
    @ColumnInfo(name = "id_icon") val id_icon: String? = null,
)
