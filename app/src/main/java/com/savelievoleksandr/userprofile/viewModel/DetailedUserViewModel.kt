package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.savelievoleksandr.userprofile.model.User
import com.savelievoleksandr.userprofile.model.UserData

class DetailedUserViewModel : ViewModel() {
    private val userData: UserData = UserData()
    private val _userDetailedLiveData = MutableLiveData<User>()
    val userDetailedLiveData = _userDetailedLiveData

    fun loadUserDetailedData(id: Int) {
        _userDetailedLiveData.value = userData.userList[id]
    }
}