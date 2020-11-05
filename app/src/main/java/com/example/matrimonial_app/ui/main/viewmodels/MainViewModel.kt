package com.example.matrimonial_app.ui.main.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.matrimonial_app.R
import com.example.matrimonial_app.database.UsersRoomDatabase
import com.example.matrimonial_app.network.MatrimonialNetwork
import com.example.matrimonial_app.repositories.MatrimonialUserRepo
import com.example.matrimonial_app.ui.main.models.DoToUiModel
import com.example.matrimonial_app.ui.main.models.UserUiModel
import kotlinx.coroutines.launch

// Type alias for parameterized ScreenState for this viewmodel
typealias MainScreenState = ScreenState<Int, Int, List<UserUiModel>>

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * ViewModel to display Matrimonial users data
 */
class MainViewModel(private val matrimonialUserRepo: MatrimonialUserRepo) : ViewModel() {
    private val _screenState = MutableLiveData<MainScreenState>()
    private var userData: MutableList<UserUiModel>? = null

    /**
     * [LiveData] of the state of the screen, wrapped in a [ScreenState] object
     */
    val screenState: LiveData<MainScreenState>
        get() = _screenState

    /**
     * Load list of users
     */
    fun loadUsers() {
        viewModelScope.launch {
            _screenState.value =
                ScreenState.Loading(R.string.progress_message_loading)
            matrimonialUserRepo.loadUsers(10).run {
                _screenState.value = if (first != null) {
                    ScreenState.Error(R.string.alert_message_something_went_wrong)
                } else {
                    ScreenState.Data(second?.let { users ->
                        users.map { user ->
                            DoToUiModel.fromUserToUserUiModel(
                                user
                            )
                        }
                            .also { userData = it.toMutableList() }
                    } ?: kotlin.run { emptyList<UserUiModel>() })
                }
            }
        }
    }

    /**
     * Update user selection (accept/decline) for given userId
     *
     * @param userId id for [UserUiModel]
     * @param userSelected true/false represent accept/decline selected
     */
    fun updateUserSelection(userId: String, userSelected: Boolean) {
        viewModelScope.launch {
            matrimonialUserRepo.updateUserSelection(userId, userSelected).run {
                DoToUiModel.fromUserToUserUiModel(this)
            }.let {
                userData?.let { list ->
                    list.forEachIndexed { index, userUiModel ->
                        if (userUiModel.userId == it.userId) {
                            userData?.set(index, it)
                            _screenState.value = ScreenState.Data(list)
                        }
                    }
                }
            }
        }
    }

    /**
     * Factory to create a [MainViewModel]
     *
     * @param appContext [Context]
     */
    @Suppress("UNCHECKED_CAST")
    class MainViewModelFactory(private val appContext: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            MainViewModel(
                MatrimonialUserRepo(
                    MatrimonialNetwork.matrimonialUserService,
                    UsersRoomDatabase.getDatabase(appContext).userDao()
                )
            ) as T
    }
}
