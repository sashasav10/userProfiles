package com.savelievoleksandr.userprofile.viewModel

import com.savelievoleksandr.userprofile.database.UserDatabaseDao
import com.savelievoleksandr.userprofile.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDAO: UserDatabaseDao
) {
    suspend fun size() = userDAO.size()
    suspend fun insert(user: User) = userDAO.insert(user)
    suspend fun get(id: Int): User = userDAO.get(id)!!
}
