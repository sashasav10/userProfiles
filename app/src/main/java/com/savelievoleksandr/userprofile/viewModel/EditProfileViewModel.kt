package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val _userDetailedLiveData = MutableLiveData<User>()
    private val dataSource = UserDatabase.getInstance(application).userDatabaseDao()
    val userDetailedLiveData: LiveData<User> = _userDetailedLiveData

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