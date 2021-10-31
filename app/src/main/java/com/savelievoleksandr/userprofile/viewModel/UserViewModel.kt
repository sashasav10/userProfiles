package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData

class UserViewModel : ViewModel() {
    private val userData: UserData = UserData()
    private val userLiveDataList = MutableLiveData<List<User>>()
    var userLiveData: LiveData<List<User>> = userLiveDataList
    fun loadUserData() {
        userLiveDataList.value = userData.userList
    }
}