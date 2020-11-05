package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo

/**
 * Created by Keshav Aggarwal 11/3/2020
 *
 * Entity class to hold postcode of user
 */
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