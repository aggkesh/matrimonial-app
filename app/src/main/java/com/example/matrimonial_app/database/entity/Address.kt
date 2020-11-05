package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

/**
 * Created by Keshav Aggarwal 11/3/2020
 *
 * Entity class to hold address of user
 */
data class Address(
    @ColumnInfo(name = "street_number")
    val streetNumber: Long? = null,

    @ColumnInfo(name = "street_name")
    val streetName: String? = null,

    @Embedded
    val postCode: PostCode
)