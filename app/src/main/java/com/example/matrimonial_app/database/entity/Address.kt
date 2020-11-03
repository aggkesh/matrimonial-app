package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class Address(
    @ColumnInfo(name = "street_number")
    val streetNumber: Long? = null,

    @ColumnInfo(name = "street_name")
    val streetName: String? = null,

    @Embedded
    val postCode: PostCode
)