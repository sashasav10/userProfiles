package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailedUserViewModel(app: Application) : AndroidViewModel(app) {
    private val _userDetailedLiveData = MutableLiveData<User>()
    val userDetailedLiveData = _userDetailedLiveData
    val dataSource = UserDatabase.getInstance(app).userDatabaseDao()

    fun loadUserDetailedData(index: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            _userDetailedLiveData.postValue(dataSource.get(index))
        }
    }

    suspend fun updateUserInfo(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            dataSource.update(user)
        }
    }
}