package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.model.UserData

class UserViewModel : ViewModel() {
    private val userData: UserData = UserData()
    var userLiveData = MutableLiveData<UserData>()
    fun loadUserData() {
        userLiveData.value = userData
    }
}