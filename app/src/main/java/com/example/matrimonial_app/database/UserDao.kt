package com.example.matrimonial_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.matrimonial_app.database.entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table WHERE _id=:userId")
    suspend fun delete(userId: String)
}