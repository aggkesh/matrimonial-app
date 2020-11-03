package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo

data class Picture(
    @ColumnInfo(name = "large")
    val large: String? = null,
    @ColumnInfo(name = "medium")
    val medium: String? = null,
    @ColumnInfo(name = "thumbnail")
    val thumbNail: String? = null
)