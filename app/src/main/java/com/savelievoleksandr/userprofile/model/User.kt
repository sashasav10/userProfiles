package com.savelievoleksandr.userprofile.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey()
    val id: Int,
    @ColumnInfo(name = "user_name")
    val name: String,
    @ColumnInfo(name = "user_photo")
    val photo: String,
    @ColumnInfo(name = "user_last_Seen")
    val lastSeen: String,
    @ColumnInfo(name = "user_posts")
    val posts: String,
    @ColumnInfo(name = "user_followers")
    var followers: String,
    @ColumnInfo(name = "user_following")
    val following: String,
    @ColumnInfo(name = "user_bio")
    val bio: String,
    @ColumnInfo(name = "user_phone")
    val phone: String,
    @ColumnInfo(name = "user_email")
    val email: String
)