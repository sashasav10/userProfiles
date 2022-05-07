package com.savelievoleksandr.userprofile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.savelievoleksandr.userprofile.database.UserDatabase
import com.savelievoleksandr.userprofile.model.User
import kotlinx.coroutines.launch

class AddUserProfileViewModel(application: Application) : AndroidViewModel(application) {

    fun isNumberValid(number: List<String>): Boolean {
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

    fun isEmpty(editText: List<String>): Boolean {
        var result: Boolean = true
        editText.forEach {
            if (it.isEmpty())
                return false
        }
        return true
    }


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