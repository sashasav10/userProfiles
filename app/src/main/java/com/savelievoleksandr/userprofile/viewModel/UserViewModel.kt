package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData
import android.app.Application
import androidx.lifecycle.AndroidViewModel
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

    fun fillUpDatabase() {
        CoroutineScope(Dispatchers.IO).launch {
            if (dataSource.getUser() == null) {
                for (user in dataSource.getAllUsers()) {
                    dataSource.insert(user)
                }
            }
        }
    }



    fun loadUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            userLiveDataList.postValue(dataSource.getAllUsers())
        }
    }
}


