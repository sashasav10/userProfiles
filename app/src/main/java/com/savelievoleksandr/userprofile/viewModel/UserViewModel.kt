package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val userLiveDataList = MutableLiveData<List<User>>()
    private var dataSource = UserDatabase.getInstance(app).userDatabaseDao()

    init {
        viewModelScope.launch {
            fillUpDatabase()
        }
        viewModelScope.launch(Dispatchers.Main) {
            loadUserData()
        }
    }

    private suspend fun fillUpDatabase() {
        if (dataSource.getUser() == null) {
            for (user in dataSource.getAllUsers()) {
                dataSource.insert(user)
            }
        }
    }

    private suspend fun loadUserData() {
        userLiveDataList.postValue(dataSource.getAllUsers())
    }

    fun getAllLivaData(): LiveData<List<User>> {
        return dataSource.getAllUsersLiveData()
    }
}


