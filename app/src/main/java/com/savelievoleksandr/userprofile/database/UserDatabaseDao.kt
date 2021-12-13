package com.savelievoleksandr.userprofile.database

import androidx.room.*
import kotlinx.coroutines.Deferred
import com.savelievoleksandr.userprofile.model.User

@Dao
interface UserDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users_table")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * from users_table WHERE id = :key")
    suspend fun get(key: Int): User?

    @Query("SELECT * FROM users_table LIMIT 1")
    suspend fun getUser(): User?
}