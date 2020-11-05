package com.example.matrimonial_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matrimonial_app.database.entity.User

/**
 * Created by Keshav Aggarwal 11/3/2020
 *
 * User Room Database class
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UsersRoomDatabase? = null

        fun getDatabase(appContext: Context): UsersRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(appContext, UsersRoomDatabase::class.java, "user_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}