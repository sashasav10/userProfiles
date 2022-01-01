package com.savelievoleksandr.userprofile.viewModel

import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData
import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    val userData: UserData = UserData()
    val userLiveDataList = MutableLiveData<List<User>>()
    var userLiveData: LiveData<List<User>> = userLiveDataList
    var dataSource = UserDatabase.getInstance(app).userDatabaseDao()
    private var id = MutableLiveData<Int>()
    val userId: LiveData<Int> = id

    init {
        viewModelScope.launch {
            fillUpDatabase()
        }
        viewModelScope.launch(Dispatchers.Main) {
            loadUserData()
        }
    }

    suspend fun fillUpDatabase() {
        if (dataSource.getUser() == null) {
            for (user in dataSource.getAllUsers()) {
                dataSource.insert(user)
            }
        }
    }

    suspend fun loadUserData() {
        userLiveDataList.postValue(dataSource.getAllUsers())
    }

    fun getAllLivaData(): LiveData<List<User>> {
        return dataSource.getAllUsersLiveData()
    }
}


