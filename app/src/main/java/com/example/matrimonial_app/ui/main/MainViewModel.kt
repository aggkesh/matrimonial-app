package com.example.matrimonial_app.ui.main

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
import kotlinx.coroutines.launch

// Type alias for parameterized ScreenState for this viewmodel
typealias MainScreenState = ScreenState<Int, Int, List<UserUiModel>>

/**
 * Created by Keshav Aggarwal 11/2/2020
 *
 * ViewModel to display Matrimonial App user data
 */
class MainViewModel(private val matrimonialUserRepo: MatrimonialUserRepo) : ViewModel() {
    private val _screenState = MutableLiveData<MainScreenState>()

    /**
     * [LiveData] of the state of the screen, wrapped in a [ScreenState] object
     */
    val screenState: LiveData<MainScreenState>
        get() = _screenState

    fun loadUsers() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading(R.string.progress_message_loading)
            matrimonialUserRepo.loadUsers(10).run {
                _screenState.value = if (first != null) {
                    ScreenState.Error(R.string.alert_message_something_went_wrong)
                } else {
                    ScreenState.Data(second?.let {
                        it.map { user -> DoToUiModel.fromUserToUserUiModel(user) }
                    } ?: kotlin.run { emptyList<UserUiModel>() })
                }
            }
        }
    }

    /**
     * Factory to create a [MainViewModel]
     *
     * @param appContext [Context]
     */
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
