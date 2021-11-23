package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData
import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class UserViewModel(app: Application) : AndroidViewModel(app) {
    private val dataSource = UserDatabase.getInstance(app).userDatabaseDao
    private val userLiveDataList = MutableLiveData<List<User>>()
    var userLiveData: LiveData<List<User>> = userLiveDataList
    fun loadUserData() {
        userLiveDataList.value = dataSource.getAllUsers()
    }

    fun insert() {
        if (dataSource.getUser() == null) {
            for (user in dataSource.getAllUsers())
                dataSource.insert(user)
        }
    }

    fun getAll(): List<User> {
        return dataSource.getAllUsers()
    }
}