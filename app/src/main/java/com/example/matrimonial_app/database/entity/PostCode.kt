package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo

data class PostCode(
    @ColumnInfo(name = "post_code")
    val postCode: String? = null,

    @ColumnInfo(name = "city")
    val city: String? = null,

    @ColumnInfo(name = "state")
    val state: String? = null,

    @ColumnInfo(name = "country")
    val country: String? = null
)