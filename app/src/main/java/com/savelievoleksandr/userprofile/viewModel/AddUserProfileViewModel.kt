package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import kotlinx.coroutines.launch

class AddUserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val dataSource = UserDatabase.getInstance(application).userDatabaseDao()
    var size = 0
    init {
        viewModelScope.launch {
            size = dataSource.size()
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            dataSource.insert(user)
        }
    }
}