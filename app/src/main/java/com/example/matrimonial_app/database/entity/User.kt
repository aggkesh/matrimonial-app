package com.example.matrimonial_app.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table",
    indices = [Index(value = ["user_name", "password", "email_id"], unique = true)]
)
class User(
    @PrimaryKey
    @ColumnInfo(name = "_id")
    val uuid: String,

    @ColumnInfo(name = "user_name")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "email_id")
    val email: String,

    @Embedded
    val userDetail: UserDetail,

    @Embedded
    val address: Address?,

    @ColumnInfo(name = "accepted_match")
    val acceptedMatch: Boolean? = null
)