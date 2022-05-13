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

    fun doesListContainOnlyNotEmptyNumbersBiggerThanZero(number: List<String>): Boolean {
        var result: Boolean = true
        number.forEach {
            if (it.isEmpty() || !it.all { char -> char.isDigit() }
            )
                return false
            if (it.toInt() < 0)
                return false
        }
        return true
    }

    fun DoesListContainNotNullValues(editText: List<String>): Boolean {
        var result: Boolean = true
        editText.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }

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