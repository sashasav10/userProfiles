package com.savelievoleksandr.userprofile.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.savelievoleksandr.userprofile.model.User

@Dao
interface UserDatabaseDao {

    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("SELECT * FROM users_table")
    fun getAllUsers(): List<User>

    @Query("SELECT * from users_table WHERE id = :key")
    fun get(key: Int): User?

    @Query("SELECT * FROM users_table LIMIT 1")
    fun getUser(): User?
}