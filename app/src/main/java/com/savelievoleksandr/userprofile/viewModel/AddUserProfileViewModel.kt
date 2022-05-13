package com.savelievoleksandr.userprofile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.test.core.app.ActivityScenario.launch
import com.savelievoleksandr.userprofile.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserProfileViewModel @Inject constructor(private val repository: UserRepository) :
    ViewModel() {
    var size = 0
    private val _userById = MutableLiveData<User>()

    init {
        viewModelScope.launch {
            size = repository.size()
        }
    }

    fun insertUser(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }
    }

    fun getUserById(id: Int): User {
        lateinit var user:User
        val job: Job = viewModelScope.launch {
            user=repository.get(id)
        }
        return user
    }

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
}