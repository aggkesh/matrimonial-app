package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo

/**
 * Created by Keshav Aggarwal 11/3/2020
 *
 * Entity class to hold user picture
 */
data class Picture(
    @ColumnInfo(name = "large")
    val large: String? = null,
    @ColumnInfo(name = "medium")
    val medium: String? = null,
    @ColumnInfo(name = "thumbnail")
    val thumbNail: String? = null
)