package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class UserDetail(
    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "last_name")
    val lastName: String? = null,

    @ColumnInfo(name = "cell_number")
    val cellNumber: String? = null,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String? = null,

//    @ColumnInfo(name = "date_of_birth")
//    val dateOfBirth: Date,

    @Embedded
    val picture: Picture?
)