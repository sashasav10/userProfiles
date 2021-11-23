package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData

class DetailedUserViewModel(app:Application) : AndroidViewModel(app) {
    private val userData: UserData = UserData()
    private val _userDetailedLiveData = MutableLiveData<User>()
    val userDetailedLiveData = _userDetailedLiveData
    val dataSource = UserDatabase.getInstance(app).userDatabaseDao

    fun loadUserDetailedData(id: Int) {
        _userDetailedLiveData.value = userData.userList[id]
    }
    fun updateUserInfo(user: User) {
        dataSource.update(user)
    }
}