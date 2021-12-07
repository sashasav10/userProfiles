package com.savelievoleksandr.userprofile.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.Deferred
import com.savelievoleksandr.userprofile.model.User

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<User>

    @Query("SELECT * from users_table WHERE id = :key")
    fun get(key: Int): User?

    @Query("SELECT * FROM users_table LIMIT 1")
    suspend fun getUser(): User?
}