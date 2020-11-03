package com.example.matrimonial_app.repositories

import android.util.Log
import com.example.matrimonial_app.database.UserDao
import com.example.matrimonial_app.database.entity.User
import com.example.matrimonial_app.network.CloudToDao
import com.example.matrimonial_app.network.MatrimonialUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MatrimonialUserRepo(
    private val matrimonialUserService: MatrimonialUserService,
    private val userDao: UserDao
) {
    private val TAG = MatrimonialUserRepo::class.java.name

    suspend fun loadUsers(userCount: Int): Pair<String?, List<User>?> {
        val userList = loadFromDb()
        return if (shouldFetchData(userList)) fetchAndSaveUser(userCount) else Pair(null, userList)
    }

    private suspend fun fetchAndSaveUser(userCount: Int): Pair<String?, List<User>?> = withContext(Dispatchers.IO) {
        // launch the services to load data
        return@withContext try {
            matrimonialUserService.fetchUsers(userCount).userResponseModelList?.mapNotNull {
                CloudToDao.fromUserResponseModelToUserDao(it)
            }?.also {
                saveUsers(it)
            }
            Pair(null, loadFromDb())
        } catch (numberFormatException: NumberFormatException) {
            Log.d(TAG, "Error while fetching user data ${numberFormatException.message}")
            Pair(numberFormatException.message, null)
        } catch (ex: Exception) {
            Log.d(TAG, "Error while fetching user data ${ex.message}")
            Pair(ex.message, null)
        }
    }

    private fun shouldFetchData(data: List<User>?) = data.isNullOrEmpty()

    private suspend fun saveUsers(data: List<User>) = withContext(Dispatchers.IO) { userDao.insertAll(data) }

    private suspend fun loadFromDb() = withContext(Dispatchers.IO) { userDao.getUsers() }
}