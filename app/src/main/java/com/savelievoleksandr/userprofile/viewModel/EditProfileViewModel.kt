package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val _userDetailedLiveData = MutableLiveData<User>()
    val dataSource = UserDatabase.getInstance(application).userDatabaseDao()
    val userDetailedLiveData: LiveData<User> = _userDetailedLiveData

    fun loadUserDetailedData(index: Int) {
        _userDetailedLiveData.value =  dataSource.get(index)
    }

    suspend fun updateUserInfo(user: User) {
        dataSource.run {
            CoroutineScope(Dispatchers.IO).launch {
                update(user)
            }
        }
    }
}